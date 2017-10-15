package web_servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans_model.Student;
import service.UserService;

/**
 * This servlet handles everything regarding data such as
 * updating, deleting, adding data etc.
 */
@WebServlet(urlPatterns = {"/update",
		                   "/delete",
		                   "/add",
		                   "/retrieve"}
)
public class dataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public dataServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am called. (DoGet data servlet)");
		switch(request.getServletPath()) {
			case "/retrieve": retrieveUser(request, response); break; 
			default: System.out.println("ERROR(Inside dataServlet *doGet*): url pattern doesn't match existing patterns.");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am called. (DoPost data servlet)");
		switch(request.getServletPath()) {
			case "/add": addUser(request, response); break;
			case "/update": updateUser(request, response); break;
			case "/delete": deleteUser(request, response); break;
			default: System.out.println("ERROR(Inside dataServlet *doPost*): url pattern doesn't match existing patterns.");
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addUser(HttpServletRequest request, HttpServletResponse response)  {
		System.out.println("*****************ADD USER ************************");
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
		
		if(password.equals(rePassword)) {
			
			//If email already placed, don't do anything.
			
			//Else, do this.
			
			//Perform hashing here//
			Student student = new Student(idnum, 
					  lastname,
					  firstname,
					  middlename,
					  mobNum,
					  telNum,
					  username, //Username is email.
					  password);
			//add the user.
			UserService.addUser(student);
			System.out.println("User added!");
		}
		
		else {
			//send error code that password did not match.
		}
		System.out.println("*******************************************");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void retrieveUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	}

}
