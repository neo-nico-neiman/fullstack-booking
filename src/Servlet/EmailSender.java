package Servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import EmailSender.SendHTMLEMail;
/**
 * Servlet implementation class EmailSender
 */
@WebServlet("/EmailSender")
public class EmailSender extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailSender() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sub = request.getParameter("sub");
		String message = request.getParameter("message");
		String destinationEmail = request.getParameter("emailTo");
		SendHTMLEMail sm = new SendHTMLEMail();
		sm.emailSender(sub, message, destinationEmail);
	}
}
