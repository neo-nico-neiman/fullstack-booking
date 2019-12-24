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
