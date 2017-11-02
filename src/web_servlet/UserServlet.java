package web_servlet;

/**
 * This servlet handles Login, logout, and signup.
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.AdminService;
import service.StudentService;

@WebServlet(urlPatterns = {"/login",	
		                   "/signUp", 
		                   "/logout"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am called. (DoGet userServlet)");
		System.out.println();
		
		switch(request.getServletPath()) {
			//case "/relog": performRelogin(request, response); break;
			case "/logout": performLogout(request, response); break;
			default: System.out.println("ERROR(Inside userServlet *doGet*): url pattern doesn't match existing patterns.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am called. (DoPost userServlet)");
		
		switch(request.getServletPath()) {
			case "/login": performLogin(request, response); break;
			case "/signUp": performSignup(request, response); break;
			default: System.out.println("ERROR(Inside userServlet *doPost*): url pattern doesn't match existing patterns.");
		}
	}
	
	
	/**
	 * Performs logout.
	 * Kills the cookie and the session.
	 * @param request - request object from client
	 * @param response - response object returned to client
	 * @throws ServletException
	 * @throws IOException
	 */
	private void performLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("********************Perform Logout*************************");
		Cookie[] cookies;
		HttpSession session = request.getSession();
		
		//Kill session
		session.invalidate();
		
		//remove cookies
		cookies = request.getCookies();
		for (Cookie c : cookies) {
			if(c.getName().equals("USER")) {
				c.setMaxAge(0);
				response.addCookie(c);
			} else if(c.getName().equals("ADMIN")) {
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		
		//redirect to homepage.
		System.out.println("Cookie successfully removed!");
		response.sendRedirect("HomePage.jsp");
		System.out.println("************************************************\n");
	}
	
	/**
	 * performs login.
	 * Sends the user to inside the webpage if existing.
	 * If not existing, sends an error message which triggers a popup on webpage.
	 * @param request - request object from client
	 * @param response - response object returned to client
	 * @throws ServletException
	 * @throws IOException
	 */
	private void performLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email, password;
		HttpSession s = request.getSession();
		System.out.println("****************PerformLogin********************");
		
		//get email and pass
		email = request.getParameter("email");
		password = request.getParameter("password");
		
		System.out.println("Email: " + email);
		System.out.println("Password: "+password);
		
		//Validate.
		if(StudentService.validateStudent(email, password)) { 
			String userID = StudentService.getStudentID(email);
			//set session attribute
			s.setAttribute("UN", userID); 
			System.out.println("Session(UN): " + s.getAttribute("UN"));
			
				
			//This generates the cookie.
			Cookie theCookie;
			theCookie = new Cookie("USER", userID); 
			theCookie.setMaxAge(604800); //1 week expirey.
		
			//Checking
			System.out.println("Cookie placed: " + theCookie.getName());
			System.out.println("Cookie value: " + theCookie.getValue());

			//Add cookie
			response.addCookie(theCookie);

			//Redirect inside website
			//response.sendRedirect("UserHomePage.jsp");
			//response.sendRedirect("viewByStudent"); //redirect to view profile.jsp
			response.getWriter().write("PASS-LOGIN-STUDENT");
			
		} else if(AdminService.validateAdmin(email, password)) { 
			String userID = AdminService.getAdminID(email);
			//set session attribute
			s.setAttribute("UN", userID); 
			System.out.println("Session(UN): " + s.getAttribute("UN"));
			
				
			//This generates the cookie.
			Cookie theCookie;
			theCookie = new Cookie("ADMIN", userID); 
			theCookie.setMaxAge(604800); //1 week expirey.
				
			//Checking
			System.out.println("Cookie placed: " + theCookie.getName());
			System.out.println("Cookie value: " + theCookie.getValue());

			//Add cookie
			response.addCookie(theCookie);

			//Redirect inside website
			//response.sendRedirect("AdminHomePage.jsp");
			response.getWriter().write("PASS-LOGIN-ADMIN");
			
		}
		
		else {
			/*Send error*/
			System.out.println("inValid (User not found)");
			//send error code.
			// TODO front end notif
			response.getWriter().write("FAIL-LOGIN");

		}
		System.out.println("****************************************\n");
		
	}
	
	/**
	 * performs sign up.
	 * Adds the user to the database
	 * @param request - request object from client
	 * @param response - response object returned to client
	 * @throws ServletException
	 * @throws IOException
	 */
	private void performSignup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//Forwards the work to dataServlet url pattern "add"
		request.getRequestDispatcher("add").forward(request, response);
	}


}
