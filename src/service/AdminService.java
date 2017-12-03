package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import beans_model.Post;
import beans_model.Student;

public class AdminService {
	
	
	
	
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
	 * Retrieves the admin ID.
	 * @param username
	 * @param password
	 * @return String userID
	 */
	public static String getAdminID(String email) {
		System.out.println();
		int id = 0;
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();

			PreparedStatement st = conn.prepareStatement("SELECT * FROM admin WHERE email = ?");
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
	 * Retrieves a student using their idnum.
	 * @param  idNum - the idNumber being checked
	 * @return the student with that id number OR null if it doesnt exist
	 */
	public static Student getStudentByIdNum(int idNum) {
		System.out.println();
		Student student = null;
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();

			PreparedStatement st = conn.prepareStatement("SELECT * FROM sofengg.student WHERE studentId "
													   + "= ? LIMIT 1");
			st.setInt(1, idNum );
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				student = new Student(rs.getInt("studentId"), 
						                 rs.getDate("birthday"),
										 rs.getDate("yearEnrolled") == null ?
												 null :
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
				System.out.println("Student " + rs.getString("firstName") + " Found!");
			} 
			
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		return student;
	}
	


	/**
	 * Retrieves a list of post
	 * @return List of posts made by the admins
	 */
	public static ArrayList<Post> getPosts() {
		System.out.println();
		ArrayList<Post> posts = new ArrayList<>();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();

			PreparedStatement st = conn.prepareStatement("SELECT * FROM sofengg.post order by postid desc;");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				posts.add(new Post(rs.getInt("postId"), rs.getString("title"), rs.getString("body"), new Date(rs.getTimestamp("date").getTime())));
				System.out.println("Post: " + rs.getString("title"));
			} 
			
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		return posts;
		
	}

	
	/**
	 * Retrieves a list of student who are eligible for an award.
	 * @return List of student or NULL should there be no student found.
	 */
	public static ArrayList<Student> getStudentsEligibleAward(String name) {
		System.out.println();
		ArrayList<Student> students = new ArrayList<>();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();

			PreparedStatement st = conn.prepareStatement("SELECT student.* FROM sofengg.student, sofengg.involvement WHERE " +
														 "studentId = idNum AND (firstName LIKE ? OR middleName " +
														 "LIKE ? OR lastName LIKE ?) GROUP BY studentId HAVING COUNT(*) >= 5");
			st.setString(1, name + "%");
			st.setString(2, name + "%");
			st.setString(3, name + "%");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				students.add(new Student(rs.getInt("studentId"), 
						                 rs.getDate("birthday"),
										 rs.getDate("yearEnrolled") == null ?
												 null :
												 Year.of(rs.getDate("yearEnrolled").getYear()), 
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
										 rs.getString("gender")));
				System.out.println("Student " + rs.getString("firstName") + " Found!");
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
	 * Retrieves a list of student using their name.
	 * @param name - name of the student
	 * @return List of student that has that name or NULL if no student/s was found.
	 */
	public static ArrayList<Student> getStudentByName(String name) {
		System.out.println();
		ArrayList<Student> students = new ArrayList<>();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();

			PreparedStatement st = conn.prepareStatement("SELECT * FROM sofengg.student WHERE firstName "
													   + "LIKE ? OR middleName LIKE ? OR lastName LIKE ?");
			st.setString(1, name + "%");
			st.setString(2, name + "%");
			st.setString(3, name + "%");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				String college  = "";

//				SELECT cName FROM sofengg.student, college WHERE college.collegeId = student.collegeId and studentId = '11237461';
				
				PreparedStatement st2 = conn.prepareStatement("SELECT cName FROM sofengg.student, sofengg.college WHERE " +
															  "college.collegeId = student.collegeId AND studentId = ?");
				st2.setInt(1, rs.getInt("studentId"));
				ResultSet rs2 = st2.executeQuery();
				if(rs2.next())
					college = rs2.getString("cName");
				
				Student student = new Student(rs.getInt("studentId"), 
							                  rs.getDate("birthday"),
											  rs.getDate("yearEnrolled") == null ?
																		    null :
																			Year.of(rs.getDate("yearEnrolled").getYear()), 
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
				student.setCollege(college);
				students.add(student);
				System.out.println("Student " + rs.getString("firstName") + " Found!");
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
	 * Creates a new post
	 * @param title - title of the post
	 * @param body  - body of the post
	 * @return the post that was created or NULL if no post was created.
	 */
	public static Post createPost(String title, String body) {
		System.out.println();
		Post post = null;
		long timeNow = Calendar.getInstance().getTimeInMillis();
		Timestamp ts = new Timestamp(timeNow);
			
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();

			PreparedStatement st = conn.prepareStatement("INSERT INTO `sofengg`.`post` (`title`, `body`, `date`) " +
													     "VALUES (?, ?, ?);");
			st.setString(1, title);
			st.setString(2, body);
			st.setTimestamp(3, ts);
			st.executeUpdate();
			
			System.out.println("Posted: " + title +"!"); 
			post = new Post(title, body, new Date(timeNow));
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		return post;
		
	}
	

	/**
	 * Updates a post
	 * @param postId - ID of the post
	 * @param title  - title of the post
	 * @param body   - body of the post
	 * @return the post that was created or NULL if no post was created.
	 */
	public static Post updatePost(int postId, String title, String body) {
		System.out.println();
		Post post = null;
		long timeNow = Calendar.getInstance().getTimeInMillis();
		Timestamp ts = new Timestamp(timeNow);
		
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();

			PreparedStatement st = conn.prepareStatement("UPDATE sofengg.post SET " +
														 "title = ?, body = ?, date = ? WHERE postId = ?;");
			st.setString(1, title);
			st.setString(2, body);
			st.setTimestamp(3, ts);
			st.setInt(4, postId);
			st.executeUpdate();
			
			System.out.println("Updated: " + title +"!"); 
			post = new Post(title, body, new Date(timeNow));
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		return post;
		
	}
	
	/**
	 * Checks if email is existing in the admin table.
	 * @param email
	 * @return
	 */
	public static boolean isExisting(String email) {
		boolean found = true;
		
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();

			PreparedStatement st = conn.prepareStatement("SELECT * FROM admin WHERE email = ?");
			st.setString(1, email);
			ResultSet rs = st.executeQuery();


		    //Email does not exist.
			if(!rs.isBeforeFirst()) {
				System.out.println("Email does not exist!(ADMIN)");
				return false;
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

	public static String convertTokenToEmail(String token) {
		// TODO Auto-generated method stub
		String email = "NONE";
		
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();

			PreparedStatement st = conn.prepareStatement("SELECT * FROM admin");
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
	 * rESET admin password
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
					"UPDATE admin "
				  + "SET hashedPass=? "//1
				 + "WHERE email=?" //2					
					);
			
			stmt.setString(1, hashPass);
			stmt.setString(2, email);
			
			stmt.executeUpdate();
			
			System.out.println("Change password success(ADMIN)!");
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();

	}


	/**
	 * Checks if the entered password is equal to the logged account.
	 * @param id
	 * @param password
	 */
	public static boolean checkIfPasswordMatches(String id, String password) {
		boolean matches = true;
		
		PasswordAuthentication p;
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement st = conn.prepareStatement("SELECT * FROM admin "
					+ "WHERE id = ? ");
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			
			//Account DOESNT exist for some reason
			if(!rs.isBeforeFirst()) {
				System.out.println("No Account exist ERROR!!");
				matches = false;
			}
			
			//Account exist, proceed to check if its pass is equal t
			else {
				while(rs.next()) {
					String email = rs.getString("email");
					System.out.println("Email: " + email);
					p = new PasswordAuthentication(email, password);
					
					//If false returned, it didnt match.
					if(!p.authenticate(password.toCharArray(), rs.getString("hashedPass"))) {
						System.out.println("Password did not match..");
						matches = false;
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
		return matches;
	}

	public static String getEmailViaID(String id) {
		String email = "ERROR";
		
		PasswordAuthentication p;
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement st = conn.prepareStatement("SELECT * FROM admin "
					+ "WHERE id = ? ");
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			
			//Account DOESNT exist for some reason
			if(!rs.isBeforeFirst()) {
				System.out.println("No Account exist ERROR!!");
			}
			
			//Account exist, proceed to check if its pass is equal t
			else {
				while(rs.next()) {
					email = rs.getString("email");
					System.out.println("Email for sending: " + email);
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
		return email;
	}

	public static void changePassword(String id, String hashedPass) {
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement(
					"UPDATE admin "
				  + "SET hashedPass=? "//1
				 + "WHERE id=?" //2					
					);
			
			stmt.setString(1, hashedPass);
			stmt.setString(2, id);
			
			stmt.executeUpdate();
			
			System.out.println("Change password success(ADMIN)!");
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println();
		
	}


}
