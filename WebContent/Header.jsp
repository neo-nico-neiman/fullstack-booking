<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<link rel="shortcut icon" href="favicon.ico" />
<link rel="stylesheet" type="text/css" href="CSS/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="CSS/main.css" />
<link rel="stylesheet" type="text/css" href="CSS/jquery-ui.css" />

<link
	href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css?family=Capriola&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Quattrocento&display=swap"
	rel="stylesheet">

</head>
<body>
	<header class="themeColor-nav sticky-top" id="start">
		<%
			//delete cache to prevent back button after logout
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		%>
		<div class="tm-container ">
			<div class="form-row ">
				<div class="col-8 ">
					<nav class="navbar navbar-expand-lg navbar-light px-0 py-2 py-lg-0">
						<a class="navbar-brand brand" id="logo"
							onclick="location.href = 'Index.jsp';"> <img
							src="img/patagonia_logo.png" width="30" height="30"
							class="d-inline-block align-top order-first order-sm-last order-lg-first "
							alt="logo"> Hotel Patagonia
						</a>
						<button
							class="navbar-toggler order-last order-sm-first order-lg-last "
							type="button" data-toggle="collapse"
							data-target="#navbarNavAltMarkup"
							aria-controls="navbarNavAltMarkup" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
							<div class="navbar-nav">
								<a class="nav-item nav-link " href="Index.jsp#start">Reservations</a>
								<a class="nav-item nav-link " href="Rooms.jsp">Rooms</a> <a
									class="nav-item nav-link" href="FindReservation.jsp">My
									Bookings</a> <a class="nav-item nav-link" href="Contact.jsp">Contact</a>

							</div>
						</div>
					</nav>
				</div>

				<div class="col-4 mr-0 float-right">

					<%
						if (session.getAttribute("username") == null) {
							/*if login failed VerifyLogin.java sends the value "fail"=1
							through session
							*/
							String fail = String.valueOf(session.getAttribute("fail"));
							String message = "";
							if (fail.equals("1")) {
								message = "Wrong credentials ";
								session.removeAttribute("fail");
							}
							//else, empty message will be displayed 
							out.println("<div class=\"tm-log float-right text-right\"><span class=\"text-danger \">" 
									+ message
									+ "</span><button type=\"button\" "
									+"class=\"btn btn-primary mt-2 mt-sm-0 float-right float-md-none\""
									+" style=\"min-width: 55px\" "
									+"onclick=\"location.href='Join.jsp'\">Join</button>");
							out.println(
									"<button type=\"button\" "
									+"class=\"btn btn-outline-secondary "
									+"ml-0 ml-sm-2 mr-0 mt-3 mb-2 mt-sm-0 mb-sm-0 "
									+"float-right float-md-none\" "
									+"data-toggle=\"modal\" data-target=\"#logInModal\">"
									+"Log in</button>");
							out.println("</div>");

						} else {
							String uname = String.valueOf(session.getAttribute("username"));
							String pass = String.valueOf(session.getAttribute("password"));
							out.println("<div class=\"tm-log float-right text-right\">Hi "
									+"<p class=\"text-warning d-inline\">");
							out.print(uname);
							out.println(
									"</p><form action=\"LogOut\" method=\"post\""
									+"class=\"float-right\"><button type=\"submit\""
									+" class=\"btn btn-outline-secondary ml-2 float-right\">"
									+"Log out</button></form></div>");

						}
					%>
				</div>
			</div>
		</div>

	</header>


</body>
<div class="modal fade" id="logInModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">

		<div class="modal-content">
			<form action="VerifyLogIn" method="post">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Log in</h5>

				</div>
				<div class="modal-body">
					<div class="col-auto">
						<label class="sr-only" for="inlineFormInputGroup">Username</label>
						<div class="input-group mb-2">
							<input type="email" name="uN" class="form-control"
								id="inlineFormInputGroup" autocomplete="off"
								placeholder="Username">
						</div>
					</div>
					<div class="col-auto">
						<label class="sr-only" for="inlineFormInput">Password</label> <input
							type="password" name="password" class="form-control mb-2"
							id="inlineFormInput" autocomplete="on" placeholder="Password">

					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Log in</button>
					<input name="originURL" value="<%=request.getRequestURL()%>"
						type="hidden">

				</div>
			</form>
		</div>
	</div>
</div>
</html>