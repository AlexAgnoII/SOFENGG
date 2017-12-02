package web_servlet;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that handles everything related to email sending.
 */
@WebServlet(urlPatterns= {"/sendVerification", "/send"})
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String SOFFENG_EMAIL = "sofenggproject@gmail.com";
	private static final String SOFFENG_PASS = "Sofenggproj";
       

    public EmailServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		switch(request.getServletPath()) {
		   case "/sendVerification":sendVerificationEmail(request, response); break;
		   default: System.out.println("Inside EmailServlet: URL NOT FOUND.");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	private void sendVerificationEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//String host = "localhost";
		System.out.println("******************SENDING VERIFICATION********************");
		String username = (String) request.getAttribute("email");
		String password = (String) request.getAttribute("password");
		String newVerificationId = (String) request.getAttribute("verificationId");
		
		
		String resultMessage = "";
		int port = request.getServerPort(); //get port of the server.
		String name = request.getServerName(); //get name of server.
		String generatedURL = "http://" + name + ":" + port +"/SOFENGG/verification?verify=" + newVerificationId;
		String from = SOFFENG_EMAIL;
		String pass = SOFFENG_PASS;
		String generatedMsg = 	"Hi!"
								+ "\n\n"
								+ "Please click the link below to activate/confirm your account."
								+ "\n\n"
								+ generatedURL
								+ "\n\n"
								+"Thank you!";
		String subject = "Account Confirmation";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.user", SOFFENG_EMAIL);
		
		properties.put("mail.smtp.auth", "true"); 
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.password", SOFFENG_PASS);
        
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");

		
		Session session = Session.getDefaultInstance(properties,  
			    new javax.mail.Authenticator() {  
					@Override
		      		protected javax.mail.PasswordAuthentication getPasswordAuthentication() {  
		      			return new javax.mail.PasswordAuthentication(SOFFENG_EMAIL, SOFFENG_PASS);  
		      		}  
		    });
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SOFFENG_EMAIL));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(username));
			message.setSubject(subject);
			message.setContent(generatedMsg, "text/plain");
			
            Transport.send(message, username, password);
			resultMessage = "Message sent successfully!";
		} catch (MessagingException e) {
			resultMessage = "Unable to send message!";
			e.printStackTrace();
		} finally {
			request.setAttribute("Message", resultMessage);
			//response.sendRedirect("Verification.jsp");
		}
		
		System.out.println("*****************************************************");
		response.getWriter().write("VALID-SIGNUP");
		
	}

}
