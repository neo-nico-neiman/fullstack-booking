<!doctype html>
<html lang="en">
<head>
<%@page import="java.sql.Date"%>

<meta charset="utf-8">
<title>Book</title>



<%
	//user info 
	String log_fName;
	String log_lName;
	String log_phone;
	String log_email;
	String log_address;
	String log_city;
	String log_postalCode;
	String log_country;

	if (session.getAttribute("username") == null) {

		log_fName = "";
		log_lName = "";
		log_phone = "";
		log_email = "";
		log_address = "";
		log_city = "";
		log_postalCode = "";
		log_country = "";

	} else {
		log_fName = String.valueOf(session.getAttribute("fName"));
		log_lName = String.valueOf(session.getAttribute("lName"));
		log_phone = String.valueOf(session.getAttribute("phone"));
		log_email = String.valueOf(session.getAttribute("email"));
		log_address = String.valueOf(session.getAttribute("address"));
		log_city = String.valueOf(session.getAttribute("city"));
		log_postalCode = String.valueOf(session.getAttribute("postalCode"));
		log_country = String.valueOf(session.getAttribute("country"));
	}

	//All the details of the desired booking
	String roomNumberSelected = null;
	Date checkInForRoom = null;
	Date checkOutForRoom = null;
	int totalDays = 0;
	int numberOfGuest = 0;
	double price = 0;
	String rDetails = null;
	String rDescription = null;
	String img = null;
	String categ = null;
	double tax = 0;
	double total = 0;
	try {
		roomNumberSelected = String.valueOf(session.getAttribute("rNum"));
		checkInForRoom = Date.valueOf(String.valueOf(session.getAttribute("cIn")));
		checkOutForRoom = Date.valueOf(String.valueOf(session.getAttribute("cOut")));
		Object obj = session.getAttribute("totalDays");
		totalDays = Integer.valueOf(String.valueOf(obj));
		numberOfGuest = Integer.parseUnsignedInt(String.valueOf(session.getAttribute("numGuest")));
		price = Double.parseDouble(String.valueOf(session.getAttribute("price")));
		rDetails = String.valueOf(session.getAttribute("rDetails"));
		rDescription = String.valueOf(session.getAttribute("rDescription"));
		img = String.valueOf(session.getAttribute("img"));
		categ = String.valueOf(session.getAttribute("categ"));
		tax = Double.parseDouble(String.valueOf(session.getAttribute("tax")));
		total = Double.parseDouble(String.valueOf(session.getAttribute("total")));
	} catch (Exception e) {
		response.sendRedirect("Index.jsp");
	}
%>

<%
	//delete cache to prevent back button after logout
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>
</head>

