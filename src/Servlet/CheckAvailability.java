package Servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DataBase.BookingDao;
import Program.Room;

@WebServlet("/AvailabilityChecker")
public class CheckAvailability extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckAvailability() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	 protected void doPost(HttpServletRequest request, 
	            HttpServletResponse response) throws ServletException, 
	            IOException {
	    	Date checkIn=null;
	    	Date checkOut=null;
	    	int numberOfGuest=0;
	    	
	    	try {
	    	checkIn = Date.valueOf(request.getParameter("checkIn"));
			checkOut = Date.valueOf(request.getParameter("checkOut"));
			numberOfGuest = Integer.parseInt(request.getParameter("numberOfGuest"));
	    	} catch (Exception e) {
	    		System.out.println(e);
	    	}
			BookingDao b1= new BookingDao();
			ArrayList <Room> rooms= b1.checkAvailabilityByGuestAndDate(checkIn, checkOut, numberOfGuest);
			PrintWriter out = response.getWriter();
			
	
			if (rooms==null) {
				
				out.println("<div class=\"room_container clearfix\">");
				out.println("<h6> There are no rooms available for the selected dates</h6>");
				out.println("<p href=\"index.jsp\">Please select different dates</p>");
				out.println("</div>");
				out.close();
			}else {
				out.println("<!DOCTYPE html>"); 
				out.println("<body>"); 
				for (int i=0; i<rooms.size(); i++) {
				roomViewer( checkIn, checkOut ,i, rooms, out, numberOfGuest);
				}
				out.println("</body>"); 
				out.close();
			}
	    }
	  
	         
	   
	    
	    private void roomViewer (Date checkIn, Date checkOut, int arrayLocation, ArrayList <Room> rooms, PrintWriter out, int numberOfGuest) throws ServletException, 
        IOException {
	    	 String [] options = new String [] {"Standard", "Standard +", "Premium"};
	    	 

	    	 String rDescription= rooms.get(arrayLocation).getRoomDescription();
	    	 String rDetails = rooms.get(arrayLocation).getRoomDetails();
	    	 String [] detailsArray = rDetails.split("\\.");
	    	 double price= rooms.get(arrayLocation).getPricePerNight();
	    	 int roomNumber= rooms.get(arrayLocation).getRoomNumber();
	    	 String img= rooms.get(arrayLocation).getImgURL();
	    	
	 		out.println("<div class=\"card mb-3\" style=\"max-width: 100%;\">");
	 		out.println("<div class=\"row no-gutters\">");
	 		out.println("<div class=\"col-md-4\">");  
	 		out.println("<img src=\""+img+"\" class=\"card-img\" alt=\"Room Image\"/>");    
	 		out.println("</div>");
	 		out.println("<div class=\"col-md-8 \" style=\"max-width: 100%;\">");  
	 		out.println("<div class=\"card-body d-flex flex-column\" style=\"min-height: 100%;\" >");     
	 		out.println("<h3 class=\"card-title\">"+options[arrayLocation]+"</h3>");    
	 		out.println("<p class=\"card-text\">"+rDescription+"</p>");
	 		out.println("<div class=\"ml-4 \" ><ul><li>"+detailsArray[0]+"</li>");
	 		out.println("<li>"+detailsArray[1]+"</li></ul></div>");
	 		out.println("<h5>Price per Night $ "+price+"</h5>");

	 		out.println("<form name=\"select\" class=\"mt-auto\" action=\"SelectedRoom\" method=\"post\">");
	 		out.println("<input name=\"categ\" type=\"hidden\" value=\""+options[arrayLocation]+"\"/>");
	 		out.println("<input name=\"img\" type=\"hidden\" value=\""+img+"\"/>");
	 		out.println("<input name=\"price\" type=\"hidden\" value=\""+price+"\"/>");
	 		out.println("<input name=\"rNum\" type=\"hidden\" value=\""+roomNumber+"\"/>");
	 		out.println("<input name=\"cIn\" type=\"hidden\" value=\""+checkIn+"\"/>");
	 		out.println("<input name=\"cOut\" type=\"hidden\" value=\""+checkOut+"\"/>");
	 		out.println("<input name=\"numGuest\" type=\"hidden\" value=\""+numberOfGuest+"\"/>");
	 		out.println("<input name=\"rDescription\" type=\"hidden\" value=\""+rDescription+"\"/>");
	 		out.println("<input name=\"rDetails\" type=\"hidden\" value=\""+rDetails+"\"/>");
	 		out.println("<button type=\"submit\" class=\"btn btn-outline-warning btn-md btn-block \">Book</button>");
	 		out.println("</form>");
	 		out.println("</div>");
	 		out.println("</div>");
	 		out.println("</div>");
	 		out.print("</div>");		
	}




}
