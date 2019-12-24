// Validator for form 
(function() {
	'use strict';
	window.addEventListener('load', function() {
		// Fetch all the forms we want to apply custom Bootstrap validation styles to
		var forms = document.getElementsByClassName('needs-validation');
		// Loop over them and prevent submission
		var validation = Array.prototype.filter.call(forms, function(form) {
			form.addEventListener('submit', function(event) {
				if (form.checkValidity() === false) {
					event.preventDefault();
					event.stopPropagation();
				}
				form.classList.add('was-validated');
			}, false);
		});
	}, false);
})();

//email validator 

$(document).ready(function() {
	//grab target
	var validator = $('#emailValidator');

	$('#inputEmail4').blur(function() {
		//grab value from input field

		var email = $('#inputEmail4').val();
		if (email != "") {
			//post to servlet
			$.ajax({
				type : "post",
				url : "EmailValidator",
				data : {
					'email' : email
				},
				success : function(response) {
					validator.fadeIn(1000);
					validator.html(response);

				}
			});
		}

	});
	//when on focus, clean previous output
	$('#inputEmail4').focus(function() {
		validator.fadeOut(1000);

	});
});
//on submit, hide output for validator
$('#joinSubmitButton').click(function() {
	$('#emailValidator').hide();

});
