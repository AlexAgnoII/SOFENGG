package web_servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans_model.Student;
import service.AdminService;
import service.StudentService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(urlPatterns = {"/search",
		                   "/viewByAdmin"
})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am called. (DoGet  AdminServlet)");
		switch(request.getServletPath()) {
	    	case "/viewByAdmin": retrieveStudentForAdmin(request, response); break;
			case "/search":  search(request, response); break;
	    	default: System.out.println("ERROR(Inside AdminServlet *doGet*): url pattern doesn't match existing patterns.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am called. (DoPost  AdminServlet)");
		switch(request.getServletPath()) {
			default: System.out.println("ERROR(Inside AdminServlet *doPost*): url pattern doesn't match existing patterns.");
		}
	}
	
	/**
	 * Searches for and retrieves the necessary information searched by admin.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void search(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {
		System.out.println("***************** SEARCH ************************");
		String name = request.getParameter("searchbar");
		System.out.println("SEARCHING: " + name);
		
		ArrayList<Student> studentList = AdminService.getStudentByName(name);
		
		System.out.println(studentList);

		request.setAttribute("studentList", studentList);
		request.getRequestDispatcher("SearchResult.jsp").forward(request, response);
		
		System.out.println("*******************************************");
	}

	/**
	 * Retrieve student that the admin clicked upon in the search result.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void retrieveStudentForAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		Cookie userCookie = null;
		Student student = null;
		
		System.out.println("************************************Retrieve User (Viewing student through admin)**********************************");
		for (Cookie c: cookies) {
			if(c.getName().equals("ADMIN")) {
				System.out.println("Cookie found!");
				System.out.println("Cookie name: " +  c.getName());
				System.out.println("Cookie Value: " + c.getValue());
				userCookie = c;
			}
		}
		
		if(userCookie == null || userCookie.getName().equals("ADMIN")){
			student = AdminService.getStudentByIdNum((Integer)request.getAttribute("idNum"));
			request.setAttribute("loggedUser", student);
			
			if(request.getServletPath().contentEquals("/view")) {
				System.out.println("Viewing via viewprofile..");
				request.getRequestDispatcher("ViewProfile.jsp").forward(request, response);
			}	
		}
		System.out.println("***********************************************************************************");
	}
	


}
