var nameInput = $('input#customerNameInput');

$(document).ready(function() {
	nameInput.keyup(function() {
		if (nameInput.val() == null || nameInput.val() == '') {
			nameInput.css('background', '#DDDDDD');
		} else {
			askServerAboutName(nameInput.val());
		}
	});
});

function askServerAboutName(email) {
	$.ajax({
		type : "POST",
		url : "isCustomerAvailable",
		data : email,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			informClient(data);
		},
		failure : function(errMsg) {
			console.log('newCustomer.js : askServerAboutName() : post failure');
		}
	});
}

function informClient(message) {
	if(message == true) {
		nameInput.css('background', '#BEF5AE');
	} else {
		nameInput.css('background', '#F29999');
	}
}