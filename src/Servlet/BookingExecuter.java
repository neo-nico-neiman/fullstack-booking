package Servlet;

import java.io.IOException;

import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataBase.BookingDao;
import DataBase.GuestDAO;
import EmailSender.SendHTMLEMail;
import Program.Booking;
import Program.Guest;
import Program.User;

/**
 * Servlet implementation class BookingExecuter
 */
@WebServlet("/BookingExecuter")
public class BookingExecuter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookingExecuter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int userId;
		GuestDAO newGD = new GuestDAO();
		User newGuest = new Guest();
		String email;
		String firstName;
		String lastName;
		if (session.getAttribute("username") == null) {

			firstName = request.getParameter("fName");
			lastName = request.getParameter("lName");
			email = request.getParameter("email");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String country = request.getParameter("country");
			String city = request.getParameter("city");
			String postalCode = request.getParameter("postalCode");

			newGuest.setFirstName(firstName);
			newGuest.setLastName(lastName);
			newGuest.setEmail(email);
			newGuest.setStreetName(address);
			newGuest.setPhone(phone);
			newGuest.setCountry(country);
			newGuest.setCity(city);
			newGuest.setPostalCode(postalCode);

			userId = newGD.addUser(newGuest);

		} else {
			email = String.valueOf(session.getAttribute("username"));
			userId = newGD.getUserId(email);
			firstName = newGD.getUser(userId).getFirstName();
			lastName = newGD.getUser(userId).getLastName();
		}

		int roomNumber;
		Date checkIn;
		Date checkOut;
		int nights;
		double ppn;
		double tax;
		double total;

		try {
			roomNumber = Integer.parseInt((String) request.getParameter(("roomNumberSelected")));
			checkIn = Date.valueOf(request.getParameter("checkInForRoom"));
			checkOut = Date.valueOf(request.getParameter("checkOutForRoom"));
			nights = Integer.parseInt(String.valueOf(request.getParameter("nights")));
			ppn = Double.parseDouble(String.valueOf(request.getParameter("ppn")));
			tax = Double.parseDouble(String.valueOf(request.getParameter("tax")));
			total = Double.parseDouble(String.valueOf(request.getParameter("total")));
		} catch (NumberFormatException e) {
			roomNumber = 0;
			checkIn = null;
			checkOut = null;
			nights = 0;
			ppn = 0;
			tax = 0;
			total = 0;
			System.out.println(e);
		}

		Booking newBooking = new Booking();
		newBooking.setCheckIn(checkIn);
		newBooking.setCheckOut(checkOut);
		newBooking.setIdExistingUser(userId);
		newBooking.setRoomNumber(roomNumber);
		newBooking.setNights(nights);
		newBooking.setPPN(ppn);
		newBooking.setTax(tax);
		newBooking.setTotal(total);
		BookingDao booking = new BookingDao();
		int bookingId = 0;
		// Additional validator to avoid reservation on room that is taken for given
		// dates
		boolean validator = booking.checkRoomAvailability(roomNumber, checkIn, checkOut);
		if (validator) {
			bookingId = booking.addBooking(newBooking);
		}

		String sub;
		String messageBody;
		String message;
		if (bookingId != 0) {
			sub = "Congrats! Your booking has been successful!";
			messageBody = "<div>Thank you<i><b> " + firstName + " " + lastName + " </b></i>for booking your stay at Patagonia Hotel!<br> \r\n"
					+ "\r\n<h3>Here are some essential details for you to save:\r\n</h3>" + "<b>Your booking Id: </b>" + bookingId
					+"\r\n<br><b>Check-In: </b>"+checkIn
					+"\r\n<br><b>Check-Out: </b>"+checkOut
					+"<br><b>Number of Nights: </b>"+nights 
					+"\r\n<br><b>Price per night: </b>"+ppn 
					+"\r\n<br><b>Tax: </b>"+tax 
					+"\r\n<br><b>Total: </b>"+total
					+"\r\n<h3>If you need to contact us, here is our information:</h3>"
					+ "Phone number: 1-204-649-8572\r\n" 
					+ "<br>Address: 123 Main St, Patagonia.\r\n"
					+ "<br>Looking forward to having you here!\r\n" 
					+ "<br><br>Best regards, \r\n" + "<br>Eduardo Rojas.\r\n"
					+ "<br>Front Desk Manager\r\n" + "<br>Patagonia Hotel"
					+ "</div>";
			message = "Your booking has been successful. Booking Id: " + bookingId
					+ ". We sent you a confirmation e-mail with all the details";
			SendHTMLEMail confimationE = new SendHTMLEMail();
			confimationE.emailSender(sub, messageBody, email);
		} else {
			message = "Oh no!. Something went wrong.Please contact our support center or call 1-800-help";
		}

		request.setAttribute("message", message);
		request.getRequestDispatcher("Confirmation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
