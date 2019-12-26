<!doctype html>
<html lang="en">
<head>
<title>Hotel Patagonia Confirmation</title>

<%
	String message = String.valueOf(request.getAttribute("message"));
	String[] strArray = message.split("\\.");

	//delete cache to prevent back button after logout
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>
</head>


<body>
	<jsp:include page="Header.jsp" flush="true" />
	<div class="bg-dark  p-4"></div>
	<div id="clouds"></div>
	<div class="confirmation">
		
		<h1><%=strArray[0]%></h1>
		<h3 class="brand" id="confirmation"><%=strArray[1]%></h3>
		<h5><%=strArray[2]%></h5>
		<a href="Index.jsp">return Home</a>
	</div>
	


	<canvas id="canvas">
	</canvas>
	<jsp:include page="Footer.jsp" flush="true" />

	<script src="JS/confirmation.js"></script>
</body>
</html>