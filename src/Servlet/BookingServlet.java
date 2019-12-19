package Servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.BookingDao;
import DataBase.GuestDAO;
import Program.Booking;
import Program.Guest;
import Program.User;

/**
 * Servlet implementation class Booking
 */
@WebServlet("/Booking")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
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
		
		Booking b= new Booking();
		
		BookingDao bd = new BookingDao();
		GuestDAO gd = new GuestDAO();
		User u= new Guest();
		String fName= String.valueOf(request.getParameter("fName"));
		String lName= String.valueOf(request.getParameter("lName"));
		String email= String.valueOf(request.getParameter("email"));
		String phone= String.valueOf(request.getParameter("phone"));
		int rNum= Integer.parseInt(String.valueOf(request.getParameter("rNum")));
		Date cIn= Date.valueOf(request.getParameter("cIn"));
		Date cOut= Date.valueOf(request.getParameter("cOut"));
		
		
		try {
		
		int id=gd.getUserId(email);
		
		if (id==0) {
			u.setFirstName(fName);
			u.setLastName(lName);
			u.setEmail(email);
			u.setPhone(phone);
			
				id=gd.addUser(u);
			
			u.setUserId(id);
			System.out.println(id);
		} else {
			u=gd.getUser(id);
		}
		b.setIdExistingUser(id);
		b.setNewUser(u);
		b.setCheckIn(cIn);
		b.setCheckOut(cOut);
		b.setRoomNumber(rNum);
		bd.addBooking(b);
		
		response.sendRedirect("Success.jsp");

	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}}
