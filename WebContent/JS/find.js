
//find reservation
function FindReservation(){
	var bookingId= document.forms["findBoo"]["bookingId"].value;
	var email= document.forms["findBoo"]["email"].value;
	if ((bookingId==0 || bookingId=="") || 
			(email==null || email=="") ) {
		
		alert("Please enter valid information");
		return false;
	}else {
		$.ajax({
			url     : 'FindReservation',
			method     : 'POST',
			data     : {bookingId : bookingId, email: email},
			success    : function(resultText){
				$('#target').html(resultText);
				},
			error : function(jqXHR, exception){
			console.log('Error occured!!');
			}
			});
			}
		}

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