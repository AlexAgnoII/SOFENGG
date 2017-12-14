package web_servlet;

import java.io.IOException;

import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*; 
import javax.activation.*;  

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import java.util.UUID;

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
		                   "/addExtInv", // Student
		                   "/displayStudentData",
		                   "/deleteInv"} //Student
)
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String duplicateError;
	
	private static final String SOFFENG_EMAIL = "sofenggproject@gmail.com";
	private static final String SOFFENG_PASS = "Sofenggproj";
	
    public StudentServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am called. (DoGet data servlet)");
		switch(request.getServletPath()) {
		    //case "/view2edit":
			case "/viewByStudent"	  : retrieveStudent(request, response); break; 
			case "/displayStudentData": displayStudentData(request, response); break; 
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
			case "/addIntInv": addInvolvements(request, response, 1); break;
			case "/addExtInv": addInvolvements(request, response, 0); break;
			case "/deleteInv": deleteInvolvement(request, response); break;
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
		
	    String verificationId = UUID.randomUUID().toString().replace("-", "");
	    
	    System.out.println("uuid = " + verificationId);
	    
		int idnum = Integer.parseInt(idNum);

		//if username OR idnumber OR both has a duplicate in DP, send error message.
		if(checkDuplicates(username, idnum)) {
			response.getWriter().write(duplicateError);
		}
		else {
			//Perform hashing here//
			PasswordAuthentication p = new PasswordAuthentication();
	        
			String newPass 			 = p.hash(password.toCharArray()),
				   newVerificationId = p.hash(verificationId.toCharArray());
			
			
			
			Student student = new Student(idnum, 
					  lastname,
					  firstname,
					  middlename,
					  username, //Username is email.
					  newPass,
					  college,
					  course);
			
			StudentService.addStudent(student, newVerificationId);
			System.out.println("User added but needs verification!");
			
			//Perform sending verification link.
			request.setAttribute("email", username);
			request.setAttribute("verificationId", newVerificationId);
			request.getRequestDispatcher("sendVerification").forward(request, response);

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
		ArrayList<Relative> relativeList = null, 
				            siblingList = null;
		Relative mother = null, father = null;
		ArrayList<Relative> sibilings = null;
		ArrayList<Involvement> internalList = null,
				               externalList = null;
		int siblingCount = 0;
		int internalSize = 0;
		int externalSize = 0;
		
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
			internalList = StudentService.getSpecificStudentInvolvement(Integer.parseInt(userCookie.getValue()), 1);
			externalList = StudentService.getSpecificStudentInvolvement(Integer.parseInt(userCookie.getValue()), 0);
			
			if(relativeList != null) {
				for(Relative r : relativeList) {
					System.out.println(r.toString());
				}
				mother = StudentService.getMother(relativeList);
			    father = StudentService.getFather(relativeList);
			    siblingList = StudentService.getSiblings(relativeList);
			    siblingCount = StudentService.getSiblingCount(relativeList);
			}
			else {
				System.out.println("No relatives.");
			}
			
			if(mother == null) 
				System.out.println("No mother.");
			if(father == null)
				System.out.println("No father");
			if (siblingList == null || siblingList.size() ==0)
				System.out.println("No Siblings.");
			
			
			System.out.println("Involvements:");
			if(internalList != null) {
				System.out.println("Internal: ");
				for(Involvement i : internalList) {
					System.out.println(i.toString());
				}
				internalSize = StudentService.getCountInvolvements(internalList);
			}
			else System.out.println("No internal");
			
			System.out.println();
			if(externalList != null) {
				System.out.println("External:");
				for(Involvement i : externalList) {
					System.out.println(i.toString());
				}
				externalSize = StudentService.getCountInvolvements(externalList);
			}
			else System.out.println("No external.");
			
			
			request.setAttribute("loggedUser", student);
		    request.setAttribute("relativeList", relativeList);
			request.setAttribute("siblingList", siblingList);
			request.setAttribute("mother", mother);
			request.setAttribute("father", father);
			request.setAttribute("siblingSize", siblingCount-1); //deduct 1 due to index for setting the fields.
			request.setAttribute("internalList", internalList);
			request.setAttribute("externalList", externalList);
			request.setAttribute("internalSize", internalSize-1); //deduct 1 due to index for setting the fields.
			request.setAttribute("externalSize", externalSize-1); //deduct 1 due to index for setting the fields.
			System.out.println("Viewing via viewprofile..");
			
			request.getRequestDispatcher("ViewProfile.jsp").forward(request, response);

		}
		
		else {
			System.out.println("ERROR! (RETRIEVESTUDENT in studentserlvet");
			System.out.println("No cookies found");
			response.sendRedirect("HomePage.jsp");
		}
		System.out.println("***********************************************************************************");
	}
	

	/**
	 * Retrieves the data of the user currently logged in.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayStudentData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		Cookie userCookie = null;
		Student student = null;
		ArrayList<Relative> relativeList = null, 
				            siblingList = null;
		Relative mother = null, father = null;
		ArrayList<Relative> sibilings = null;
		ArrayList<Involvement> internalList = null,
				               externalList = null;
		int siblingCount = 0;
		int internalSize = 0;
		int externalSize = 0;
		
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
			internalList = StudentService.getSpecificStudentInvolvement(Integer.parseInt(userCookie.getValue()), 1);
			externalList = StudentService.getSpecificStudentInvolvement(Integer.parseInt(userCookie.getValue()), 0);
			
			if(relativeList != null) {
				for(Relative r : relativeList) {
					System.out.println(r.toString());
				}
				mother = StudentService.getMother(relativeList);
			    father = StudentService.getFather(relativeList);
			    siblingList = StudentService.getSiblings(relativeList);
			    siblingCount = StudentService.getSiblingCount(relativeList);
			}
			else {
				System.out.println("No relatives.");
			}
			
			if(mother == null) 
				System.out.println("No mother.");
			if(father == null)
				System.out.println("No father");
			if (siblingList == null || siblingList.size() ==0)
				System.out.println("No Siblings.");
			
			
			System.out.println("Involvements:");
			if(internalList != null) {
				System.out.println("Internal: ");
				for(Involvement i : internalList) {
					System.out.println(i.toString());
				}
				internalSize = StudentService.getCountInvolvements(internalList);
			}
			else System.out.println("No internal");
			
			System.out.println();
			if(externalList != null) {
				System.out.println("External:");
				for(Involvement i : externalList) {
					System.out.println(i.toString());
				}
				externalSize = StudentService.getCountInvolvements(externalList);
			}
			else System.out.println("No external.");
			
			
			request.setAttribute("loggedUser", student);
		    request.setAttribute("relativeList", relativeList);
			request.setAttribute("siblingList", siblingList);
			request.setAttribute("mother", mother);
			request.setAttribute("father", father);
			request.setAttribute("siblingSize", siblingCount-1); //deduct 1 due to index for setting the fields.
			request.setAttribute("internalList", internalList);
			request.setAttribute("externalList", externalList);
			request.setAttribute("internalSize", internalSize-1); //deduct 1 due to index for setting the fields.
			request.setAttribute("externalSize", externalSize-1); //deduct 1 due to index for setting the fields.
			System.out.println("Viewing via printStudentData..");
			
			request.getRequestDispatcher("PrintStudentData.jsp").forward(request, response);

		}
		
		else {
			System.out.println("ERROR! (DISPLAYSTUDENTDATA in studentserlvet");
			System.out.println("No cookies found");
			response.sendRedirect("HomePage.jsp");
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
	    
	    StudentService.updateStudent(student);
    
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
		Map<String, String[]> eVal = request.getParameterMap();
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
	    	StudentService.updateOrAddRelative(r); //updating done here.
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
	 * Adds involvement for user.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addInvolvements(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException {
		ArrayList<Involvement> involvementList = new ArrayList<Involvement>();
		Enumeration<String> e = request.getParameterNames();
		ArrayList<String> paramNames = new ArrayList<String>();
		Cookie[] cookies = request.getCookies();
		Cookie userCookie = null;
		String param = null;
		String value = null;
		int involvementCount = 0;
		String temp[];
		
		if(type == 0)
			System.out.println("***************** ADD EXTERNAL INVOLVEMENTS ************************");
		else
			System.out.println("***************** ADD INTERNAL INVOLVEMENTS ************************");
		
		for (Cookie c: cookies) {
			if(c.getName().equals("USER")) {
				System.out.println("Cookie found!");
				System.out.println("Cookie name: " +  c.getName());
				System.out.println("Cookie Value: " + c.getValue());
				userCookie = c;
			}
		}
		
		while(e.hasMoreElements()) {
			param = e.nextElement();
			value = request.getParameter(param);
			
			System.out.println("Name: " + param + " | Value: + " + value);
			paramNames.add(param);
			involvementCount++;
		}
		
		involvementCount /= 3;
		for(int i = 0; i < involvementCount; i++) {
			involvementList.add(new Involvement());
		}
		
		for(String parameter : paramNames) {
			temp = parameter.split("-");
			//0 = name
			//1 = fieldIndex
			//2 = Database status (0 for nothing, else is at db.)
			
			if(parameter.matches(".*Year.*")) {
				involvementList.get(Integer.parseInt(temp[1])).setAcadYear(Integer.parseInt(request.getParameter(parameter)));
			}
			
			else if (parameter.matches(".*Org.*")) {
				involvementList.get(Integer.parseInt(temp[1])).setiName(request.getParameter(parameter));
			}
			
			else {
				involvementList.get(Integer.parseInt(temp[1])).setPosition(request.getParameter(parameter));
				involvementList.get(Integer.parseInt(temp[1])).setId(Integer.parseInt(temp[2]));

			}
			
		}
		
		for(Involvement i : involvementList) {
			i.setInternal(type);
			i.setIdNum(Integer.parseInt(userCookie.getValue()));
			System.out.println(i.toString());
			StudentService.addOrUpdateInvolvements(i);
		}
		
		
		System.out.println("Involvements addeed/Updated!");
		System.out.println("***********************************************************************************");
	}
	

	/**
	 * Deletes an involvement of the user.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteInvolvement(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		System.out.println("***************** DELETING INVOLVEMENT ************************");
		
		System.out.println("Involvement " + " Deleted!");
		System.out.println("***********************************************************************************");
	}
}
