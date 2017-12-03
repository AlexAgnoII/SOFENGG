package web_servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.AdminService;
import service.PasswordAuthentication;
import service.StudentService;

/**
 * Servlet implementation class PasswordServlet
 */
@WebServlet(urlPatterns= {"/resetPassCheckEmail",
		                  "/resetPassword",  //redirecting to reset password page
		                  "/resetPasswordAction",//action itself to update DB of the new password (Forgotten). 
		                  "/checkPasswordMatch",
		                  "/changePassowrd"}) //action itself to update DB of the new password (Changing). 
public class PasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch(request.getServletPath()) {
		    case "/resetPassword": resetPassword(request, response); break;
		    default: System.out.println("(GET)PasswordServlet error (URL NOT FOUND)");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch(request.getServletPath()) {
		    case "/resetPassCheckEmail": resetPassCheckEmail(request, response); break;
		    case "/resetPasswordAction":
		    case "/changePassword": updatePassword(request, response); break;
		    case "/checkPasswordMatch": checkPasswordMatch(request, response); break;
		    default: System.out.println("(POST)PasswordServlet error (URL NOT FOUND)");
		}
	}
	
	
	/**
	 * Check if given password matches the currently logged in user's password.
	 * @param request
	 * @param response
	 */
	private void checkPasswordMatch(HttpServletRequest request, HttpServletResponse response) {
		String password = request.getParameter("password");
		boolean user = false, 
				admin = false;
		
		String message = "WRONG";
		System.out.println("********* CHECK PASSWORD MATCH ********************");
		System.out.println("Entered password: " + password);
		
		//Check whether user is admin OR student
		Cookie[] cookieList = request.getCookies();
		if(cookieList != null) {
			for(Cookie c : cookieList) {	
				//Its a user!
				if(c.getName().equals("USER")) {
					System.out.println("USER Cookie found!");
					user = true; //if it exists, proceed.
				//Its an admin!
				} else if(c.getName().equals("ADMIN")) {
					System.out.println("ADMIN Cookie found!");
					admin = true;
				}
				else {
					System.out.println("ERROR, NO USER/ADMIN FOUND");
				}
			}
		}
		else {
			System.out.println("Cookielist is empty.");
		}
		
		//Check whether given password is equal to what the useer has entered
		//User found.
		if(user) {
			
		}
		
		//Admin found.
		else if (admin) {
			
		}
		
		else {
			System.out.println("No user found ERROR");
		}
		
		
		
		
		
		
		System.out.println("***************************************************");
		
	}

	/**
	 * updates the password, given the token and the new password.
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updatePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String password = request.getParameter("password");
		String token = request.getParameter("token");
		String email = "NONE";
		
		email = StudentService.convertTokenToEmail(token);
		
		//Remove session attribute "U"
		session.removeAttribute("U");
		
		//Success for student
		if(!email.equals("NONE")) {
			System.out.println("Success!");
			System.out.println("Email: " + email);
			
			StudentService.resetPassword(email, password);
		}
		
		//Fail for student, go for admin..
		else  {
			System.out.println("Did not exist in user, checking admin...");
			email = AdminService.convertTokenToEmail(token);
			
			//Success for admin
			if(!email.equals("NONE")) {
				System.out.println("Success in admin!!");
				System.out.println("Email: " + email);
				AdminService.resetPassword(email, password);
				
			}
			
			//Something went wrong.
			else {
				System.out.println("problem encountered. (PASSWORD SERVLET, RESET PASSWORD FUNC");
			}
		}
		
		response.getWriter().write("SUCCESS-CHANGE");
		
	}
	
	/**
	 * Redirects to change password page.
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String token = request.getParameter("reset");
		
		System.out.println("*****************RESET PASSWORD *******************************");
		System.out.println("Token: " + token);
	
		
		session.setAttribute("U", token);
		response.sendRedirect("ResetPassword.jsp");
		System.out.println("***************************************************************");
	}

	
	/**
	 * Performs checking if email exists for ressetting password.
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void resetPassCheckEmail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("email");
		
		
		System.out.println("**************RESET PASS CHECK EMAIL ******************");
		System.out.println("New version.");
		System.out.println("Email: " + email);
		//Check student OR Admin
		if(StudentService.isExisiting(email) || AdminService.isExisting(email)) {
			System.out.println("Email exists!");
			//Encode email.
			PasswordAuthentication p = new PasswordAuthentication();
			String token = p.hash(email.toCharArray());
			System.out.println("Email code: " + token);
			System.out.println(p.authenticate(email.toCharArray(), token));
			
			
			request.setAttribute("email", email);
			request.setAttribute("token", token);
			
			//Send comfirmation email
			request.getRequestDispatcher("sendResetPassConfirm").forward(request, response);

		}
			
		else {
			System.out.println("Email does not exist!");
			response.getWriter().write("DOES-NOT-EXIST");
		}
		System.out.println("********************************************************");
	}

}
