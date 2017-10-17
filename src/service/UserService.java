package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;

import beans_model.Involvement;
import beans_model.Student;

/**
 * 
 *All services logic / business procces / business logic will be placed here. (That includes the database as well.)
 *
 */
public class UserService {

	
	public UserService() {
		
	}

	/**
	 * Validates if the user is existing or not.
	 * @param username
	 * @param password
	 * @return true or false
	 */
	public static boolean validateUser(String username, String password) {
		boolean found = false;
		PasswordAuthentication p = new PasswordAuthentication();
		
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement st = conn.prepareStatement("SELECT * FROM student WHERE email = ?");
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
	 * Validates if the admin is existing or not.
	 * @param username
	 * @param password
	 * @return true or false
	 */
	public static boolean validateAdmin(String username, String password) {
		boolean found = false;
		PasswordAuthentication p = new PasswordAuthentication();
		
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement st = conn.prepareStatement("SELECT * FROM admin WHERE email = ?");
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
		
			while(rs.next()) {
				if(p.authenticate(password.toCharArray(), rs.getString("hashedPass"))) {
					System.out.println("Admin found, valid!");
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
	 * Retrieves the user ID.
	 * @param username
	 * @param password
	 * @return String userID
	 */
	public static String getUserID(String email) {
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
			
			if (id == 0){
				st = conn.prepareStatement("SELECT * FROM admin WHERE email = ?");
				st.setString(1, email);
				rs = st.executeQuery();
	
				while(rs.next()) {
					System.out.println("Id found!");
					id = rs.getInt("id");
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
		return Integer.toString(id);
		
	}

	/**
	 * Retrieves a list of student using their name.
	 * @param name
	 * @return List of student
	 */
	public static ArrayList<Student> getStudentByName(String name) {
		System.out.println();
		ArrayList<Student> students = new ArrayList<>();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();

			PreparedStatement st = conn.prepareStatement("SELECT * FROM student WHERE firstName LIKE ? OR middleName LIKE ? OR lastName LIKE ?");
			st.setString(1, name);
			st.setString(2, name);
			st.setString(3, name);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				students.add(new Student(rs.getInt("studentId"), 
						                 rs.getDate("birthday"),
										 Year.of(rs.getDate("dateEnrolled").getYear()), 
										 rs.getString("firstName"),
										 rs.getString("middleName"),
										 rs.getString("lastName"),
										 rs.getString("celNo"), 
										 rs.getString("telNo"), 
										 rs.getString("email"), 
										 rs.getString("address"), 
										 rs.getString("course"),
										 rs.getString("hashedPass"),
										 rs.getString("civil"),
										 rs.getString("citizen"),
										 rs.getString("gender")));
				System.out.println("Student Found!");
				break;
			} 
			
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		return students;
		
	}
	

	/**
	 * Retrieves a list of organization using their full title or acronym.
	 * @param title
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
			st.setString(1, title);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				found = true;
				orgs.add(new Involvement(rs.getInt("involvementId"), rs.getInt("idNum"), 
												 rs.getString("iName"), rs.getString("position"), 
												 Year.of(rs.getDate("acadYear").getYear())));
				System.out.println("Org Found!");
				break;
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
		return orgs;
		
	}
	

	/**
	 * Retrieves a list of project using their full title or acronym.
	 * @param title
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
			st.setString(1, title);
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
				
				break;
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
		return projects;
		
	}
	
	public static void addUser(Student student) {
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement(
					"INSERT INTO student (studentId, firstName, middleName, lastName, celNo, telNo, email, hashedPass, collegeId, course) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
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
			
			stmt.executeUpdate();
			
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
	}
	

	// TODO Remove this temporary class of adding admin class
	public static void addAdmin(int id, String pass, String email) {
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			PasswordAuthentication p = new PasswordAuthentication();
			
			PreparedStatement stmt =  conn.prepareStatement("INSERT INTO admin (adminId, hashedPass, email) VALUES (?, ?, ?)");
		
			stmt.setInt(1, id);
			stmt.setString(2, p.hash(pass.toCharArray()));
			stmt.setString(3, email);
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("Added 11526491 admin");
	}
	
	/**
	 * 
	 * @param id - id placed in cookie.
	 * @return the student object containing his/her information.
	 */
	public static Student getLoggedStudent(int id) {
		Student student = null;
		
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			
			PreparedStatement stmt =  conn.prepareStatement("SELECT * FROM Student natural join college WHERE id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("Get logged student loop.");
				student = new Student(rs.getInt("studentId"), 
						              rs.getDate("birthday"),
						              Year.of(rs.getDate("yearEnrolled").getYear()), 
						              rs.getString("firstName"),
						              rs.getString("middleName"), 
						              rs.getString("lastName"),
						              rs.getString("celNo"), 
						              rs.getString("telNo"), 
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
	
	public static void updateStudent(Student student) {
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement(
					"UPDATE student "
				  + "SET studentId=?, "//1
				      + "firstName=?, " //2
					  + "middleName=?,"//3
					  + "lastName=?, "//4
					  + "celNo=?, "//5
					  + "telNo=?, "//6
					  + "email=?, "//7
					  + "collegeId=?, " //8
	   				  + "course=?, " //9
					  + "address=?, " //10
					  + "birthday=?, " //11
					  + "civil=?, " //12
					  + "citizen=?, " //13
					  + "gender=?, " //14
					  + "country=?, " //15
					  + "province=?, " //16 
					  + "zip=?, " //17
					  + "city=? "//18
				 + "WHERE id=?" //19					
					);
			
			stmt.setInt(1, student.getStudentId());
			stmt.setString(2, student.getFirstName());
			stmt.setString(3, student.getMiddleName());
			stmt.setString(4, student.getLastName());
			stmt.setString(5, student.getCelNo());
			stmt.setString(6, student.getTelNo());
			stmt.setString(7, student.getEmail());
			stmt.setInt(8, Integer.parseInt(student.getCollege()));
			stmt.setString(9,student.getCourse());
			stmt.setString(10, student.getAddress());
			stmt.setDate(11, student.getTempDate());
			stmt.setString(12, student.getCivil());
			stmt.setString(13, student.getCitizen());
			stmt.setString(14, student.getGender());
			stmt.setString(15,student.getCountry());
			stmt.setString(16, student.getProvince());
			stmt.setInt(17, student.getZip());
			stmt.setString(18,  student.getCity());
			stmt.setInt(19, student.getDbID());
			
			stmt.executeUpdate();
			
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
	}
	
}
