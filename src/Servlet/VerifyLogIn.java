package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataBase.GuestDAO;
import Program.User;

/**
 * Servlet implementation class VerifyLogIn
 */
@WebServlet("/VerifyLogIn")
public class VerifyLogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerifyLogIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uName = request.getParameter("uN");
		String pass = request.getParameter("password");
		String originPage = request.getParameter("originURL");
		GuestDAO gD = new GuestDAO();
		User user = null;
		boolean verifyPass = false;
		// get user from dataBase that contains the declared uName
		try {
			user = gD.getUsername(uName);
			// Compare the given password against the password stored in db for this
			// username
			verifyPass = user.verifyPassword(pass);
		} catch (Exception e) {
			verifyPass = false;
			System.out.println(e);
		}

		if (verifyPass) {
			user = gD.getUser(gD.getUserId(uName));

			String fName = user.getFirstName();
			String lName = user.getLastName();
			String phone = user.getPhone();
			String email = uName;
			String address = user.getStreetName();
			String city = user.getCity();
			String postalCode = user.getPostalCode();
			String country = user.getCountry();

			HttpSession session = request.getSession();
			session.removeAttribute("fail");
			session.setAttribute("username", uName);
			session.setAttribute("fName", fName);
			session.setAttribute("lName", lName);
			session.setAttribute("phone", phone);
			session.setAttribute("email", email);
			session.setAttribute("address", address);
			session.setAttribute("city", city);
			session.setAttribute("postalCode", postalCode);
			session.setAttribute("country", country);

			response.sendRedirect(originPage);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("fail", 1);
			response.sendRedirect(originPage);
		}
	}

}
