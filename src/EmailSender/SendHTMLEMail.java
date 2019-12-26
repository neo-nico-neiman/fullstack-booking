package EmailSender;

import com.sun.mail.smtp.SMTPTransport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class SendHTMLEMail {

	// for example, smtp.mailgun.org
	private static final String SMTP_SERVER = "smtp.gmail.com";
	private static String USERNAME;
	private static String PASSWORD;
	private static String EMAIL_FROM;
	private static final String EMAIL_TO_CC = "";

	public void emailSender(String sub, String message, String EMAIL_TO) throws IOException {

		Properties prop = System.getProperties();
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", SMTP_SERVER); // optional, defined in SMTPTransport
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "587"); // default port 25

		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(prop, null);
		Message msg = new MimeMessage(session);

		try {
//        	//local version
//        	FileInputStream f = new FileInputStream("C:\\Users\\extra\\Google Drive\\COMIT\\Git\\BookingManagementInProgress\\BookingProgram\\src\\EmailSender\\emailCredentials.properties");
//        	//load the properties file
//        	Properties emailProp =  new Properties();
//        	emailProp.load(f);
//        	USERNAME = emailProp.getProperty("USERNAME");
//        	PASSWORD = emailProp.getProperty("PASSWORD");
//        	EMAIL_FROM = emailProp.getProperty("EMAIL_FROM");

			// heroku version
			USERNAME = System.getenv("USERNAME");
			PASSWORD = System.getenv("PASSWORD");
			EMAIL_FROM = System.getenv("EMAIL_FROM");

			// from
			msg.setFrom(new InternetAddress(EMAIL_FROM));

			// to
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_TO, false));

			// cc
			msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(EMAIL_TO_CC, false));

			// subject
			msg.setSubject(sub);

			// content
//            msg.setText(message);
			msg.setContent(message, "text/html");

			msg.setSentDate(new Date());

			// Get SMTPTransport
			SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

			// connect
			t.connect(SMTP_SERVER, USERNAME, PASSWORD);

			// send
			t.sendMessage(msg, msg.getAllRecipients());

			System.out.println("Response: " + t.getLastServerResponse());

			t.close();

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
