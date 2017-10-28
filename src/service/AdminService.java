package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;

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
}
