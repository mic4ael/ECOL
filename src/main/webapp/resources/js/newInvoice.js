var creationDate = $('input#creationDatePicker');
var soldDate = $('input#soldDatePicker');
var paymentDate = $('input#paymentDate');

var searchCustomerButton = $('a#searchCustomer');
var searchCustomerModal = $('div#searchCustomerModal');
var customersTableBody = $('tbody#customersJsonList');
var customersModalCloser = $('button#customersModalCloser');
var searchCustomerInput = $('input#searchCustomerInput');

var searchProductButton = $('product-model-trigger');

/* Customer form handlers */

var customerName = $('input#customer-name');
var customerCity = $('input#customer-city');
var customerStreet = $('input#customer-street');
var customerPostal = $('input#customer-postal');
var customerHome = $('input#customer-home');
var customerNip = $('input#customer-nip');
var customerContactPhone = $('input#customer-contactPhone');
var customerFaxPhone = $('input#customer-faxPhone');
var customerEmail = $('input#customer-email');

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
	paymentDate.datepicker({
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
		var hiddenTd = $(this).find('td:first').html();
		getJsonAndPrepareForm(hiddenTd);
	});
}

function getJsonAndPrepareForm(hiddenTd) {
	$.ajax({
		type : "POST",
		url : "customerJson",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		data : hiddenTd,
		success : function(data) {
			prepareCustomerForm(data);
		},
		failure : function(errMsg) {
			console.log('newInvoice.js : getJsonAndPrepareForm(hiddenTd) : post failure');
		}
	});
}

function prepareCustomerForm(customerInfo) {
	customerName.val(customerInfo.name);
	customerCity.val(customerInfo.address.city);
	customerStreet.val(customerInfo.address.street);
	customerPostal.val(customerInfo.address.postalCode);
	customerHome.val(customerInfo.address.homeNumber);
	customerNip.val(customerInfo.nip);
	customerContactPhone.val(customerInfo.contactPhone);
	customerFaxPhone.val(customerInfo.faxPhone);
	customerEmail.val(customerInfo.email);
	
	searchCustomerModal.modal('hide');
}

function initCustomerFilter() {
	searchCustomerInput.keyup(function() {
		removeCustomersRows();
		getCustomersJsons(searchCustomerInput.val());
	});
}