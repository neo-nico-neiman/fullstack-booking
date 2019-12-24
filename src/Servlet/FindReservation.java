package Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DataBase.BookingDao;
import Program.Booking;

/**
 * Servlet implementation class FindReservation
 */
@WebServlet("/FindReservation")
public class FindReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindReservation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		
		String email= request.getParameter("email");
		int bId=Integer.parseInt(String.valueOf(request.getParameter("bookingId")));
		
		BookingDao bd= new BookingDao();
		Booking booking=bd.getBooking(bId);
		boolean validator;
		try {
			validator= booking.getUser().getEmail().equals(email);
		} catch (NullPointerException e) {
			validator= false;
		}
	
		if (validator) {
			String message="We found it!</br>Please see all the details below.";
			out.println("<h6 class=\" text-success text-center\"> "+message+"</h6>");
			out.println("<table class=\"table table-hover\">");
			out.println("<tbody>");
			out.println("<tr>");
			out.println("<th scope=\"row\">First Name</th>");
			out.println("<td>"+booking.getUser().getFirstName()+"</td>");
			out.println("<th scope=\"row\">Last Name</th>");
			out.println("<td>"+booking.getUser().getLastName()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th scope=\"row\">Booking ID</th>");
			out.println("<td>"+bId+"</td>");
			out.println("<th scope=\"row\">Check-in</th>");
			out.println("<td>"+booking.getCheckIn()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th scope=\"row\">Room Number</th>");
			out.println("<td>"+booking.getRoom().getRoomNumber()+"</td>");
			out.println("<th scope=\"row\">Check-out</th>");
			out.println("<td>"+booking.getCheckOut()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th scope=\"row\">Price Per Night</th>");
			out.println("<td>$"+booking.getPPN()+"</td>");
			out.println("<th scope=\"row\">Total Nights</th>");
			out.println("<td>"+booking.getNights()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th scope=\"row\">Taxes &amp Fees</th>");
			out.println("<td>$"+booking.getTax()+"</td>");
			out.println("<th scope=\"row\">Total</th>");
			out.println("<td>$"+booking.getTotal()+"</td>");
			out.println("</tr>");
			out.println("</tbody>");
			out.println("</table>");	
			out.close();
								
		}else {
			String message="There is no booking matching this info in our database.";
			out.println("<h6 class=\" text-danger text-center \"> "+message+"</h6>");
			out.close();
		}	
	}
}
