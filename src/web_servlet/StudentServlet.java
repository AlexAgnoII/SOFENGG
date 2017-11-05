package web_servlet;

import java.io.IOException;

import java.time.Year;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans_model.Involvement;
import beans_model.Relative;
import beans_model.Student;
import service.PasswordAuthentication;
import service.StudentService;

/**
 * This servlet handles everything regarding data such as
 * updating, deleting, adding data etc.
 */
@WebServlet(urlPatterns = {"/updatePersonal", //Student
		                   "/updateFamily",
				   		   "/updateAcadInfo1", //Student
		                   "/add", //Student
		                   "/viewByStudent",
		                   "/addIntInv", //Student
		                   "/addExtInv"} //Student
)
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String duplicateError;
	
    public StudentServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am called. (DoGet data servlet)");
		switch(request.getServletPath()) {
		    //case "/view2edit":
			case "/viewByStudent": retrieveStudent(request, response); break; 
			default: System.out.println("ERROR(Inside dataServlet *doGet*): url pattern doesn't match existing patterns.");
		}
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am called. (DoPost data servlet)");
		switch(request.getServletPath()) {
			case "/add": addStudents(request, response); break;
			case "/updatePersonal": updatePersonal(request, response); break;
			case "/updateAcadInfo1": updateAcadInfo(request, response); break;
			case "/updateFamily": updateFamily(request, response); break;
			case "/addIntInv": addInternalInvolvements(request, response); break;
			case "/addExtInv": addExternalInvolvements(request, response); break;
			default: System.out.println("ERROR(Inside dataServlet *doPost*): url pattern doesn't match existing patterns.");
		}
	}

	/**
	 * Add students
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addStudents(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {
		System.out.println("*****************ADD USER ************************");
		String idNum = request.getParameter("idNum");
		String lastname = request.getParameter("lastName");
		String firstname = request.getParameter("firstName");
		String middlename = request.getParameter("middleName");
		String username = request.getParameter("email"); //Email
		String password = request.getParameter("password");
		String rePassword = request.getParameter("password2");
		String college = request.getParameter("college");
		String course = request.getParameter("course");
		int idnum = Integer.parseInt(idNum);

		//if username OR idnumber OR both has a duplicate in DP, send error message.
		if(checkDuplicates(username, idnum)) {
			response.getWriter().write(duplicateError);
		}
		else {
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
			
			StudentService.addStudent(student);
			System.out.println("User added!");
			
			//response.sendRedirect("HomePage.jsp");
			response.getWriter().write("VALID-SIGNUP");
		}
		System.out.println("*******************************************");
	}

	private boolean checkDuplicates(String username, int idnum) {
		// TODO Auto-generated method stub
		boolean hasDuplicate = false;
		ArrayList<String> container = new ArrayList<String>(); //Contains the fields that has duplicates with the DB.
		duplicateError = "";
		
		//Email is already taken.
		if(StudentService.isEmailTaken(username)) {
			hasDuplicate = true;
			container.add("EMAIL-TAKEN");
		}
		
		//idnumber is already taken
		if(StudentService.isIdNumberTaken(idnum)) {
			hasDuplicate = true;
			container.add("IDNUM-TAKEN");
		}
		
		if(hasDuplicate) {
			if(container.size() == 1)
				duplicateError = container.get(0);
			else {
				for (int i = 0; i < container.size(); i++) {
					if(i != container.size()-1) {
						duplicateError += container.get(i) + "|";
					}
					else {
						duplicateError += container.get(i);
					}
					
				}
			}
		}
		
		return hasDuplicate;
	}

	/**
	 * Retrieves the data of the user currently logged in.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void retrieveStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		Cookie userCookie = null;
		Student student = null;
		ArrayList<Relative> relativeList = null;
		
		System.out.println("************************************Retrieve User (User side)**********************************");
		for (Cookie c: cookies) {
			if(c.getName().equals("USER")) {
				System.out.println("Cookie found!");
				System.out.println("Cookie name: " +  c.getName());
				System.out.println("Cookie Value: " + c.getValue());
				userCookie = c;
			}
		}
		
		if(userCookie != null){
			student = StudentService.getLoggedStudent(Integer.parseInt(userCookie.getValue()));
			System.out.println("Relatives:");
			relativeList = StudentService.getRelatives(Integer.parseInt(userCookie.getValue()));
			
			if(relativeList != null) {
				for(Relative r : relativeList) {
					System.out.println(r.toString());
				}
			}
			else {
				System.out.println("No relatives.");
			}
			
			
			
			request.setAttribute("loggedUser", student);
			request.setAttribute("relativeList", relativeList);
			System.out.println("Viewing via viewprofile..");
			
			request.getRequestDispatcher("ViewProfile.jsp").forward(request, response);

		}
		
		else {
			System.out.println("ERROR! (RETRIEVESTUDENT in studentserlvet");
		}
		System.out.println("***********************************************************************************");
	}
	

	/**
	 * updates the student's personal information.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updatePersonal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("****************************UPDATE PERSONAL*******************************************");
		Student student = new Student();
		
		String dbID = (String) request.getSession().getAttribute("UN");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String prov = request.getParameter("prov");
		String country = request.getParameter("country");
		int zip = Integer.parseInt(request.getParameter("zip"));
		String cell = request.getParameter("cell");
		String tel = request.getParameter("tel");
		String bday = request.getParameter("bday");
		String civil = request.getParameter("civil");
		String citizen = request.getParameter("citizen");
		String gender = request.getParameter("gender");
		
		student.setSQLDate(bday);
		
		System.out.println("SessionID: " + dbID);
		System.out.println("address: " + address);
		System.out.println("City: " + city);
		System.out.println("prov:  " + prov);
		System.out.println("country: " + country);
		System.out.println("zip: " + zip);
		System.out.println("cell: " + cell);
		System.out.println("tel: " + tel);
		System.out.println("bday: " + student.getTempDate());
		System.out.println("civil: " + civil);
		System.out.println("citizen: " + citizen);
		System.out.println("gender: " + gender);
		
	    student.setDbID(Integer.parseInt(dbID));
		student.setCelNo(cell);
		student.setTelNo(tel);
	    student.setAddress(address);
		student.setCivil(civil);
		student.setCitizen(citizen);
		student.setGender(gender);
		student.setCountry(country);
		student.setProvince(prov);
		student.setZip(zip);
	    student.setCity(city);
    
	    System.out.println("***********************************************************************************");
	}
	
	/**
	 * Updates the family siblings/mother/father of a student.
	 * @param request
	 * @param response
	 */
	private void updateFamily(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("********************************UPDATE FAMILY******************************");
		Relative dadRel = new Relative();
		Relative momRel = new Relative();
		Relative sibRel = new Relative();
		Enumeration<String> e = request.getParameterNames();
		ArrayList<String> siblingList = new ArrayList<String>();
		ArrayList<Relative> relativeList = new ArrayList<Relative>();
		Cookie[] cookies = request.getCookies();
		Cookie userCookie = null;
		String[] temp;
		int lengthSib; //length of siblings.
		
		for (Cookie c: cookies) {
			if(c.getName().equals("USER")) {
				System.out.println("Cookie found!");
				System.out.println("Cookie name: " +  c.getName());
				System.out.println("Cookie Value: " + c.getValue());
				userCookie = c;
			}
		}
		System.out.println("User ID: " + userCookie.getValue());
		
		//Organize Relatives.
		System.out.println();

		
		//Separated parents from siblings
		while (e.hasMoreElements()) {
		    String param = e.nextElement();
		    String value = request.getParameter(param);
		    System.out.println(param + ": " + value);
		    temp = param.split("-"); //0 = name | 1 =  Relative ID in database. (For Mom and Dad)
		    
		    if(param.matches(".*dad.*")) {
		    	if(param.matches(".*dadName.*")) {
		    		dadRel.setName(value);
		    	}
		    	else if (param.matches(".*dadWork.*")) {
		    		dadRel.setOccupation(value);
		    	}
		    	else {
		    		dadRel.setSQLDate(value);
		    		dadRel.setType("Father");
		    		dadRel.setRelativeId(Integer.parseInt(temp[1]));
		    	}
		    	
		    }
		    
		    else if(param.matches(".*mom.*")){

		    	if(param.matches(".*momName.*")) {
		    		momRel.setName(value);
		    	}
		    	else if (param.matches(".*momWork.*")) {
		    		momRel.setOccupation(value);
		    	}
		    	else {
		    		momRel.setSQLDate(value);
		    		momRel.setType("Mother");
		    		momRel.setRelativeId(Integer.parseInt(temp[1]));
		    	}
		    }
		    
		    else siblingList.add(param);
		}
	 
	    System.out.println();
	    
	    lengthSib = siblingList.size() / 3;
	    for(int i = 0; i < lengthSib; i++) {
	    	relativeList.add(new Relative());
	    }

	    for(String r : siblingList) {
	    	temp = r.split("-"); //0 = name | 1 = field index  | 2 =Relative ID in database. (For Mom and Dad)
	    	//temp[1] is the # of the field input in sibling.
	    	if(temp[0].equals("sibName")) {
	    		relativeList.get(Integer.parseInt(temp[1])).setName(request.getParameter(r));
	    		
	    	}
	    	else if(temp[0].equals("sibWork")) {
	    		relativeList.get(Integer.parseInt(temp[1])).setOccupation(request.getParameter(r));
	    	}
	    	else {
	    		relativeList.get(Integer.parseInt(temp[1])).setType("Sibling");//sincec this if goes once through a certain field, this sets the type of the field once.
	    		relativeList.get(Integer.parseInt(temp[1])).setSQLDate(request.getParameter(r));
	    		relativeList.get(Integer.parseInt(temp[1])).setRelativeId(Integer.parseInt(temp[2]));
	    	}
	    }
	    
	    relativeList.add(dadRel);
	    relativeList.add(momRel);
	    
	    for (Relative r : relativeList) {
	    	r.setStudentId(Integer.parseInt(userCookie.getValue()));
	    	System.out.println(r.toString());
	    	StudentService.updateOrAddRelative(r);
	    }
	    
	    
	    
	    System.out.println("Update/Add Complete!");
		System.out.println("***************************************************************************");
	}
	
	/**
	 * Updates the user's acade,mic information
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateAcadInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("****************************UPDATE PERSONAL*******************************************");
		
		Student student = new Student();
		String dbID 	= (String) request.getSession().getAttribute("UN");
		String course   = request.getParameter("course");
		String college  = request.getParameter("college");
		String idNum	= request.getParameter("idNum");
		int idnum 		= Integer.parseInt(idNum);
		
		System.out.println("SessionID: " + dbID);
		System.out.println("idNum: " + idNum);
		System.out.println("course: " + course);
		System.out.println("college: " + college);
		
	    student.setDbID(Integer.parseInt(dbID));
	    student.setStudentId(idnum);
	    student.setCourse(course);
		student.setCollege(college);
		
	    StudentService.updateStudentAcadInfo(student);
	    System.out.println("***********************************************************************************");
	    //After updating, go back to edit.
	    response.sendRedirect("view2edit");
	}

	/**
	 * Adds internal involvement for the user
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addInternalInvolvements(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		Cookie userCookie = null;
		int idnum;
		
		System.out.println("***************** ADD INTERNAL INVOLVEMENTS ************************");
		String inyear = request.getParameter("inyear");
		String org = request.getParameter("inorg");
		String pos = request.getParameter("inpos");
		
		Year year = Year.parse(inyear);
		
		for (Cookie c: cookies) {
			if(c.getName().equals("USER")) {
				System.out.println("Cookie found!");
				System.out.println("Cookie name: " +  c.getName());
				System.out.println("Cookie Value: " + c.getValue());
				userCookie = c;
			}
		}
		
		idnum = StudentService.getStudentIDNum(Integer.parseInt(userCookie.getValue()));
		
		
		Involvement involvement = new Involvement(idnum, org, pos, year, 1);
		
		involvement.setIdNum(idnum);
		involvement.setiName(org);
		involvement.setAcadYear(year);
		involvement.setPosition(pos);
		involvement.setInternal(1);
		
		StudentService.addInvolvements(involvement);
		
		response.sendRedirect("viewByStudent");
		
		System.out.println("***********************************************************************************");
		
	}
	
	
	/**
	 * Adds external involvement for user.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addExternalInvolvements(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		Cookie userCookie = null;
		int idnum;
		
		System.out.println("***************** ADD EXTERNAL INVOLVEMENTS ************************");
		String exyear = request.getParameter("exyear");
		String org = request.getParameter("exorg");
		String pos = request.getParameter("expos");
		
		Year year = Year.parse(exyear);
		
		for (Cookie c: cookies) {
			if(c.getName().equals("USER")) {
				System.out.println("Cookie found!");
				System.out.println("Cookie name: " +  c.getName());
				System.out.println("Cookie Value: " + c.getValue());
				userCookie = c;
			}
		}
		
		idnum = StudentService.getStudentIDNum(Integer.parseInt(userCookie.getValue()));
		
		Involvement involvement = new Involvement(idnum, org, pos, year, 0);
		
		involvement.setIdNum(idnum);
		involvement.setiName(org);
		involvement.setAcadYear(year);
		involvement.setPosition(pos);
		involvement.setInternal(0);
		
		StudentService.addInvolvements(involvement);
		
		response.sendRedirect("viewByStudent");
		
		System.out.println("***********************************************************************************");
	}
}
