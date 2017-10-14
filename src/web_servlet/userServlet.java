package web_servlet;

/**
 * This servlet handles Login, logout, relogin, and signup.
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/login",	
		                   "/signUp", 
		                   "/relog", 
		                   "/logout"}
)

public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public userServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("I am called. (DoGet)");
		response.sendRedirect("Test.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("I am called. (DoPost)");
	}

}
