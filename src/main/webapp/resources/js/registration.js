var infoParagraph = $('p#regInfo');
var emailInput = $('input#emailInput');

$(document).ready(function() {
	emailInput.keyup(function() {
		if ((emailInput.val()).length >= 6) {
			askServerAboutEmail(emailInput.val());
		}
		if ((emailInput.val()).length < 6 || emailInput.val().length == 0) {
			infoParagraph.text('');
			emailInput.css('background', '#FFFFFF');
		}
	});
});

function askServerAboutEmail(email) {
	$.ajax({
		type : "POST",
		url : "test",
		data : email,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			informClient(data);
		},
		failure : function(errMsg) {
			console.log('registration.js : askServerAboutEmail() : post failure');
		}
	});
}

function informClient(message) {
	if(message == true) {
		infoParagraph.html('E-mail is available').css('color', '#78E359').css('font-weight', 'bold');
		emailInput.css('background', '#BEF5AE');
	} else {
		infoParagraph.html('E-mail is not available!').css('color', '#FF0000').css('font-weight', 'bold');
		emailInput.css('background', '#F29999');
	}
}