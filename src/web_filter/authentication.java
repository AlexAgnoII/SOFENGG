package web_filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Authentication
 */
@WebFilter(urlPatterns={"*.html" ,"*.jsp"})
public class authentication implements Filter {
	private HttpServletRequest req;
	private HttpServletResponse res;
	private boolean user;

    /**
     * Default constructor. 
     */
    public authentication() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		req = (HttpServletRequest) request;
		res = (HttpServletResponse) response;
		user = false; //Fixed infinite redirection
		Boolean admin = false;
		String url = req.getServletPath(); //
		String temp;
		HttpSession theSession;
		
		System.out.println("\n*************AUTHENTICATE SERVLET*********************");
		System.out.println("Checking if cookies and session exists.");
		//check if session attribute exists
		theSession = req.getSession();
		System.out.println("Session attribute(UN): " + theSession.getAttribute("UN"));
		
		//Check if the cookie "USER" exists.
		Cookie[] cookieList = req.getCookies();
		System.out.println("Authenticate url path: "+  url);
		if(cookieList != null) {
			for(Cookie c : cookieList) {
				if(c.getName().equals("USER")) {
					System.out.println("USER Cookie found!");
						
					if(c.getMaxAge() != 0)
						user = true; //if it exists, proceed.
					
					//Allows session attribute to stay in the website
					//When user enters exact url.
					if(theSession.getAttribute("UN") == null) {
						temp = c.getValue();
						System.out.println("(Authenticate) Cookie value: " + temp);
						theSession.setAttribute("UN",temp);
						System.out.println("Session(UN): " + theSession.getAttribute("UN"));
					}
					
				} else if(c.getName().equals("ADMIN")) {
					System.out.println("ADMIN Cookie found!");
						
					if(c.getMaxAge() != 0){
						admin = true;
					}
					
					//Allows session attribute to stay in the website
					//When user enters exact url.
					if(theSession.getAttribute("UN") == null) {
						temp = c.getValue();
						System.out.println("(Authenticate) Cookie value: " + temp);
						theSession.setAttribute("UN",temp);
						System.out.println("Session(UN): " + theSession.getAttribute("UN"));
					}
					
				}
			}
		}
		
		System.out.println("***********************AUTHENTICATE FILTER LOG**********************");
		System.out.println("Url:" + url);
		System.out.println("Cookie exists: " + user);
		
		System.out.println("Is it user or adming?::");
		if(user) {
			System.out.println("User");
		}
		else if(admin) {
			System.out.println("Admin");
		}
		else {
			System.out.println("No user!");
		}
		

		switch(url) {
		    /*Public Pagges*/
			case "/ExpiredReset.html": 
		    case "/Verification.jsp":
		    case "/ResetPassword.jsp":
		    case "/ResetPasswordEmail.html":
		    case "/Signup.jsp":
			case "/HomePage.jsp": //If user, proceed to UserHomePage.
				                System.out.println("Access public pages...");
								if(user) {
									 System.out.println("Redirecting to ViewProfile.jsp.."); 
									 res.sendRedirect("UserHomePage.jsp"); //Redirect to viewprofile for now.
								 }
								
								 //If admin, go to adminHomePage
								 else if(admin) {
									 System.out.println("Redirecting to AdminHomePage.jsp..");
									 res.sendRedirect("AdminHomePage.jsp");
								 }
								 //No user, continue.
								 else {
									 System.out.println("Continue on this page..");
									 chain.doFilter(request, response);
								 }
								 break; 
			/*Private Pages*/
			case "/ViewProfile.jsp":
			case "/UserHomePage.jsp": //if cookie exists, continue
								System.out.println("Access user pages..");
				                if(user) {
				               	   System.out.println("Continue on this page..");
				                   chain.doFilter(request, response);
								 }
				                //If not, go to homepage
				                
				                else {
				               	  System.out.println("Redirecting to HomePage.jsp..");
				               	  res.sendRedirect("HomePage.jsp");
				                }
								 break; 
			case "/ViewStudents.jsp":
			case "/AdminViewStudent.jsp":
			case "/AdminSearchForStudents.jsp": 
			case "/SearchResult.jsp": //if cookie exists, continue
			case "/AdminHomePage.jsp": //if cookie exists, continue
								System.out.println("Access admin pages...");
				                if(admin) {
				               	   System.out.println("Continue on this page..");
				                   chain.doFilter(request, response);
								 }
				                //If not, go to homepage
				                else {
				               	 System.out.println("Redirecting to HomePage.jsp..");
				               	 res.sendRedirect("HomePage.jsp");
				                }
								 break;
		    //Both pages has this
			case "/ChangePassword.jsp": //if user/admin exist, continue
				                         if(admin || user) {
							               	   System.out.println("Continue on this page..");
							                   chain.doFilter(request, response);
										 }
				                         else {
							               	 System.out.println("Redirecting to HomePage.jsp..");
							               	 res.sendRedirect("HomePage.jsp");
				                         }
				                         break;
		    case "/Verified.html" :
		    case "/EmailSent.html":	 System.out.println("Continue on this page..");
            						 chain.doFilter(request, response);
						          	 break;
			default: System.out.println("ERORR (In authentication filter): Path does not exist ");

				    
		}
		
		System.out.println("*********************************************\n\n");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
