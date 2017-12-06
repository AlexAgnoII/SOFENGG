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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AdminService;
import service.PasswordAuthentication;
import service.StudentService;

/**
 * Servlet that handles everything related to email sending.
 */
@WebServlet(urlPatterns= {"/sendVerification", "/sendResetPassConfirm"})
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String SYSTEM_EMAIL = "sofenggproject@gmail.com";
	private static final String SYSTEM_PASSWORD = "Sofenggproj";
       

    public EmailServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch(request.getServletPath()) {
		   case "/sendVerification":sendVerificationEmail(request, response); break;
		   case "/sendResetPassConfirm":sendResetPassConfirm(request, response); break;
		   default: System.out.println("Inside EmailServlet: URL NOT FOUND.");
		}
		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

	private void sendResetPassConfirm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = (String) request.getAttribute("email");
		String token = (String) request.getAttribute("token");
		
		System.out.println("*********************** SEND RESET PASSWORD CONFIRM EMAIL******************************");
		System.out.println("Email: " + email);
		System.out.println("Token: " + token);
		
		String resultMessage = "";
		int port = request.getServerPort(); //get port of the server.
		String name = request.getServerName(); //get name of server.
		String generatedURL = "http://" + name + ":" + port +"/SOFENGG/resetPassword?reset=" + token;
		String generatedMsg = 	"Hi!\n\n"
				                + "If you did NOT issue this reset password request, please disregard this message."
								+ "\n\n"
								+ "However if you did, please click the link below to reset your password."
								+ "\n\n"
								+ generatedURL
								+ "\n\n"
								+"Thank you!";
		String subject = "Reset Password";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.user", SYSTEM_EMAIL);
		
		properties.put("mail.smtp.auth", "true"); 
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.password", SYSTEM_PASSWORD);
        
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");

		
		Session session = Session.getDefaultInstance(properties,  
			    new javax.mail.Authenticator() {  
					@Override
		      		protected javax.mail.PasswordAuthentication getPasswordAuthentication() {  
		      			return new javax.mail.PasswordAuthentication(SYSTEM_EMAIL, SYSTEM_PASSWORD);  
		      		}  
		    });
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SYSTEM_EMAIL));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject(subject);
			message.setContent(generatedMsg, "text/plain");
			
            Transport.send(message/*, username, password*/);
			resultMessage = "Message sent successfully!";
		} catch (MessagingException e) {
			resultMessage = "Unable to send message!";
			e.printStackTrace();
		} finally {
			request.setAttribute("Message", resultMessage);
			//response.sendRedirect("Verification.jsp");
		}
		
		System.out.println("Sending of reset password confirmation email done!");
		System.out.println("***************************************************************************************");
		response.getWriter().write("EXISTS");
	}
	
	
	private void sendVerificationEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//String host = "localhost";
		System.out.println("******************SENDING VERIFICATION********************");
		String email = (String) request.getAttribute("email");
		String newVerificationId = (String) request.getAttribute("verificationId");
		
		
		String resultMessage = "";
		int port = request.getServerPort(); //get port of the server.
		String name = request.getServerName(); //get name of server.
		String generatedURL = "http://" + name + ":" + port +"/SOFENGG/verification?verify=" + newVerificationId;
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
		properties.put("mail.smtp.user", SYSTEM_EMAIL);
		
		properties.put("mail.smtp.auth", "true"); 
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.password", SYSTEM_PASSWORD);
        
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");

		
		Session session = Session.getDefaultInstance(properties,  
			    new javax.mail.Authenticator() {  
					@Override
		      		protected javax.mail.PasswordAuthentication getPasswordAuthentication() {  
		      			return new javax.mail.PasswordAuthentication(SYSTEM_EMAIL, SYSTEM_PASSWORD);  
		      		}  
		    });
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SYSTEM_EMAIL));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject(subject);
			message.setContent(generatedMsg, "text/plain");
			
            Transport.send(message/*, username, password*/);
			resultMessage = "Message sent successfully!";
		} catch (MessagingException e) {
			resultMessage = "Unable to send message!";
			e.printStackTrace();
		} finally {
			request.setAttribute("Message", resultMessage);
			//response.sendRedirect("Verification.jsp");
		}
		
		System.out.println("sending done!");
		System.out.println("*****************************************************");
		response.getWriter().write("VALID-SIGNUP");
		
	}

}
