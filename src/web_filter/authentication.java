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
	private boolean proceed;

    /**
     * Default constructor. 
     */
    public authentication() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		req = (HttpServletRequest) request;
		res = (HttpServletResponse) response;
		proceed = false; //Fixed infinite redirection
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
						proceed = true; //if it exists, proceed.
					
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
		System.out.println("Cookie exists: " + proceed);

		switch(url) {
			case "/HomePage.jsp"://if Cookie exists, proceed to userHomePage.
			case "/Signup.jsp":
								 if(proceed) {
									 System.out.println("Redirecting to UserHomePage.jsp..");
									 res.sendRedirect("UserHomePage.jsp");
								 }
								 //If not, continue to page.
								 else {
									 System.out.println("Continue on this page..");
									 chain.doFilter(request, response);
								 }
								 break; 
			case "/UserHomePage.jsp": //if cookie exists, continue
				                     if(proceed) {
				                    	 System.out.println("Continue on this page..");
				                        chain.doFilter(request, response);
									 }
				                     //If not, go to homepage
				                     else {
				                    	 System.out.println("Redirecting to HomePage.jsp..");
				                    	 res.sendRedirect("HomePage.jsp");
				                     }
									 break;
									 
			default: System.out.println("ERORR (In authentication filter): Path does not exist ");
		}
		
		System.out.println("*********************************************\n\n");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}