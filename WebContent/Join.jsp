<!doctype html>
<html lang="en">
<head>
<title>Join</title>

<%
	//delete cache to prevent back button after logout
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>
</head>

<body>
	<jsp:include page="Header.jsp" flush="true" />
	<div class="bg-dark  p-4"></div>

	<div class="tm-container my-3 ">
		<%
			if (session.getAttribute("error") != null) {
				out.println("<div class=\"row\">");
				out.println("<div class=\"col-sm-12 bg-light \">");
				out.println("<h4 class=\"text-danger\">Something went wrong! Please try again.</h4>");
				out.println("</div>");
				out.println("</div>");
				session.removeAttribute("error");
			}
		%>
		<div class="row">
			<div class="col-sm-12 bg-light ">
				<form name="gInfo" class="d-flex flex-column needs-validation"
					style="height: 100%;" action="NewGuest" method="POST" novalidate>
					<h4 class="mt-4">Join Our Family</h4>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputFName4">First Name</label> <input type="text"
								class="form-control" id="inputFName4" name="fName"
								placeholder="First Name" required>
							<div class="valid-feedback">Looks good!</div>
							<div class="invalid-feedback">What is your name?</div>
						</div>
						<div class="form-group col-md-6">
							<label for="inputLName4">Last Name</label> <input type="text"
								class="form-control" id="inputLName4" name="lName"
								placeholder="Last Name" required>
							<div class="valid-feedback">Good job!</div>
							<div class="invalid-feedback">Don't forget your Last Name.
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputEmail4">E-mail</label> <input type="email"
								class="form-control" id="inputEmail4" name="email"
								placeholder="E-mail" required>
							<div class="email-val" id="emailValidator"></div>
							<div class="valid-feedback">Great!</div>
							<div class="invalid-feedback">Please provide a valid
								e-mail.</div>
						</div>
						<div class="form-group col-md-6">
							<label for="inputPass4">Password</label> <input type="password"
								class="form-control" id="inputPass4" name="pass"
								placeholder="Password" required>
							<div class="valid-feedback">Super!</div>
							<div class="invalid-feedback">Please provide a valid
								password.</div>
						</div>
					</div>
					<div class="form-row">

						<div class="form-group col-md-6">
							<label for="inputPhone4">Phone</label> <input type="number"
								class="form-control" id="inputPhone4" name="phone"
								placeholder="Mobile or home" required>
							<div class="valid-feedback">Super!</div>
							<div class="invalid-feedback">Please provide a valid phone.
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="inputAddress">Address</label> <input type="text"
							class="form-control" id="inputAddress" name="address"
							placeholder="1234 Main St Apt. 3B" required>
						<div class="valid-feedback">So good!</div>
						<div class="invalid-feedback">Please provide a valid
							Address.</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputCity">City</label> <input type="text"
								class="form-control" id="inputCity" placeholder="City"
								name="city" required>
							<div class="valid-feedback">Right on!</div>
							<div class="invalid-feedback">Aren't you proud of your
								city?</div>
						</div>
						<div class="form-group col-md-4">
							<label for="inputCountry">Country</label> <input type="text"
								class="form-control" id="inputCountry" name="country"
								placeholder="Country" required>
							<div class="valid-feedback">We love your country!</div>
							<div class="invalid-feedback">Are forgetting something?</div>
						</div>
						<div class="form-group col-md-2">
							<label for="inputZip">Postal Code</label> <input type="text"
								class="form-control" id="inputZip" name="postalCode"
								placeholder="Postal Code" required>
							<div class="valid-feedback">yeah!</div>
							<div class="invalid-feedback">Last but not least.</div>
						</div>
					</div>
					<div class="mt-auto">
						<button type="submit" class="btn btn-primary btn-block mb-2"
							id="joinSubmitButton">Submit</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="Footer.jsp" flush="true" />
	<script src="JS/join.js"></script>


</body>
</html>