var searchProductButton = $('a#product-model-trigger');
var searchProductsModal = $('div#searchProductModal');
var searchProductModalCloser = $('button#productsModalCloser');

var searchProductInput = $('input#searchProductInput');
var productNameInput = $('input#prod-name');
var tableBody = $('tbody#productsJsonList');

$(document).ready(function() {
	initSearchProductModal();
});

function initSearchProductModal() {
	searchProductButton.on('click', function() {
		searchProductsModal.modal();
		getProductsJsons();
	});
	
	searchProductModalCloser.on('click', function() {
		searchProductsModal.modal('hide');
		removeProductsRows();
	});
	
	initProductFilter();
}

function getProductsJsons(pattern) {
	$.ajax({
		type : "POST",
		url : "productsJson",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		data : pattern != null ? pattern : "",
		success : function(data) {
			prepareProductRows(data);
		},
		failure : function(errMsg) {
			console.log('newInvoiceProduct.js : getProductsJsons() : post failure');
		}
	});
}

function prepareProductRows(data) {
	for(var i = 0 ; i < data.length ; i++) {
		tableBody.append('<tr class="clickable"><td style="display: none;">' + data[i].id + '</td><td>' + data[i].productName + '</td><td>' + data[i].basePrice + '</td><td>' + data[i].tax + '</td></tr>');
	}
	initProductsRowListener();
}

function initProductsRowListener() {
	$(document).on('click', 'tbody#productsJsonList tr', function() {
		var name = $(this).find('td:second').html();
		prepareForm(name);
	});
}

function prepareForm(name) {
	productNameInput.val(name);
}

function removeProductsRows() {
	$('tbody#productsJsonList tr').each(function() {
		$(this).remove();
	});
}

function initProductFilter() {
	searchProductInput.keyup(function() {
		removeProductsRows();
		getProductsJsons(searchProductInput.val());
	});
}