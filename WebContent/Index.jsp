<!doctype html>
<html lang="en">

<head>

<title>Index</title>

<%
	//delete cache to prevent back button after logout
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>
</head>

<body>
	<div class="parallax-window" data-parallax="scroll" data-speed="0.2"
		data-natural-width="1200" data-image-src="img/forest.jpg"
		iosFix="true" androidFix="true" zIndex="-10">
		<section class="sectionParallax">
			<ul class="heroText">
				<li class="scroll " data-rateY="1" data-rateX="-1"
					data-direction="horizontal">hotel</li>
				<li class="scroll " data-rate="1" data-direction="vertical">Patagonia</li>
				<li class="scroll " data-rateY="1" data-rateX="1"
					data-direction="horizontal">boutique</li>

			</ul>
		</section>
	</div>

	<jsp:include page="Header.jsp" flush="true" />

	<div class="bg-dark ">
		<div class="tm-container">
			<form name="searchRoom" method="post">
				<div class="form-row">
					<div class="col-sm-4 py-3">
						<input type="text" id="checkInSide" class="form-control checkIn"
							placeholder="Check-in" readonly>
					</div>
					<div class="col-sm-4 py-3">
						<input type="text" id="checkOutSide" class="form-control checkOut"
							placeholder="Check-out" readonly>
					</div>
					<div class="col-sm-2 py-3">
						<input type="number" class="form-control" placeholder="Guests"
							name="numberOfGuestSide" id="numberOfGuestSide" min="1" max="3"
							value="2" required>
					</div>
					<div class="col-sm-2 py-3 ">
						<button type="button"
							class="btn btn-outline-warning mr-0 float-right btn-block"
							onclick="AvailabilityChecker()">Search</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div id="main_content" class="tm-container clearfix px-0">
		<div class="container fadeIn bg-white pl-0 float-left clearfix">
			<div class="row my-4 ">
				<div class="col-lg-8 ">
					<img class="rounded " src="img/hotel_view2.jpg" alt="hotel lounge" />
				</div>
				<div class="col-lg-4">
					<h2 class="brand mt-2">Hotel Patagonia</h2>
					<h4>
						<q>In a glorious True North setting amid supreme natural
							wonders</q>
					</h4>
					<p>Inside the hotel, one now finds warm, welcoming rooms that
						feature the finest quality traditional materials accented by
						modern touches, bringing to mind a friend's home in the mountains.
						Here, too, one discovers fine restaurants, fun eateries, a
						stunning wellness and spa area, conference facilities, a lounge
						"living room" with unparalleled views and an accompanying
						fireplace and library of 500 books.</p>
				</div>
			</div>
			<hr>
		</div>
		<div class="container fadeIn bg-white float-right pr-0 clearfix">
			<div class="row my-4">
				<div class="col-lg-4">
					<h4>
						<q>The guestrooms at Patagonia Hotel capture the essence of a
							friend's mountain retreat</q>
					</h4>
					<p>Double Rooms are either 28 square meters and face north or
						20 square meters and face south (some south-facing Double rooms
						have slightly restricted views). Single rooms are south-facing and
						18 square meters. All rooms include bath and shower, flat-screen
						televisions, and a "Suitepad" with over 100 complimentary daily
						newspapers and magazines in German, English, and French.</p>
					<br>
				</div>
				<div class="col-lg-8 ">
					<img class="rounded " src="img/mountain_retreat.jpg"
						alt="Mountain retreat" />
				</div>
			</div>
		</div>
	</div>
	<div class="jumbotron marginx-10  ">
		<h1 class="display-4">Join the family!</h1>
		<p class="lead">In just under one minute join the family, get
			weekly rewards and invitations for special events.</p>
		<hr class="my-4">
		<p>
			<em>Did you know that our Hotel donates 10% of annual revenue to
				fire fighters and forest conservationists?</em>
		</p>
		<a class="btn btn-primary btn-lg" href="Join.jsp" role="button">Learn
			more</a>
	</div>
	<div class="tm-container ">
		<div class="row">
			<div class="col-lg-8  fadeIn" id="target_bottom">
				<div class="imgFood mx-0 card" style="max-width: 100%;">
					<div class="row ">

						<div class="col-lg-4 ">
							<div class="card-header opacity-70 text-light ">
								<h5 class="card-title">Savour Winnipeg's Freshest Flavours</h5>
								<p class="card-text">The Patagonia Hotel, serves
									sophisticated European cuisine with an International twist.
									Menus change regularly to showcase the seasons' freshest
									flavors, including sweet corn, late summer berries, apples and
									plums.</p>

								<small>*If you have any special request, please let us
									know in advance and we will take care of you</small>

								<h5 class="mt-2">Hours:</h5>
								<p>Monday - Friday: 6am - 7am</p>
								<p>Pastry and Coffee only 7am - 10am Full hot breakfast</p>
							</div>
						</div>
						<div class="col-lg-8 ">
							<!-- <img src="img/food1.jpg" class="card-img " alt="food fest"> -->
						</div>
					</div>
				</div>
			</div>

			<div class="col-lg-4 card mt-2 mt-lg-0">
				<form name="searchRoomB" method="post" class="d-flex flex-column"
					style="height: 100%;">

					<div class="py-2 ml-4">
						<h4>Check Availability and Reserve</h4>
						<small>Find the best accommodation for your upcoming stay.<a
							href="#"> Why book with us?</a></small>
					</div>


					<div class="form-group my-2 my-md-auto card opacity-70">
						<label for="formGroupExampleInput" class="ml-4 text-white">Check-in
							Date</label> <input type="text" id="cIBottom"
							class="form-control checkIn pl-4 " placeholder="Select a date"
							readonly>
					</div>
					<div class="form-group my-2 my-md-auto card opacity-70">
						<label for="formGroupExampleInput2" class="ml-4 text-white">Check-out
							Date</label> <input type="text" id="cOBottom"
							class="form-control checkOut pl-4" placeholder="Select a date"
							readonly>
					</div>
					<div class="form-group my-2 my-md-auto card opacity-70">
						<label for="formGroupExampleInput2" class="ml-4 text-white">Number
							of Guests</label> <input type="text" id="gBottom"
							class="form-control pl-4" value="2"
							placeholder="How many adults?">
					</div>
					<div class=" my-2 my-md-auto">
						<button type="button"
							class="btn btn-outline-warning btn-md btn-block mt-2"
							onclick="AvailabilityCheckerBottom()">Search</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="Footer.jsp" flush="true" />
	<script src="JS/parallax.js"></script>
	<script src="JS/index.js">
			</script>

</body>

</html>