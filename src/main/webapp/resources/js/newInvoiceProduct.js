var searchProductButton = $('a#product-model-trigger');
var searchProductsModal = $('div#searchProductModal');
var searchProductModalCloser = $('button#productsModalCloser');

var searchProductInput = $('input#searchProductInput');
var productNameInput = $('input#prod-name');
var tableBody = $('tbody#productsJsonList');

var hiddenProductId = $('input#hiddenProductId');

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
	$
			.ajax({
				type : "POST",
				url : "productsJson",
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				data : pattern != null ? pattern : "",
				success : function(data) {
					prepareProductRows(data);
				},
				failure : function(errMsg) {
					console
							.log('newInvoiceProduct.js : getProductsJsons() : post failure');
				}
			});
}

function prepareProductRows(data) {
	for ( var i = 0; i < data.length; i++) {
		tableBody.append('<tr class="clickable"><td style="display: none;">'
				+ data[i].id + '</td><td>' + data[i].productName + '</td><td>'
				+ data[i].basePrice + ' PLN' + '</td><td>' + data[i].taxAmount
				+ ' %' + '</td></tr>');
	}
	initProductsRowListener();
}

function initProductsRowListener() {
	$(document).on('click', 'tbody#productsJsonList tr', function() {
		var id = $(this).find('td:first').html();
		var name = $(this).find('td:eq(1)').html();
		prepareForm(id, name);
	});
}

function prepareForm(id, name) {
	hiddenProductId.val(id);
	productNameInput.val(name);
	searchProductsModal.modal('hide');
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