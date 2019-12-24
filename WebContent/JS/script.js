
// Validator for form in Join.jsp
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

//Email validator to avoid registration using email of existing user

//find reservation
function FindReservation() {
	var bookingId = document.forms["findBoo"]["bookingId"].value;
	var email = document.forms["findBoo"]["email"].value;
	if ((bookingId == 0 || bookingId == "") || (email == null || email == "")) {

		alert("Please enter valid information");
		return false;
	} else {
		$.ajax({
			url : 'FindReservation',
			method : 'POST',
			data : {
				bookingId : bookingId,
				email : email
			},
			success : function(resultText) {
				$('#target').html(resultText);
			},
			error : function(jqXHR, exception) {
				console.log('Error occured!!');
			}
		});
	}
}

//Alter modal based on button clicked

$('#contactForm').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget) // Button that triggered the modal
	var recipient = button.data('whatever') // Extract info from data-* attributes
	// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
	// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
	var modal = $(this)
	modal.find('.modal-title').text('New message to ' + recipient)

})

//Email sender 

function EmailSender() {
	var sub = document.getElementById("subject").value;
	var message = document.getElementById("message-text").value;
	var emailTo = document.getElementById("emailTo").value;
	if ((sub == null || sub == "") ||

	(message == null || message == "")) {

		alert("Please enter valid information");
		return false;
	} else {
		$('#processing').modal('show');
		$('#contactForm').modal('hide');
		$.ajax({
			url : 'EmailSender',
			method : 'POST',
			data : {
				'sub' : sub,
				'message' : message,
				'emailTo' : emailTo
			},
			success : function(resultText) {

				$('#processing').modal('hide');
				$('#emailSent').modal('show');

			},
			error : function(jqXHR, exception, errorThrown) {
				console.log('Error occured!!');
				$('#contactForm').modal('hide');

			}

		});
	}
}

//maps

function initMap() {
	var location = {
		lat : 49.876445,
		lng : -97.247000
	};
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 11,
		center : location
	});
	// The marker, positioned at location
	var marker = new google.maps.Marker({
		position : location,
		map : map
	});
}
//zoom in and out img
$(document).ready(function() {

	$('.hotelImg').mouseenter(function() {

		$(this).animate({
			width : "99%"
		}, 700);

	});

	$('.hotelImg').mouseleave(function() {

		$(this).animate({
			width : "100%"
		}, 700);
	});
});

//email validator for join.jsp

$(document).ready(function() {
	//grab target
	var validator = $('#emailValidator');

	$('#inputEmail4').blur(function() {
		//grab value from input field

		var email = $('#inputEmail4').val();
		var controler = new RegExp(/[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}/igm);
		var result = controler.test(email);
		console.log(result);

		if (result == false) {
			event.preventDefault();
			event.stopPropagation();
		} else {
			//post to servlet
			$.ajax({
				type : "post",
				url : "EmailValidator",
				data : {
					'email' : email
				},
				success : function(response) {
					validator.show();
					validator.html(response);

				}
			});
		}

	});
	//when on focus, clean previous output
	$('#inputEmail4').focus(function() {
		validator.html('');

	});
});
//on submit, hide output for validator
$('#joinSubmitButton').click(function() {
	$('#emailValidator').hide();

});
