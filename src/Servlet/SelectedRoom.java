package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet that process and forward the Romm Number and Dates selected for
 * reservation
 */
@WebServlet("/SelectedRoom")
public class SelectedRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectedRoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		if (session.getAttribute("rNum") != null) {
			/*
			 * in case that customer change is mind and want to make reservation based on
			 * different parameters, then all parameters are removed from session
			 */
			session.removeAttribute("rNum");
			session.removeAttribute("cIn");
			session.removeAttribute("cOut");
			session.removeAttribute("price");
			session.removeAttribute("rDescription");
			session.removeAttribute("rDetails");
			session.removeAttribute("img");
			session.removeAttribute("totalDays");
			session.removeAttribute("numGuest");
			session.removeAttribute("categ");
			session.removeAttribute("tax");
			session.removeAttribute("total");
		}

		Object rNum = request.getParameter("rNum");
		Object cIn = request.getParameter("cIn");
		Object cOut = request.getParameter("cOut");
		double price = Double.parseDouble(String.valueOf(request.getParameter("price")));
		Object rDescription = request.getParameter("rDescription");
		Object rDetails = request.getParameter("rDetails");
		Object img = request.getParameter("img");

		// get total days of reservation
		String dateIn = String.valueOf(cIn);
		String dateOut = String.valueOf(cOut);
		LocalDate cI = Date.valueOf(dateIn).toLocalDate();
		LocalDate cO = Date.valueOf(dateOut).toLocalDate();
		Period tDays = Period.between(cI, cO);
		int days = tDays.getDays();
		int months = tDays.getMonths() * 30;
		int years = tDays.getYears() * 365;
		int totalDays = days + months + years;
		int numberOfGuest = Integer.parseUnsignedInt(request.getParameter("numGuest"));
		String categ = String.valueOf(request.getParameter("categ"));
		DecimalFormat df = new DecimalFormat("#.##");
		double tax = price * 0.12;
		double totalTax = tax * totalDays;
		String taxFormated = df.format(totalTax);
		double subtotal = (price + tax) * totalDays;
		String total = df.format(subtotal);

		/*
		 * add all values to session to allow user login on bookingRoom.jsp without
		 * loosing all selected values
		 */
		session.setAttribute("rNum", rNum);
		session.setAttribute("cIn", cIn);
		session.setAttribute("cOut", cOut);
		session.setAttribute("price", price);
		session.setAttribute("rDescription", rDescription);
		session.setAttribute("rDetails", rDetails);
		session.setAttribute("img", img);
		session.setAttribute("totalDays", totalDays);
		session.setAttribute("numGuest", numberOfGuest);
		session.setAttribute("categ", categ);
		session.setAttribute("tax", taxFormated);
		session.setAttribute("total", total);

		response.sendRedirect("BookingRoom.jsp");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
