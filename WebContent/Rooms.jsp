
<!DOCTYPE html>
<html>
<head>
<title>Rooms Hotel Patagonia</title>

<%@page import="DataBase.RoomDAO"%>
<%@page import="Program.Room"%>
<%@page import="java.util.ArrayList"%>
<%
	//delete cache to prevent back button after logout
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>

</head>
<body class="themeColor">
	<jsp:include page="Header.jsp" flush="true" />
	<div class="bg-dark  p-4 mb-3"></div>
	<div class="tm-container">
		<div class="w-100">
			<h4>Our Rooms</h4>
		</div>
		<%
			ArrayList<Room> roomArray = new ArrayList<Room>();
			RoomDAO rd = new RoomDAO();
			Room r = new Room();
			try {
				for (int i = 101; i < 104; i++) {
					r = rd.getRoom(i);
					roomArray.add(r);
				}

			} catch (NullPointerException e) {
				r = null;
				roomArray.add(r);
				System.out.println(e);
			}

			for (int i = 0; i < roomArray.size(); i++) {
				r = roomArray.get(i);
				String rDetails = r.getRoomDetails();
				String[] detArray = rDetails.split("\\.");
				out.println("<div class=\"card mb-3 mw-100 \">");
				out.println("<div class=\"row no-gutters\">");
				out.println("<div class=\"col-md-4\">");
				out.println("<img src=\"" + r.getImgURL() + "\" class=\"card-img hotelImg\" alt=\"Room Image\"/>");
				out.println("</div>");
				out.println("<div class=\"col-md-8 mw-100\" >");
				out.println("<div class=\"card-body d-flex flex-column\" style=\"min-height: 100%;\" >");
				out.println("<h3 class=\"card-title text-capitalize\">" + r.getRoomCategoryName() + "</h3>");
				out.println("<p class=\"card-text\">" + r.getRoomDescription() + "</p>");
				out.println("<div class=\"ml-4 \" ><ul><li>" + detArray[0] + "</li>");
				out.println("<li>" + detArray[1] + "</li></ul></div>");
				out.println("<h5>Price per Night $ " + r.getPricePerNight() + "</h5>");
				out.println("</div>");
				out.println("</div>");
				out.println("</div>");
				out.print("</div>");
			}
		%>
	</div>

	<jsp:include page="Footer.jsp" flush="true" />
	<script src="JS/rooms.js"></script>

</body>
</html>