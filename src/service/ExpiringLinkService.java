package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ExpiringLinkService {
	
	public static final int MAX_MINS = 5;
	public static void save(String email, String token) {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		
		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement stmt =  conn.prepareStatement(
					"INSERT INTO expiration_table (email, token, time) " +
					"VALUES (?, ?, ?)"
					);
			
			stmt.setString(1, email);
			stmt.setString(2, token);
			stmt.setTimestamp(3, currentTime);
			
			stmt.executeUpdate();
			
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	

	public static boolean isPending(String email) {
		boolean pending = false;

		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement st = conn.prepareStatement("SELECT * FROM expiration_table WHERE email = ?");
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			
			//Checks if result set has any data returned.
			//Returns false if no result
			if (!rs.isBeforeFirst()) {    
			    System.out.println("Email not pending"); 
			} 
			else {
				System.out.println("Email is pending.");
				pending = true;
			}
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		System.out.println("Is it pending: " + pending);
		return pending;
	}
	
	public static boolean isBelow(String token) {
		boolean isBelow = false;
		Timestamp time = null;
		long max = 1000 * 60 * MAX_MINS;
		System.out.println("Token checking if below " + MAX_MINS + ": " + token );
		Timestamp currentTime, savedTimePrediction;

		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement st = conn.prepareStatement("SELECT * FROM expiration_table WHERE token = ?");
			st.setString(1, token);
			ResultSet rs = st.executeQuery();
			
			//Checks if result set has any data returned.
			if (!rs.isBeforeFirst()) {    
			    System.out.println("No results for some reason. (Token sadla)");
			} 
			else {
				while(rs.next()) {
					time = rs.getTimestamp("time");
					System.out.println("Timestamp: " + time);
					System.out.println("Miliseconds: " + time.getTime());
					
					currentTime = new Timestamp(System.currentTimeMillis());	
					
					//5 mins after prediction
					savedTimePrediction = new Timestamp(time.getTime() + max);
					
					System.out.println("Current time: " + currentTime);
					System.out.println("SavedTimePredict: " + savedTimePrediction);
					if(currentTime.getTime() < savedTimePrediction.getTime()) {
						isBelow = true;
					}
					
				}
			}
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		System.out.println("Is it below 5: " + isBelow);
		return isBelow;
	}


	public static void kill(String token) {
		// TODO Auto-generated method stub

		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement st = conn.prepareStatement("DELETE FROM expiration_table WHERE token = ?");
			st.setString(1, token); 
			st.execute();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
	}


	public static boolean isPendingToken(String token) {
		boolean pending = false;

		try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DatabaseManager.getConnection();
			
			PreparedStatement st = conn.prepareStatement("SELECT * FROM expiration_table WHERE token = ?");
			st.setString(1, token);
			ResultSet rs = st.executeQuery();
			
			//Checks if result set has any data returned.
			//Returns false if no result
			if (!rs.isBeforeFirst()) {    
			    System.out.println("Token is not pending"); 
			} 
			else {
				System.out.println("Token is pending.");
				pending = true;
			}
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		System.out.println("Is it pending: " + pending);
		return pending;
	}
}
