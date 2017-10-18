package web_servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Year;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans_model.Involvement;
import beans_model.Student;
import service.PasswordAuthentication;
import service.UserService;

/**
 * This servlet handles everything regarding data such as
 * updating, deleting, adding data etc.
 */
@WebServlet(urlPatterns = {"/update",
		                   "/delete",
		                   "/add",
		                   "/view",
		                   "/addIntInv",
		                   "/addExtInv",
		                   "/search"}
)
public class dataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public dataServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am called. (DoGet data servlet)");
		switch(request.getServletPath()) {
			case "/view": retrieveUser(request, response); break; 
			default: System.out.println("ERROR(Inside dataServlet *doGet*): url pattern doesn't match existing patterns.");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am called. (DoPost data servlet)");
		switch(request.getServletPath()) {
			case "/add": addUser(request, response); break;
			case "/update": updateUser(request, response); break;
			case "/delete": deleteUser(request, response); break;
			case "/addIntInv": addInternalInvolvements(request, response); break;
			case "/addExtInv": addExternalInvolvements(request, response); break;
			case "/search": search(request, response); break;
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
	private void search(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {
		System.out.println("***************** SEARCH ************************");
		String name = request.getParameter("searchbar");
		System.out.println("SEARCHING: " + name);
		
		ArrayList<Student> studentList = UserService.getStudentByName(name);
		
		System.out.println(studentList);

		request.setAttribute("studentList", studentList);
		request.getRequestDispatcher("SearchResult.jsp").forward(request, response);
		
		System.out.println("*******************************************");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addUser(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {
		System.out.println("*****************ADD USER ************************");
		String idNum = request.getParameter("idNum");
		String lastname = request.getParameter("lastName");
		String firstname = request.getParameter("firstName");
		String middlename = request.getParameter("middleName");
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("password2");
		String college = request.getParameter("college");
		String course = request.getParameter("course");
		int idnum = Integer.parseInt(idNum);
		
			
		//If email already placed, don't do anything. <- ???

		//Else, do this.
		
		//Perform hashing here//
		PasswordAuthentication p = new PasswordAuthentication();
        
		String newPass = p.hash(password.toCharArray());
		Student student = new Student(idnum, 
				  lastname,
				  firstname,
				  middlename,
				  username, //Username is email.
				  newPass,
				  college,
				  course);
		
		UserService.addUser(student);
		System.out.println("User added!");
		
		response.sendRedirect("HomePage.jsp");
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
		Cookie[] cookies = request.getCookies();
		Cookie userCookie = null;
		Student student = null;
		
		System.out.println("************************************Retrieve User**********************************");
		for (Cookie c: cookies) {
			if(c.getName().equals("USER")) {
				System.out.println("Cookie found!");
				System.out.println("Cookie name: " +  c.getName());
				System.out.println("Cookie Value: " + c.getValue());
				userCookie = c;
			}
		}
		
		student = UserService.getLoggedStudent(Integer.parseInt(userCookie.getValue()));
		request.setAttribute("loggedUser", student);
		request.getRequestDispatcher("ViewProfile.jsp").forward(request, response);
		
		System.out.println("***********************************************************************************");
	}
	

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Student student = new Student();
		String dbID = (String) request.getSession().getAttribute("UN");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String prov = request.getParameter("prov");
		String country = request.getParameter("country");
		int zip = Integer.parseInt(request.getParameter("zip"));
		String email = request.getParameter("email");
		String cell = request.getParameter("cell");
		String tel = request.getParameter("tel");
		String bday = request.getParameter("bday");
		String civil = request.getParameter("civil");
		String citizen = request.getParameter("citizen");
		String gender = request.getParameter("gender");
		int idNum = Integer.parseInt(request.getParameter("idNum"));
		String course = request.getParameter("course");
		String college = request.getParameter("college");
		
		student.setSQLDate(bday);
		
		System.out.println("SessionID: " + dbID);
		System.out.println("lastname: " + lastName);
		System.out.println("firstname: " + firstName);
		System.out.println("middleName: " + middleName);
		System.out.println("address: " + address);
		System.out.println("City: " + city);
		System.out.println("prov:  " + prov);
		System.out.println("country: " + country);
		System.out.println("zip: " + zip);
		System.out.println("email: " + email);
		System.out.println("cell: " + cell);
		System.out.println("tel: " + tel);
		System.out.println("bday: " + student.getTempDate());
		System.out.println("civil: " + civil);
		System.out.println("citizen: " + citizen);
		System.out.println("gender: " + gender);
		System.out.println("idNum: " + idNum);
		System.out.println("course: " + course);
		System.out.println("college: " + college);
		
	    student.setDbID(Integer.parseInt(dbID));
		student.setStudentId(idNum);
	    student.setFirstName(firstName);
	    student.setMiddleName(middleName);
		student.setLastName(lastName);
		student.setCelNo(cell);
		student.setTelNo(tel);
	    student.setEmail(email);
        student.setCollege(college);
		student.setCourse(course);
	    student.setAddress(address);
		
		student.setCivil(civil);
		student.setCitizen(citizen);
		student.setGender(gender);
		student.setCountry(country);
		student.setProvince(prov);
		student.setZip(zip);
	    student.setCity(city);
	    
	    UserService.updateStudent(student);
	    
	    //After updating, go back to view.
	    response.sendRedirect("view");
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

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addInternalInvolvements(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		Cookie userCookie = null;
		int idnum;
		
		System.out.println("***************** ADD INVOLVEMENTS ************************");
		String inyear = request.getParameter("inyear");
		String org = request.getParameter("inorgname");
		String pos = request.getParameter("inorgpos");
		
		Year year = Year.parse(inyear);
		
		for (Cookie c: cookies) {
			if(c.getName().equals("USER")) {
				System.out.println("Cookie found!");
				System.out.println("Cookie name: " +  c.getName());
				System.out.println("Cookie Value: " + c.getValue());
				userCookie = c;
			}
		}
		
		idnum = UserService.getUserIDNum(Integer.parseInt(userCookie.getValue()));
		
		Involvement involvement = new Involvement(idnum, org, pos, year, 1);
		
		UserService.addInvolvements(involvement);
		
		System.out.println("***********************************************************************************");
		
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addExternalInvolvements(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		Cookie userCookie = null;
		int idnum;
		
		System.out.println("***************** ADD INVOLVEMENTS ************************");
		String inyear = request.getParameter("inyear");
		String org = request.getParameter("inorgname");
		String pos = request.getParameter("inorgpos");
		
		Year year = Year.parse(inyear);
		
		for (Cookie c: cookies) {
			if(c.getName().equals("USER")) {
				System.out.println("Cookie found!");
				System.out.println("Cookie name: " +  c.getName());
				System.out.println("Cookie Value: " + c.getValue());
				userCookie = c;
			}
		}
		
		idnum = UserService.getUserIDNum(Integer.parseInt(userCookie.getValue()));
		
		Involvement involvement = new Involvement(idnum, org, pos, year, 0);
		
		UserService.addInvolvements(involvement);
		
		System.out.println("***********************************************************************************");
		
	}

}
