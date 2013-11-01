var creationDate = $('input#creationDatePicker');
var soldDate = $('input#soldDatePicker');

var searchCustomerButton = $('a#searchCustomer');
var searchCustomerModal = $('div#searchCustomerModal');
var customersTableBody = $('tbody#customersJsonList');
var customersModalCloser = $('button#customersModalCloser');
var searchCustomerInput = $('input#searchCustomerInput');

$(document).ready(function() {
	
	/* Date pickers */
	
	initDatePickers();
	
	/* Search customer */
	
	initSearchCustomerModal();
	initCustomerFilter();
	
});

function initDatePickers() {
	creationDate.datepicker({
		dateFormat: "dd - mm - yy"
	});
	soldDate.datepicker({
		dateFormat: "dd - mm - yy"
	});
}

function initSearchCustomerModal() {
	searchCustomerButton.on('click', function() {
		searchCustomerModal.modal();
		getCustomersJsons();
	});
	
	customersModalCloser.on('click', function() {
		searchCustomerModal.modal('hide');
		removeCustomersRows();
	});
	
	initCustomerFilter();
}

function removeCustomersRows() {
	$('tbody#customersJsonList tr').each(function() {
		$(this).remove();
	});
}

function getCustomersJsons(pattern) {
	$.ajax({
		type : "POST",
		url : "customersJson",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		data : pattern != null ? pattern : "",
		success : function(data) {
			prepareCustomerRows(data);
		},
		failure : function(errMsg) {
			console.log('newInvoice.js : getCustomersJsons() : post failure');
		}
	});
}

function prepareCustomerRows(data) {
	for(var i = 0 ; i < data.length ; i++) {
		customersTableBody.append('<tr class="clickable"><td style="display: none;">' + data[i].id + '</td><td>' + data[i].name + '</td><td>' + data[i].address.city + '</td><td>' + data[i].nip + '</td></tr>');
	}
	initCustomerRowListener();
}

function initCustomerRowListener() {
	$(document).on('click', 'tbody#customersJsonList tr', function() {
		rowChildren = $(this).children();
		console.log(rowChildren[0].html());
	});
}

function initCustomerFilter() {
	searchCustomerInput.keyup(function() {
		removeCustomersRows();
		getCustomersJsons(searchCustomerInput.val());
	});
}