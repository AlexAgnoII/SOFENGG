package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;

import beans_model.Involvement;
import beans_model.Relative;
import beans_model.Student;

/**
 * 
 *All services logic / business procces / business logic 
 *for student's use will be placed here. (That includes the database as well.)
 *
 */
public class StudentService {

	
	public StudentService() {}
	
	/**
	 * Checks if the username (email) has already been taken.
	 * @param username - the email
	 * @return true (if taken) or false (not taken)
	 */
	public static boolean isEmailTaken(String username) {
		boolean isTaken = false;
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement st = conn.prepareStatement("SELECT * FROM student WHERE email = ?");
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			
			//Checks if result set has any data returned.
			if (!rs.isBeforeFirst()) {    
			    System.out.println("Email not yet taken."); 
			} 
			else {
				System.out.println("Email is taken.");
				isTaken = true;
			}
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		return isTaken;
	}
	
	/**
	 * Checks if the idNumber entered is taken or not.
	 * @param idnum - idNumber to be checked.
	 * @return true (taken) or false (not taken)
	 */
	public static boolean isIdNumberTaken(int idnum) {
		boolean isTaken = false;
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement st = conn.prepareStatement("SELECT * FROM student WHERE studentId = ?");
			st.setInt(1, idnum);
			ResultSet rs = st.executeQuery();
			
			//Checks if result set has any data returned.
			if (!rs.isBeforeFirst() ) {    
			    System.out.println("iDnumber not yet taken."); 
			} 
			else {
				System.out.println("iDnumber is taken.");
				isTaken = true;
			}
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		return isTaken;
	}

