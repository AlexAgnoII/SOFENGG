package web_servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans_model.Post;
import beans_model.Student;
import service.AdminService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(urlPatterns = {"/search",
						   "/searchQualifiedStudents", 
						  "/viewByAdmin",
		                  "/createPost",
		                  "/getPosts",
		                  "/updatePost"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am called. (DoGet  AdminServlet)");
		switch(request.getServletPath()) {
	    	case "/viewByAdmin"			   : retrieveStudentForAdmin(request, response); break;
			case "/search"				   : search(request, response); break;
			case "/searchQualifiedStudents": searchQualifiedStudents(request, response); break;
			case "/getPosts"			   : getPosts(request, response); break;
	    	default: System.out.println("ERROR(Inside AdminServlet *doGet*): url pattern doesn't match existing patterns.");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am called. (DoPost  AdminServlet)");
		switch(request.getServletPath()) {
			case "/createPost":  createPost(request, response); break;
			case "/updatePost":  updatePost(request, response); break;
			default: System.out.println("ERROR(Inside AdminServlet *doPost*): url pattern doesn't match existing patterns.");
		}
	}

	/**
	 * Updates a post by the admin
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updatePost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {
		System.out.println("***************** UPDATE POST ************************");
		String title  = request.getParameter("title"),
			   body   = request.getParameter("body");
		int    postId = 0;
		
		try{
			postId = Integer.parseInt(request.getParameter("postId"));
		}catch(NumberFormatException e){
			System.out.println("Error: AdminServlet.java String to Integer parsing updatePost method");
		}
		System.out.println("Updating: Post #" + postId);
		
		AdminService.updatePost(postId, title, body);

		
		System.out.println("*******************************************");
	}

	/**
	 * Creates new post by the admin.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void createPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {
		System.out.println("***************** Create Post ************************");
		String title = request.getParameter("title"),
			   body  = request.getParameter("body"),
			   id 	 = "";
		

//		Cookie[]cookies = request.getCookies();
//		for (Cookie c : cookies) {
//			if(c.getName().equals("ADMIN") && c.getMaxAge() != 0) {
//				id = "" + c.getValue();
//			}
//		}
		System.out.println("Posting: " + title);
		AdminService.createPost(title, body);

		
		
		System.out.println("*******************************************");
	}


	/**
	 * Retrieves a list of posts made by the admin
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @return List of Posts
	 */
	private void getPosts(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {
		System.out.println("***************** UPDATING POST FEED ************************");
		String user = request.getParameter("user");
		ArrayList<Post> postList = AdminService.getPosts();
		String htmlPostList 	 = "";
		SimpleDateFormat ft = new SimpleDateFormat ("MMMMM dd, yyyy; hh:mm a");
		
		
	    for(Post p : postList){
			htmlPostList += "<div class = 'postContainer' postId = '" + p.getPostId() +"'>" +
							"	<div class='pchead'>" +
					        "   	<p class = 'postTitle'>" + p.getTitle() + "</p>" +
					        (user.equals("admin") ?
					        "		<a class='modal-trigger' href = '#updateAnnounce' onClick = '(function(){"+
							"				    document.getElementById(\"updateTitle\"" +
					        ").setAttribute(\"postId\", \"" + p.getPostId() + "\");" +
							"   				$(\"#updateTitle\").val(\"" + p.getTitle() + "\");" +
							"   		 		$(\"#updateBody\").val(\"" + p.getBody() + "\");" +
							"				    return false;" +
							"				})();return false;'>" +
							"	<i class='material-icons editbtn'>edit</i></a>" : "") +
					        "   <p class = 'postDate' >" + ft.format(p.getDate()) + "</p>" +
					        "	</div>" + 
					        "   <p class = 'postBody' >" + p.getBody() + "</p>" + 
					        "</div> ";
		}
		
		System.out.println(postList);
	    response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlPostList);       
		System.out.println("*******************************************");
		
		
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
		String name 			 = request.getParameter("name"),
			   collegeVal 		 = request.getParameter("collegeVal"),
			   htmlStudentList 	 = "";
		
		System.out.println("SEARCHING: " + name);
		
		if(collegeVal.equals("College"))
			collegeVal = "";
		
		ArrayList<Student> studentList = AdminService.getStudent(name, collegeVal);
			
	    for(Student s : studentList){

			htmlStudentList += "<tr>" + 
				               "	<td class='tableIdNum center-align'>"   + s.getStudentId() + "</td>" +
				               "	<td class='tableName left-align'>" 	    + s.getFirstName()  + " " +
																		      s.getMiddleName() + " " +
																		      s.getLastName()   + "</td>" +
				               "	<td class='tableCollege center-align'>" + s.getCollege() +"</td>" +
				               "	<td class='tableAward center-align'>"   + AdminService.getStudentAwards(s) + "</td>" +
				               "</tr>";
	    }

		System.out.println(studentList);

//		request.setAttribute("studentList", studentList);
//		request.getRequestDispatcher("SearchResult.jsp").forward(request, response);
	    response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlStudentList);    
		System.out.println("*******************************************");
	}

	/**
	 * Searches for and retrieves the necessary information searched by admin.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void searchQualifiedStudents(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {
		System.out.println("***************** SEARCH QUALIFIED STUDENTS ************************");
		String name 			 = request.getParameter("name"),
			   collegeVal 		 = request.getParameter("collegeVal"),
			   htmlStudentList 	 = "";
		
		System.out.println("SEARCHING: " + name);
		
		ArrayList<Student> studentList = AdminService.getStudentsEligibleAward(name, collegeVal);
			
	    for(Student s : studentList){
			htmlStudentList += "<tr>" + 
				               "	<td class='tableIdNum center-align'>"   + s.getStudentId() + "</td>" +
				               "	<td class='tableName left-align'>" 	    + s.getFirstName()  + " " +
																		      s.getMiddleName() + " " +
																		      s.getLastName()   + "</td>" +
				               "	<td class='tableCollege center-align'>" + s.getCollege() +"</td>" +
				               "	<td class='tableAward center-align'>"   + AdminService.getStudentAwards(s) + "</td>" +
				               "</tr>";
	    }

		System.out.println(studentList);

//		request.setAttribute("studentList", studentList);
//		request.getRequestDispatcher("SearchResult.jsp").forward(request, response);
	    response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlStudentList);    
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
