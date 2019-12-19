<!doctype html>
<html lang="en">
<head>
<title>Find Reservation</title>
<%
String log_UN="";
if (session.getAttribute("username")!=null){
	
	log_UN= String.valueOf(session.getAttribute("username"));
} 

//delete cache to prevent back button after logout
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>
</head>

<body class="themeColor">
	<jsp:include page="Header.jsp" flush="true" />
	<div class="bg-dark  p-4"></div>
	<div id="main_content" class="tm-container clearfix px-0 ">
		
			
			<h4 class="mt-4">Let's Find your reservation!</h4>
			<hr >
			<form name="findBoo" class="d-flex needs-validation flex-column h-100"
			action="BookingExecuter" method="POST" novalidate>
			<div class="form-row d-flex">
			
				<div class="form-group col-md-5">
					<label for="inputBoo">Booking Number</label> <input type="number"
						class="form-control" id="inputBoo" name="bookingId"
						placeholder="Please enter your Booking Number" required>
					<div class="valid-feedback">Looks good!</div>
					<div class="invalid-feedback">This does not seem right.</div>
				</div>
				<div class="form-group col-md-5">
					<label for="inputEmail">E-mail</label> <input type="email"
						class="form-control" id="inputEmail" name="email"
						placeholder="The e-mail used for this reservation "
						value="<%=log_UN%>" required>
					<div class="valid-feedback">Good job!</div>
					<div class="invalid-feedback">mmm, is this your e-mail?.</div>
				</div>
				<div class="form-group col-md-2 align-self-end">
					<button type="button" id="buttonS"
						class="btn btn-outline-dark mr-0 btn-block"
						onclick="FindReservation()">Search</button>
				</div>
				
			</div>
		</form>
		<div id="target"></div>
	</div>

	<jsp:include page="Footer.jsp" flush="true" />
	<script src="JS/find.js"></script>
	

</body>
</html>