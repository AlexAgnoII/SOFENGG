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
import javax.servlet.http.HttpSession;

import beans_model.Notification;
import beans_model.Post;
import beans_model.Student;
import service.AdminService;
import service.StudentService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(urlPatterns = {"/search",
						   "/searchQualifiedStudents", 
						  "/viewByAdmin",
		                  "/createPost",
		                  "/getPosts",
		                  "/getAdminNotifs",
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
		
		if(loggedAdmin(request))
			switch(request.getServletPath()) {
		    	case "/viewByAdmin"			   : retrieveStudentForAdmin(request, response); break;
				case "/search"				   : search(request, response); break;
				case "/searchQualifiedStudents": searchQualifiedStudents(request, response); break;
				case "/getPosts": getPosts(request, response); break;
				case "/getAdminNotifs": getAdminNotifs(request, response); break;
		    	default: System.out.println("ERROR(Inside AdminServlet *doGet*): url pattern || " +
		    			request.getServletPath() + " || doesn't match existing patterns.");
			}
		else // For the other functions that other users also access
			switch(request.getServletPath()) {
				case "/getPosts": getPosts(request, response); break;
		    	default			: System.out.println("Redirecting to HomePage.jsp..");
	         	 				  response.sendRedirect("HomePage.jsp");
			}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am called. (DoPost  AdminServlet)");
		if(loggedAdmin(request))
			switch(request.getServletPath()) {
				case "/createPost":  createPost(request, response); break;
				case "/updatePost":  updatePost(request, response); break;
				default: System.out.println("ERROR(Inside AdminServlet *doPost*): url pattern doesn't match existing patterns.");
			}
		else {
          	 System.out.println("Redirecting to HomePage.jsp..");
          	 response.sendRedirect("HomePage.jsp");
        }
	}

    private boolean loggedAdmin(HttpServletRequest req){
    	System.out.println("Checking if an admin is logged in.");
		
		Boolean admin = false;
		
		//Check if the cookie "USER" exists.
		Cookie[] cookieList = req.getCookies();
		if(cookieList != null) {
			for(Cookie c : cookieList) {
				if(c.getName().equals("ADMIN")) {
					System.out.println("ADMIN Cookie found!");
						
					if(c.getMaxAge() != 0){
						admin = true;
						break;
					}
				}
			}
		}

		return admin;
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
		Post post = null;
		int postId = 0;
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
		post = AdminService.createPost(title, body);
		postId = post.getPostId();
		AdminService.addNotif(postId, title, body);

		
		
		System.out.println("*******************************************");
	}

	/**
	 * Retrieves a list of notifs for the admin
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @return List of Posts
	 */
	private void getAdminNotifs(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {
		System.out.println("***************** UPDATING NOTIF FEED ************************");
		int notificationCount = 0;
		
		ArrayList<Notification> notificationList = AdminService.getAdminNotificationList();
//		notificationCount = AdminService.getCountNotifications(notificationList);
		String htmlNotifList 	 = "";
		
		
	    for(Notification n : notificationList){
	    	htmlNotifList += 
	    					"<a class='text' href = 'UserHomePage.jsp'>" +
							"		<button class='nchead' type = 'button' notifId = '" + n.getNotifId() +"'>" +
					        "   		Vice dean just posted a new announcement." +
					        "		</button>" + 
					        "</a>";
		}
		
		System.out.println(htmlNotifList);
	    response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlNotifList);       
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
					        "   <textarea class = 'postBody' readonly>" + p.getBody() + "</textarea>" + 
					        "</div> ";
		}
		
		System.out.println(postList);
	    response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write(htmlPostList);       
		System.out.println("*******************************************");
		
		
	}

	/** Converts the college to its abbreviated form
	 * 
	 * @param college
	 * @return The abbreviated college text
	 */
	private String getAbbreviatedCollege(String college){
		switch(college){
			case "College of Computer Studies": return "CCS";
			case "College of Business":  		return "RVRCOB";
			case "College of Education":  		return "BAGCED";
			case "College of Engineering":  	return "GCOE";
			case "College of Liberal Arts": 	return "CLA";
			case "College of Science":      	return "COS";
			case "School of Economics": 		return "SOE";
		    default   :     					return college;
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
		String name 			 = request.getParameter("name"),
			   collegeVal 		 = request.getParameter("collegeVal"),
			   htmlStudentList 	 = "";
		
		System.out.println("SEARCHING: " + name);
		
		if(collegeVal.equals("College"))
			collegeVal = "";
		
		ArrayList<Student> studentList = AdminService.getStudent(name, collegeVal);
		int studentId = 0;
	    for(Student s : studentList){
	    	studentId = s.getStudentId();
	    	System.out.println("ID: " + studentId);
			htmlStudentList += "<tr class = 'tableDataRow' onClick = \"(function(){"
							   + "	$.ajax({"
							   + " 		context: this,"
							   + " 		url:`viewByAdmin`,"
							   + " 		data:{idNum: `" + studentId + "`},"
							   + " 		type: `GET`,"
							   + " 		cache:false,"
							   + " 		success: function(data){"
							   + "			$(`#form" + studentId + "`).submit();"
//							   + " 			var jsp = data.split('#')[0]; "
//							   + "			var idNum = data.split('#')[1]; "
//									   + "	$.ajax({"
//									   + " 		context: this,"
//									   + " 		url: jsp,"
//									   + " 		data:{idNum: idNum},"
//									   + " 		type: `POST`,"
//									   + " 		cache:false,"
//									   + " 		success: function(data2){"
									   + "				 window.location.href = `AdminViewStudent.jsp`;"
//							   		   + " 		}, error:function(){"
//					   				   + " 	  }});" 
					   		   + " 		}, error:function(){"
			   				   + " 	  }});" +
							   "    })();return false;\">" +
				               "	<td class='tableIdNum center-align'>"   + studentId + "</td>" +
				               "	<td class='tableName left-align'>" 	    + s.getFirstName()  + " " +
																		      s.getMiddleName() + " " +
																		      s.getLastName()   + "</td>" +
				               "	<td class='tableCollege center-align'>" + getAbbreviatedCollege(s.getCollege()) +"</td>" +
				               "	<td class='tableAward center-align'>"   + AdminService.getStudentAwards(s) + "</td>"
		               		 + "	<form style=`display: hidden` action=`AdminViewStudent.jsp` "
		               		 + "		method=`POST` id=`form" + studentId + "`>"
               		 		 + "		<input type=`hidden` id=`var" + studentId + 
               		 		 			"`  name=`var" + studentId + "` value=`"+ studentId + "`/>"
	 		 				 + "	</form>" +
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
			htmlStudentList += "<tr class = 'tableDataRow' onClick = \"(function(){"+
							   "	$.ajax({"
							   + " 		context: this,"
							   + " 		url:'viewByAdmin',"
							   + " 		data:{idNum: '" + s.getStudentId() + "'},"
							   + " 		type: 'GET',"
					   		   + "	    dataType: 'json',"
							   + " 		cache:false,"
							   + " 		success: function(data){"
					   		   + " 		}, error:function(){"
			   				   + " 	  }});" +
							   "    return false;" +
							   "    })();return false;\">" +
				               "	<td class='tableIdNum center-align'>"   + s.getStudentId() + "</td>" +
				               "	<td class='tableName left-align'>" 	    + s.getFirstName()  + " " +
																		      s.getMiddleName() + " " +
																		      s.getLastName()   + "</td>" +
				               "	<td class='tableCollege center-align'>" + getAbbreviatedCollege(s.getCollege()) +"</td>" +
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
		Student student = null;

		System.out.println("************************************Retrieve User (Viewing student through admin)**********************************");
		System.out.println("Student idNum: "+ request.getParameter("idNum"));
		student = AdminService.getStudentByIdNum(Integer.parseInt(request.getParameter("idNum")));
		request.setAttribute("loggedUser", student);
		
		System.out.println("Viewing via AdminViewStudent..");
//		request.getRequestDispatcher("AdminViewStudent.jsp").forward(request, response);
//		response.sendRedirect("AdminViewStudent.jsp");

	    response.setContentType("text/html"); 
	    response.setCharacterEncoding("UTF-8"); 
	    response.getWriter().write("AdminViewStudent.jsp#"+ request.getParameter("idNum"));  
		System.out.println("***********************************************************************************");
	}
	


}
