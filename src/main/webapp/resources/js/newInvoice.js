var creationDate = $('input#creationDatePicker');
var soldDate = $('input#soldDatePicker');

var searchCustomerButton = $('a#searchCustomer');
var searchCustomerModal = $('div#searchCustomerModal');

$(document).ready(function() {
	
	/* Date pickers */
	
	initDatePickers();
	
	/* Search customer */
	
	initSearchCustomerModal();
	
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
	});
}