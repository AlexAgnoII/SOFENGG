package web_servlet;

/**
 * This servlet handles Login, logout, relogin, and signup.
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;

//Important note: Temporarily removed relog in this servlet (Switched to authentication).
@WebServlet(urlPatterns = {"/login",	
		                   "/signUp", 
		                   //"/relog", 
		                   "/logout"}
)

public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public userServlet() {
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
	 * Performs relogin.
	 * If the cookie exists, user will be relogin to the website.
	 * If not, user is redirected to the homepage.
	 * @param request - request object from client
	 * @param response - response object returned to client
	 * @throws ServletException
	 * @throws IOException
	 */
//	private void performRelogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("**********************Perform Relogin*********************");
//		Cookie[] cookies;
//		HttpSession session = request.getSession();
//		String value = null;
//		
//		//Get all cookies from browser:
//		cookies = request.getCookies();
//		if(cookies != null) {
//			//Traverse the whole cookie, and find this certain cookie = s2 of Cookie in loginServlet
//			for(Cookie c : cookies) {
//				if(c.getName().equals("USER")) {
//					System.out.println("Cookie found: " + c.getName());
//					System.out.println("The value of cookie: " + c.getValue());
//					value = c.getValue();
//					response.addCookie(c);
//				}
//			}
//		}
//		
//		//If existing redirect to website.:
//		if(value != null) {
//			session.setAttribute("UN", value);
//			response.sendRedirect("UserHomePage.jsp");
//		}
//		
//		//If not existing, back to homepage.
//		else {
//			System.out.println("No user found.");
//			response.sendRedirect("HomePage.jsp");
//		}
//		
//		System.out.println("******************************************\n");
//	}
	
	
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
		if(UserService.validateUser(email, password)) { 
			String userID = UserService.getUserID(email);
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
			response.sendRedirect("UserHomePage.jsp");
			
		}
		
		else {
			System.out.println("inValid");
			//send error code.
			// TODO front end notif
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
		
		String idNum = request.getParameter("idNum");
		String lastname = request.getParameter("lastName");
		String firstname = request.getParameter("firstName");
		String middlename = request.getParameter("middleName");
		String mobNum = request.getParameter("celNo");
		String telNum = request.getParameter("telNo");
		String username = request.getParameter("email");

		String password = request.getParameter("password");
		String rePassword = request.getParameter("password2");
		int idnum = Integer.parseInt(idNum);
		System.out.println(rePassword);
		
		// TODO verify if username is really and email
		// TODO check if there is an input ??? 
		if (idNum.matches("[0-9]+") 	  && idNum.length() == 8		    &&
			mobNum.matches("[0-9]+") 	  && telNum.matches("[0-9]+") 		&& 
			lastname.matches("[a-zA-Z]+") && firstname.matches("[a-zA-Z]+") &&
			middlename.matches("[a-zA-Z]+") && password.equals(rePassword) ) 
			request.getRequestDispatcher("add").forward(request, response);
		else {
			System.out.println("Invalid input");
			// TODO front end notif
		}
	}


}
