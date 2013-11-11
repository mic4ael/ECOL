var dateFrom = $('input#dateFrom');
var dateTo = $('input#dateTo');

$(document).ready(function() {
	initDatePickers();
});

function initDatePickers() {
	dateFrom.datepicker({
		dateFormat: "dd - mm - yy"
	});
	dateTo.datepicker({
		dateFormat: "dd - mm - yy"
	});
}