<body>


	<jsp:include page="Header.jsp" flush="true" />
	<div class="bg-dark  p-4"></div>
	<div class="tm-container my-3">
		<div class="row  ">
			<div class="col-lg-8  card bg-light order-lg-first order-last">
				<form name="gInfo" class="d-flex flex-column needs-validation"
					style="height: 100%;" action="BookingExecuter" method="POST"
					novalidate>
					<h4 class="mt-4">Guest Information</h4>
					<hr>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputFName4">First Name</label> <input type="text"
								class="form-control" id="inputFName4" name="fName"
								placeholder="If you have two it's cool" value="<%=log_fName%>"
								required>
							<div class="valid-feedback">Looks good!</div>
							<div class="invalid-feedback">What is your name?</div>
						</div>
						<div class="form-group col-md-6">
							<label for="inputLName4">Last Name</label> <input type="text"
								class="form-control" id="inputLName4" name="lName"
								placeholder="Surname or family name" value="<%=log_lName%>"
								required>
							<div class="valid-feedback">Good job!</div>
							<div class="invalid-feedback">Don't forget your Last Name.
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputEmail4">E-mail</label> <input type="email"
								class="form-control" id="inputEmail4" name="email"
								placeholder="We care about your privacy "
								value="<%=log_email%>" required>
							<div class="valid-feedback">Great!</div>
							<div class="invalid-feedback">Please provide a valid
								e-mail.</div>
						</div>
						<div class="form-group col-md-6">
							<label for="inputPhone4">Phone</label> <input type="number"
								class="form-control" id="inputPhone4" name="phone"
								placeholder="Mobile or home" value="<%=log_phone%>" required>
							<div class="valid-feedback">Super!</div>
							<div class="invalid-feedback">Please provide a valid phone.
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="inputAddress">Address</label> <input type="text"
							class="form-control" id="inputAddress" name="address"
							placeholder="1234 Main St Apt. 3B" value="<%=log_address%>"
							required>
						<div class="valid-feedback">So good!</div>
						<div class="invalid-feedback">Please provide a valid
							Address.</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputCity">City</label> <input type="text"
								class="form-control" id="inputCity" name="city"
								value="<%=log_city%>" placeholder="The city where you live"
								required>
							<div class="valid-feedback">Right on!</div>
							<div class="invalid-feedback">Aren't you proud of your
								city?</div>
						</div>
						<div class="form-group col-md-4">
							<label for="inputCountry">Country</label> <input type="text"
								class="form-control" id="inputCountry" name="country"
								value="<%=log_country%>" placeholder="Country" required>
							<div class="valid-feedback">We love your country!</div>
							<div class="invalid-feedback">Are forgetting something?</div>
						</div>
						<div class="form-group col-md-2">
							<label for="inputZip">Postal Code</label> <input type="text"
								class="form-control" id="inputZip" name="postalCode"
								placeholder="Postal Code" value="<%=log_postalCode%>" required>
							<div class="valid-feedback">yeah!</div>
							<div class="invalid-feedback">Last but not least.</div>
						</div>
					</div>
					<div>
						<hr>
						<h4 class="mt-2">Payment Information</h4>
						<hr>
					</div>
					<div class="form-row ">
						<div class="form-group col-md-6">
							<label for="inputCCNumber">Credit Card</label> <input
								type="password" class="form-control" id="inputCCNumber"
								name="ccNumber" placeholder="Secured Checkout" required>
							<div class="valid-feedback">Looks good!</div>
							<div class="invalid-feedback">Please provide a valid Credit
								card Number.</div>
						</div>
						<div class="form-group col-md-3">
							<label for="inputExp">Expiration</label> <input type="number"
								class="form-control" id="inputExp" name="expir"
								placeholder="MM/YY" required>
							<div class="valid-feedback">Looks good!</div>
							<div class="invalid-feedback">Please provide a valid
								expiration date.</div>
						</div>
						<div class="form-group col-md-3">
							<label for="CVV">CVV</label> <input type="number"
								class="form-control" id="CVV" name="cvv" placeholder="3 digits"
								required>
							<div class="valid-feedback">Looks good!</div>
							<div class="invalid-feedback">Please provide a valid CVV.</div>
						</div>

					</div>
					<div class="batches">
						<img src="img/SVG/americanexpress.svg" alt="amex"> <img
							src="img/SVG/paypal.svg" alt="paypal"> <img
							src="img/SVG/visa.svg" alt="visa"> <img
							src="img/SVG/ripple.svg" alt="ripple"> <img
							src="img/SVG/apple.svg" style="color: royalblue" alt="applepay">
					</div>
					<div class="my-auto w-100 ">
						<input name="roomNumberSelected" type="hidden"
							value="<%=roomNumberSelected%>"> <input
							name="checkInForRoom" type="hidden" value="<%=checkInForRoom%>">
						<input name="checkOutForRoom" type="hidden"
							value="<%=checkOutForRoom%>"> <input name="nights"
							type="hidden" value="<%=totalDays%>"> <input name="ppn"
							type="hidden" value="<%=price%>"> <input name="tax"
							type="hidden" value="<%=tax%>"> <input name="total"
							type="hidden" value="<%=total%>">
						<button type="submit" class="btn btn-primary btn-block mb-2">Complete
							Reservation</button>
					</div>
				</form>
			</div>
			<div
				class="col-lg-3 ml-lg-4 card mb-4 mb-lg-0 order-lg-last order-first">
				<div class="px-1 px-md-2 px-lg-3 " style="width: 100%;">
					<img src="<%=img%>" alt="Room" class="card-img-top">
					<div class="card-body pl-0">
						<h5 class="card-title">Booking Summary</h5>
						<p class="card-text">
							Room Category
							<%=categ%></p>
						<small class="text-muted"><%=rDescription%></small>
					</div>
					<hr>
					<ul class="list-inline ">
						<li class="list-inline-item"><strong>Guests</strong></li>
						<li class="list-inline-item float-right pr-4"><%=numberOfGuest%></li>
					</ul>
					<hr>
					<ul class="list-inline ">
						<li class="list-inline-item"><strong>Nights</strong></li>
						<li class="list-inline-item float-right pr-4"><%=totalDays%></li>
					</ul>
					<hr>
					<ul class="list-inline ">
						<li class="list-inline-item"><strong>Check-In</strong></li>
						<li class="list-inline-item float-right pr-4"><%=checkInForRoom%></li>
					</ul>
					<hr>
					<ul class="list-inline ">
						<li class="list-inline-item"><strong>Check-Out</strong></li>
						<li class="list-inline-item float-right pr-4"><%=checkOutForRoom%></li>
					</ul>
					<hr>

					<ul class="list-inline ">
						<li class="list-inline-item"><small class="text-muted">The
								reservation will be confirmed in the hotel's currency. Please be
								advised that converted rates are estimated based on the daily
								exchange rate and may therefore vary. </small></li>
					</ul>
					<hr>
					<ul class="list-inline ">
						<li class="list-inline-item"><strong>Nightly Rate </strong></li>
						<li class="list-inline-item float-right pr-4">$ <%=price%></li>
					</ul>
					<hr>
					<ul class="list-inline ">
						<li class="list-inline-item"><strong>Taxes &amp;
								Fees</strong></li>
						<li class="list-inline-item float-right pr-4">$ <%=tax%></li>
					</ul>
					<hr>
					<ul class="list-inline ">
						<li class="list-inline-item"><strong>Total</strong></li>
						<li class="list-inline-item float-right pr-4">$ <%=total%></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="Footer.jsp" flush="true" />
	<script src="JS/booking.js"></script>


</body>
</html>

