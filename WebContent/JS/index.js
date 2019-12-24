$(document).ready(function() {
	var pageWidth = $(window).width();
	var tags = document.querySelectorAll('.fadeIn');
	// if mobile ignore first

	if (pageWidth < 376) {

		var firstTag = tags[0];
		$(firstTag).addClass('visible');
	}
});

$(document).on('scroll', function() {
	var pageTop = $(document).scrollTop();
	var pageBottom = pageTop + $(window).height();
	var tags = document.querySelectorAll('.fadeIn');

	for (var i = 0; i < tags.length; i++) {
		var tag = tags[i];
		// as soon as the element crosses the bottom it becomes visible
		if ($(tag).position().top < pageBottom) {
			$(tag).addClass("visible");
		} else {
			$(tag).removeClass("visible");
		}
	}
});

// AJAX function to verify availability and display results on div without
// refreshing entire page

function AvailabilityChecker() {
	var checkIn = $('#checkInSide').val();
	var checkOut = $('#checkOutSide').val();
	var numberOfGuest = $('#numberOfGuestSide').val();

	if ((checkIn == null || checkIn == "")
			|| (checkOut == null || checkOut == "")
			|| (numberOfGuest == null || numberOfGuest <= 0 || numberOfGuest > 3)) {

		alert("Please enter valid information");
		return false;
	} else {
		$.ajax({
			url : 'AvailabilityChecker',
			method : 'POST',
			data : {
				'checkIn' : checkIn,
				'checkOut' : checkOut,
				'numberOfGuest' : numberOfGuest
			},
			success : function(resultText) {
				$('#main_content').html(resultText);
			},
			error : function(jqXHR, exception, errorThrown) {
				console.log('Error occured!!');
			}

		});
	}
}

// Availability checker bottom

function AvailabilityCheckerBottom() {
	var checkIn = $('#cIBottom').val();
	var checkOut = $('#cOBottom').val();
	var numberOfGuest = $('#gBottom').val();

	if ((checkIn == null || checkIn == "")
			|| (checkOut == null || checkOut == "")
			|| (numberOfGuest == null || numberOfGuest <= 0 || numberOfGuest > 3)) {

		alert("Please enter valid information");
		return false;
	} else {
		$.ajax({
			url : 'AvailabilityChecker',
			method : 'POST',
			data : {
				'checkIn' : checkIn,
				'checkOut' : checkOut,
				'numberOfGuest' : numberOfGuest
			},
			success : function(resultText) {
				$('#target_bottom').html(resultText);
			},
			error : function(jqXHR, exception, errorThrown) {
				console.log('Error occured!!');
			}

		});
	}
}
// Date picker

$(function() {
	$(".checkOut").datepicker({
		dateFormat : "yy-mm-dd",
		minDate : '+1d'
	});
	$(".checkIn").datepicker({
		dateFormat : "yy-mm-dd",
		minDate : 'today',
		onSelect : function(date) {

			var selectedDate = new Date(date);
			var msecsInADay = 86400000;
			var endDate = new Date(selectedDate.getTime() + (2 * msecsInADay));

			// Set Minimum Date of EndDatePicker After Selected Date of
			// StartDatePicker
			$(".checkOut").datepicker("option", "minDate", endDate);

		}
	}).val();

	$(".checkOut").datepicker({
		dateFormat : "yy-mm-dd"
	}).val();
});

// input that is readonly has bg white
function whiteInput() {
	$(':input[readonly]').css({
		'background-color' : '#fff'
	});
}

$(document).ready(function() {
	whiteInput();
});

// parallax for header

$(document).ready(
		function() {

			window.addEventListener('scroll', function(e) {

				const target = document.querySelectorAll('.scroll');

				var index = 0, length = target.length;
				for (index; index < length; index++) {
					var pos = window.pageYOffset * target[index].dataset.rate;
					if (pos >= 540) {
						pos = 0;
					}
					if (target[index].dataset.direction == 'vertical') {
						target[index].style.transform = 'translate3d(0px,'
								+ pos + 'px, 0px)';

					} else {
						var posY = window.pageYOffset
								* target[index].dataset.ratey;
						var posX = window.pageYOffset
								* target[index].dataset.ratex;
						var pageWidth = $(window).width();
						if (pageWidth > 1450 && posY >= 540) {
							posY = 0;
							posX = 0;
						} else if (pageWidth >= 1350 && pageWidth < 1450
								&& posY >= 420) {
							posY = 0;
							posX = 0;
						}

						target[index].style.transform = 'translate3d(' + posX
								+ 'px,' + posY + 'px, 0px)';

					}

				}
			})

		});