	/**
	 * Validates if the student is existing or not.
	 * @param username - entered username(email)
	 * @param password - entered password
	 * @return true or false
	 */
	public static boolean validateStudent(String username, String password) {
		boolean found = false;
		PasswordAuthentication p = new PasswordAuthentication(username, password);
		
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement st = conn.prepareStatement("SELECT * FROM student WHERE email = ? ");
			st.setString(1, username);
			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				if(p.authenticate(password.toCharArray(), rs.getString("hashedPass"))) {
					System.out.println("User found, valid!");
					found = true;
					break;
				}
			}

			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		return found;
	}
	
	/**
	 * Checks if the username i validated or not.
	 * @param username - the username
	 * @return
	 */
	public static boolean isValidated(String username) {
		boolean validated = true;
		
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();

			PreparedStatement st = conn.prepareStatement("SELECT * FROM student WHERE email = ? " +
					                                      "AND (verificationId IS NULL OR verificationId = \'\')");
			st.setString(1, username);
			ResultSet rs = st.executeQuery();


			//No result was returned. ( Not verified!)
			if(!rs.isBeforeFirst()) {
				System.out.println("Not yet Verified!");
				validated =  false;
			}


			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		return validated;
	}
	
	/**
	 * Checks if the email exist in the database.
	 * @param email
	 * @return true or false
	 */
	public static boolean isExisiting(String email) {
		boolean found = true;
		
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();

			PreparedStatement st = conn.prepareStatement("SELECT * FROM student WHERE email = ?");
			st.setString(1, email);
			ResultSet rs = st.executeQuery();


			//Email does not exist.
			if(!rs.isBeforeFirst()) {
				System.out.println("Email does not exist!");
				found =  false;
			}


			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		return found;
	}
	

	/**
	 * Checks if a student is eligible for an award
	 * (at least 1 involvement)
	 * @param studentDBId - DB ID of the student
	 * @return true or false
	 */
	public static boolean eligibleAward(int studentDBId) {
		boolean found = false;
		PasswordAuthentication p = new PasswordAuthentication();
		
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			PreparedStatement st = conn.prepareStatement("SELECT student.studentId FROM sofengg.student, "  +
														 "sofengg.involvement WHERE studentId = idNum AND " +
														 "student.studentId = ? " 							+ 
														 "GROUP BY studentId HAVING COUNT(*) >= 5");

			st.setInt(1, studentDBId);
			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				System.out.println("User is eligible for an award!");
				found = true;
			}

			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		return found;
	}
	
	/**
	 * Retrieves the user ID (on DB, not the same with ID number of student..
	 * @param username
	 * @param password
	 * @return String userID OR 0 (not existing)
	 */
	public static String getStudentID(String email) {
		System.out.println();
		int id = 0;
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();

			PreparedStatement st = conn.prepareStatement("SELECT * FROM student WHERE email = ?");
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				System.out.println("Id found!");
				id = rs.getInt("id");
				break;
			} 
				
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		return Integer.toString(id);
		
	}

	

	/**
	 * Retrieves a list of organization using their full title or acronym.
	 * @param title - title/acronym of organization 
	 * @return List of organization 
	 */
	public static ArrayList<Involvement> getOrgList(String title) {
		System.out.println();
		ArrayList<Involvement> orgs = new ArrayList<>();
		Boolean found = false;
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();

			
			PreparedStatement st = conn.prepareStatement("SELECT i.* FROM sofengg.involvementhandler iH"     + 
													     " RIGHT JOIN involvement i ON iH.involvementID"      +
													     " = i.involvementId WHERE iH.involvementID IS NULL" +
													     " AND i.iName LIKE ? GROUP BY i.iName");
			st.setString(1, title + "%");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				found = true;
				orgs.add(new Involvement(rs.getInt("involvementId"), rs.getInt("idNum"), 
												 rs.getString("iName"), rs.getString("position"), 
												 Year.of(rs.getDate("acadYear").getYear())));
				System.out.println("Org Found!");
			} 
			
			if (!found){
				st = conn.prepareStatement("SELECT i.* FROM sofengg.involvementhandler iH" 	   + 
										   " RIGHT JOIN involvement i ON iH.involvementID"      +
										   " = i.involvementId WHERE iH.involvementID IS NULL" +
										   " GROUP BY iName");
				rs = st.executeQuery();
				
				while(rs.next()) {
					String[] iName = rs.getString("iName").split(" ");
					found = false;
					
					for(int i = 0; i < title.length() && iName.length != i; i++){
						if(title.charAt(i) == iName[i].charAt(0))
							 found = true;
						else found = false;
					}
					
					if(found){
						orgs.add(new Involvement(rs.getInt("involvementId"), rs.getInt("idNum"), 
								 rs.getString("iName"), rs.getString("position"), 
								 Year.of(rs.getDate("acadYear").getYear())));
						System.out.println("Org Found!");
					}
				} 
			}
			
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		return orgs;
		
	}
	

	/**
	 * Retrieves a list of project using their full title or acronym.
	 * @param title of involvement/project
	 * @return List of project
	 */
	public static ArrayList<Involvement> getProjList(String title) {	// TODO debug
		System.out.println();
		ArrayList<Involvement> projects = new ArrayList<>();
		ArrayList<String> handler = new ArrayList<>();
		Boolean found = false,
				initial = false;
		String initialName = "";
		
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();

			PreparedStatement st = conn.prepareStatement("SELECT i.*, ih.handler FROM sofengg.involvementhandler iH" + 
													     " INNER JOIN involvement i ON iH.involvementID"     		 +
													     " = i.involvementId WHERE i.iName LIKE ? GROUP BY i.iName"  +
													     " ORDER BY i.iName");
			st.setString(1, title + "%");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				
				found = true;
				if(!initialName.equals(rs.getString("iName"))){
					
					if(projects.size() == 0) {
						handler.add(rs.getString("handler")); 
						Involvement involvement = new Involvement(rs.getInt("involvementId"), rs.getInt("idNum"), 
																 rs.getString("iName"), rs.getString("position"), 
																 Year.of(rs.getDate("acadYear").getYear()));
						involvement.setHandler(handler);
						projects.add(involvement);
						
					} else{
						// Update previous project
						Involvement involvement = projects.get(projects.size() - 1);
						involvement.setHandler(handler);
						projects.remove(projects.size() - 1);
						projects.add(involvement);
						
						// Add new project
						handler = new ArrayList<>();
						handler.add(rs.getString("handler")); 
						involvement = new Involvement(rs.getInt("involvementId"), rs.getInt("idNum"), 
												 	  rs.getString("iName"), rs.getString("position"), 
													  Year.of(rs.getDate("acadYear").getYear()));
						handler.add(rs.getString("handler")); 
						projects.add(involvement);
					}
					
					System.out.println("Projects Found!");
					initialName = rs.getString("iName");
				} else handler.add(rs.getString("handler"));	// More than 1 handler
				
			} 
			
			if (!found){
				st = conn.prepareStatement("SELECT i.*, ih.handler FROM sofengg.involvementhandler iH" + 
									       " INNER JOIN involvement i ON iH.involvementID"     		 +
									       " = i.involvementId GROUP BY i.iName ORDER BY i.iName");
				rs = st.executeQuery();
				
				while(rs.next()) {
					String[] iName = rs.getString("iName").split(" ");
					found = false;
					
					for(int i = 0; i < title.length() && iName.length != i; i++){
						if(title.charAt(i) == iName[i].charAt(0))
							 found = true;
						else found = false;
					}
					
					if(found){
						

						found = true;
						if(!initialName.equals(rs.getString("iName"))){
							
							if(projects.size() == 0) {
								handler.add(rs.getString("handler")); 
								Involvement involvement = new Involvement(rs.getInt("involvementId"), rs.getInt("idNum"), 
																		 rs.getString("iName"), rs.getString("position"), 
																		 Year.of(rs.getDate("acadYear").getYear()));
								involvement.setHandler(handler);
								projects.add(involvement);
								
							} else{
								// Update previous project
								Involvement involvement = projects.get(projects.size() - 1);
								involvement.setHandler(handler);
								projects.remove(projects.size() - 1);
								projects.add(involvement);
								
								// Add new project
								handler = new ArrayList<>();
								handler.add(rs.getString("handler")); 
								involvement = new Involvement(rs.getInt("involvementId"), rs.getInt("idNum"), 
														 	  rs.getString("iName"), rs.getString("position"), 
															  Year.of(rs.getDate("acadYear").getYear()));
								handler.add(rs.getString("handler")); 
								projects.add(involvement);
							}
							
							System.out.println("Projects Found!");
							initialName = rs.getString("iName");
						} else handler.add(rs.getString("handler"));	// More than 1 handler
					}
				} 
			}
			
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		return projects;
		
	}
	
	/**
	 * Adds the studennt to the database.
	 * @param student - the Student object being added.
	 */
	public static void addStudent(Student student, String verificationId) {
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement(
					"INSERT INTO student (studentId, firstName, middleName, lastName, " +
					"celNo, telNo, email, hashedPass, collegeId, course, verificationId) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
					);
			
			stmt.setInt(1, student.getStudentId());
			stmt.setString(2, student.getFirstName());
			stmt.setString(3, student.getMiddleName());
			stmt.setString(4, student.getLastName());
			stmt.setString(5, student.getCelNo());
			stmt.setString(6, student.getTelNo());
			stmt.setString(7, student.getEmail());
			stmt.setString(8, student.getHashedPass());
			stmt.setInt(9, Integer.parseInt(student.getCollege()));
			stmt.setString(10,student.getCourse());
			stmt.setString(11, verificationId);
			
			stmt.executeUpdate();
			
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
	}
	
	public static void verifyStudent(String hash) {
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement(
					"UPDATE student "
				  + "SET verificationId=? "//1
				 + "WHERE verificationId=?" //2					
					);
			
			stmt.setString(1, "");
			stmt.setString(2, hash);
			
			stmt.executeUpdate();
			
			System.out.println("Student verified!");
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
	}
	
	
	/**
	 * Retrieves the info of the logged-in student.
	 * @param id - id placed in cookie.
	 * @return the student object containing his/her information.
	 */
	public static Student getLoggedStudent(int id) {
		Student student = null;
		int year;
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM Student natural join college WHERE id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("Get logged student loop.");
				
				if (rs.getDate("yearEnrolled") == null) {
					year = 0;
				}
				
				else year = rs.getDate("yearEnrolled").getYear();
				
				student = new Student(rs.getInt("studentId"), 
						              rs.getDate("birthday"),
						              Year.of(year), 
						              rs.getString("firstName"),
						              rs.getString("middleName"), 
						              rs.getString("lastName"),
						              rs.getString("telNo"), 
						              rs.getString("celNo"), 
						              rs.getString("email"), 
						              rs.getString("address"), 
						              rs.getString("course"), 
						              rs.getString("hashedPass"),
						              rs.getString("civil"), 
						              rs.getString("citizen"),
						              rs.getString("gender"));
				student.setCollege(rs.getString("cName"));
				student.setCity(rs.getString("city"));
				student.setProvince(rs.getString("province"));
				student.setCountry(rs.getString("country"));
				student.setZip(rs.getInt("zip"));
				student.calculateAge(rs.getDate("birthday"));
			}
			
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		System.out.println("Student retrieved!");
		return student;
	}

	/**
	 * Retrieves the student's ID number
	 * @param id
	 * @return the student with the id number
	 */
	public static int getStudentIDNum(int id) {
		int idNum = 0;
		
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM Student natural join college WHERE id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("Get logged student id loop.");
				idNum = rs.getInt("studentId");
				
			}
			
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		System.out.println("ID retrieved!");
		return idNum;
	}

	
	/**
	 * Updates the student
	 * @param student
	 */
	public static void updateStudent(Student student) {
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement(
					"UPDATE student "
				  + "SET celNo=?, "//1
					  + "telNo=?, "//2
					  + "address=?, " //3
					  + "birthday=?, " //4
					  + "civil=?, " //5
					  + "citizen=?, " //6
					  + "gender=?, " //7
					  + "country=?, " //8
					  + "province=?, " //9 
					  + "zip=?, " //10
					  + "city=? "//11
				 + "WHERE id=?" //12					
					);
			
			stmt.setString(1, student.getCelNo());
			stmt.setString(2, student.getTelNo());
			stmt.setString(3, student.getAddress());
			stmt.setDate(4, student.getTempDate());
			stmt.setString(5, student.getCivil());
			stmt.setString(6, student.getCitizen());
			stmt.setString(7, student.getGender());
			stmt.setString(8,student.getCountry());
			stmt.setString(9, student.getProvince());
			stmt.setInt(10, student.getZip());
			stmt.setString(11,  student.getCity());
			stmt.setInt(12, student.getDbID());
			
			stmt.executeUpdate();
			
			System.out.println("Update success for "+ student.getDbID() + "!");
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
	}

	
	/**
	 * Updates studennt academic information.
	 * @param student - student to update
	 */
	public static void updateStudentAcadInfo(Student student) {
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement(
					"UPDATE student "
				  + "SET studentId=?, " //1
					  + "course=?,"//2
					  + "college=?, "//3
				 + "WHERE id=?" //4					
					);
			
			stmt.setInt(1, student.getStudentId());
			stmt.setString(2, student.getCourse());
			stmt.setString(3, student.getCollege());
			stmt.setInt(9, Integer.parseInt(student.getCollege()));
			
			stmt.executeUpdate();
			
			System.out.println("Update acad info success for "+ student.getDbID() + "!");
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
	}

	
	/**
	 * Updates the relatives
	 * @param relative
	 */
	public static void updateRelatives(Relative relative) {
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement(
					"UPDATE relative "
				  + "SET name=?,"//1
					  + "occupation=?, "//2
					  + "birthday=? "//3
				 + "WHERE relativeId=?" + " AND type=?" //4 & 5				
					);
			
			stmt.setString(1, relative.getName());
			stmt.setString(2, relative.getOccupation());
			stmt.setDate(3, relative.getTempDate());
			stmt.setInt(4, relative.getRelativeId());
			stmt.setString(5, relative.getType());
			
			stmt.executeUpdate();
			
			System.out.println("Update success for "+ relative.getRelativeId() + "!");
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
	}

	/**
	 * ADds the new relative to the DB.
	 * @param relative
	 */
	private static void addRelatives(Relative relative) {
		// TODO Auto-generated method stub
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement(
					"INSERT INTO relative (studentDBId, " //1
				                       + "name," //2
							           + "type," //3
				                       + "occupation," //4
							           + "birthday)" //5
					                   + " VALUES (?, ?, ?, ?, ?)"
					);
			
			stmt.setInt(1, relative.getStudentId());
			stmt.setString(2, relative.getName());
			stmt.setString(3, relative.getType());
			stmt.setString(4, relative.getOccupation());
			stmt.setDate(5, relative.getTempDate());
			
			stmt.executeUpdate();
			
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("Added relative!");
		System.out.println();
	}

	/**
	 * Decides whether the service updates OR adds the relative.
	 * @param r
	 */
	public static void updateOrAddRelative(Relative r) {
		// TODO Auto-generated method stub
		
		//If relativeID is zero, then it is not yet recorded in the DB.
		if(r.getRelativeId() == 0) {
			System.out.println(r.getName() + ": is zero, add it.");
			addRelatives(r);
		}
		
		//update that field.
		else {
			System.out.println(r.getName() + ": is not zero, update it.");
			updateRelatives(r);
		}
		
	}

	public static ArrayList<Relative> getRelatives(int studentDBid) {
		// TODO Auto-generated method stub
		ArrayList<Relative> relativeList = new ArrayList<Relative>();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("select * from relative\r\n" + 
					                                        "WHERE studentDBid = ?");
			stmt.setInt(1, studentDBid);
			ResultSet rs = stmt.executeQuery();
			
			//No result was returned.
			if(!rs.isBeforeFirst()) {
				System.out.println("Doesnt have relatives yet.");
				return null;
			}
			
			//Result set returned! proceed.
			else {
				System.out.println("Has Relatives!");
				while(rs.next()) {
					relativeList.add(new Relative(rs.getInt("relativeId"),
							                      rs.getInt("studentDBId"),
												  rs.getString("name"),
												  rs.getString("type"),
												  rs.getString("occupation"),
												  rs.getDate("birthday")
							                     ));
				}
			}
			
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		
		return relativeList;
	}
	
	
	public static Relative getMother(ArrayList<Relative> relativeList) {
		for (Relative r : relativeList) {
			if(r.getType().equals("Mother")) {
				return r;
			}
		}
		
		//No mother found.
		return null;
	}
	
	public static Relative getFather(ArrayList<Relative> relativeList) {
		for (Relative r : relativeList) {
			if(r.getType().equals("Father")) {
				return r;
			}
		}
		
		//No Father found.
		return null;
	}
	
	
	public static ArrayList<Relative> getSiblings(ArrayList<Relative> relativeList) {
		ArrayList<Relative> siblings = new ArrayList<Relative>();
		
		for(Relative r : relativeList) {
			if(r.getType().equals("Sibling")) {
				siblings.add(r);
			}
		}
		
		
		if(siblings.size() == 0)
			return null;
		else return siblings;
		
	}
	
	public static int getSiblingCount(ArrayList<Relative> relativeList) {
		int sum = 0;
		
		for (Relative r : relativeList) {
			sum++;
		}
		
		return sum;
	}
	
	
	/**
	 * ADdes involvments
	 * @param involvement
	 */
	@SuppressWarnings("deprecation")
	public static void addInvolvements(Involvement involvement) {
		System.out.println();
		
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement(
					"INSERT INTO involvement (iName, studentDBId, position, acadYear, internal) VALUES (?, ?, ?, ?, ?)"
					);
			
			stmt.setString(1, involvement.getiName());
			stmt.setInt(2, involvement.getIdNum());
			stmt.setString(3, involvement.getPosition());
			stmt.setInt(4, involvement.getAcadYear());
			stmt.setInt(5, involvement.getInternal());

			stmt.executeUpdate();
			
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
	}
	

	/**
	 * Updates the involvement.
	 * @param involvement
	 */
	private static void updateInvolvements(Involvement involvement) {
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement(
					"UPDATE involvement "
				  + "SET iName=?,"//1
					  + "position=?, "//2
					  + "acadYear=? "//3
				 + "WHERE involvementId=?" + " AND studentDBId=?" //4 & 5				
					);
			
			stmt.setString(1, involvement.getiName());
			stmt.setString(2, involvement.getPosition());
			stmt.setInt(3, involvement.getAcadYear());
			stmt.setInt(4, involvement.getId());
			stmt.setInt(5, involvement.getIdNum());
			
			stmt.executeUpdate();
			
			System.out.println("Update success for "+ involvement.getId() + "!");
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		
	}
	
	/**
	 * Decided whether involvements are added or not.
	 */
	public static void addOrUpdateInvolvements(Involvement involvement) {
		// TODO Auto-generated method stub
		
		//If zero, its not yet on DB.
		if(involvement.getId() == 0) {
			System.out.println("Not yet added, proceed in adding..");
			addInvolvements(involvement);
			
		}
		
		else {
			System.out.println("Added! Proceed in updating..");
			updateInvolvements(involvement);
		}
		
	}

	public static ArrayList<Involvement> getSpecificStudentInvolvement(int studentDBid, int type) {
		ArrayList<Involvement> involvementList = new ArrayList<Involvement>();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("select * from involvement\r\n" + 
					                                        "WHERE studentDBid = ? AND internal=?");
			stmt.setInt(1, studentDBid);
			stmt.setInt(2, type);
			ResultSet rs = stmt.executeQuery();
			
			//No result was returned.
			if(!rs.isBeforeFirst()) {
				System.out.println("Doesnt have involvements yet.");
				return null;
			}
			
			//Result set returned! proceed.
			else {
				System.out.println("Has Relatives!");
				while(rs.next()) {
					involvementList.add(new Involvement(rs.getInt("involvementId"),
														rs.getInt("studentDBId"),
														rs.getString("iName"),
														rs.getString("position"),
														rs.getInt("acadYear"),
														rs.getInt("internal")
							));
				}
			}
			
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		
		return involvementList;
	}
	
	
	public static int getCountInvolvements(ArrayList<Involvement> involvementList) {
		int sum = 0;
		for(Involvement i : involvementList) {
			sum++;
		}
		
		return sum;
	}
	
	/**
	 * Converts the token into an email if it exists.
	 * @param token
	 * @return
	 */
	public static String convertTokenToEmail(String token) {
		// TODO Auto-generated method stub
		String email = "NONE";
		
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();

			PreparedStatement st = conn.prepareStatement("SELECT * FROM student");
			ResultSet rs = st.executeQuery();


			//Email does not exist.
			if(!rs.isBeforeFirst()) {
				System.out.println("Email does not exist!");
				return email;
			}
			else {
				
				PasswordAuthentication p = new PasswordAuthentication();
				while(rs.next()) {
					if(p.authenticate(rs.getString("email").toCharArray(), token)) {
						System.out.println("Matches!!!");
						email = rs.getString("email");
						break;
					}
				}

			}


			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		return email;
	}
	
	/**
	 * reset admin password
	 * @param email
	 * @param password
	 */
	public static void resetPassword(String email, String password) {
		PasswordAuthentication p = new PasswordAuthentication();
		
		String hashPass = p.hash(password.toCharArray());
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement(
					"UPDATE student "
				  + "SET hashedPass=? "//1
				 + "WHERE email=?" //2					
					);
			
			stmt.setString(1, hashPass);
			stmt.setString(2, email);
			
			stmt.executeUpdate();
			
			System.out.println("Change password success(STUDENT)!");
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		
	}







}
