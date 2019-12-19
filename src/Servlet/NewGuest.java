package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataBase.GuestDAO;
import Program.Guest;
import Program.User;


/**
 * Servlet implementation class NewGuest
 */
@WebServlet("/NewGuest")
public class NewGuest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewGuest() {
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
		String firstName="";
		String lastName="";
		String email="";
		String pass="";
		String address="";
		String phone="";
		String country="";
		String city="";
		String postalCode="";
				
		User newGuest;
		int userId;
		
		try {
		 firstName= request.getParameter("fName");
		 lastName= request.getParameter("lName");
		 email = request.getParameter("email");
		 pass= request.getParameter("pass");
		 address= request.getParameter("address");
		 phone= request.getParameter("phone");
		 country= request.getParameter("country");
		 city= request.getParameter("city");
		 postalCode= request.getParameter("postalCode");
		
		
		newGuest = new Guest(email, pass);
		newGuest.setFirstName(firstName);
		newGuest.setLastName(lastName);
		newGuest.setStreetName(address);
		newGuest.setPhone(phone);
		newGuest.setCountry(country);
		newGuest.setCity(city);
		newGuest.setPostalCode(postalCode);
		newGuest.setEmail(email);
		
		GuestDAO newGD= new GuestDAO();
		 userId=newGD.addUser(newGuest);
		} catch (Exception e) {
			userId=0;
		}
		if (userId!=0) {
			
			//if succesfull create session for user
			HttpSession session = request.getSession();
			session.removeAttribute("error");
			session.setAttribute("username", email);
			session.setAttribute("fName", firstName);
			session.setAttribute("lName", lastName);
			session.setAttribute("phone", phone);
			session.setAttribute("email", email);
			session.setAttribute("address", address);
			session.setAttribute("city", city);
			session.setAttribute("postalCode", postalCode);
			session.setAttribute("country", country);
			
			response.sendRedirect("Index.jsp");
			
		} else {
			String error= "e";
			HttpSession session = request.getSession();
			session.setAttribute("error", error);
			response.sendRedirect("Join.jsp");
		}
		
	}

}
