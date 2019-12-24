package Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DataBase.GuestDAO;

@WebServlet("/EmailValidator")
public class EmailValidator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailValidator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		PrintWriter out= response.getWriter();
		GuestDAO gd= new GuestDAO();
		int id=0;
		try {
			String email= String.valueOf(request.getParameter("email"));
			id=gd.getUserId(email);
		}catch (Exception e) {
			System.out.println("email available");
		}
		if(id>0) {
			out.println("<span class=\"text-danger text-sm\">"
						+"This e-mail is already in use"
						+"</span>");
		}else {
			out.print("<span class=\"text-success\">"
						+"This e-mail is available"
						+"</span>");
		}
	}
}
