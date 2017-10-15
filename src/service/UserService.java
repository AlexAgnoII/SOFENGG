package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			
			String query = "SELECT * FROM student WHERE email = '" + username + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
		
			while(rs.next()) {
				if(p.authenticate(password.toCharArray(), rs.getString("hashedPass"))) {
					System.out.println("User found, valid!");
					found = true;
					break;
				}
			}
			
			if(!found){
				query = "SELECT * FROM admin WHERE email = '" + username + "'";
				rs = st.executeQuery(query);
			
				while(rs.next()) {
					if(p.authenticate(password.toCharArray(), rs.getString("hashedPass"))) {
						System.out.println("Admin found, valid!");
						found = true;
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
			
			String query = "SELECT * FROM admin WHERE email = '" + username + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
		
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
	 * @return true or false
	 */
	public static String getUserID(String email) {
		System.out.println();
		int id = 0;
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			String query = "SELECT * FROM student";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			

			while(rs.next()) {
				String retrieveEmail = rs.getString("email");
				System.out.println("Email in db: " + retrieveEmail);
				if(email.equals(retrieveEmail)) {
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

	public static void addUser(Student student) {
		System.out.println();
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement(
					"INSERT INTO student (studentId, firstName, middleName, lastName, celNo, telNo, email, hashedPass) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
					);
			
			stmt.setInt(1, student.getStudentId());
			stmt.setString(2, student.getFirstName());
			stmt.setString(3, student.getMiddleName());
			stmt.setString(4, student.getLastName());
			stmt.setString(5, student.getCelNo());
			stmt.setString(6, student.getTelNo());
			stmt.setString(7, student.getEmail());
			stmt.setString(8, student.getHashedPass());
			
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
	
}
