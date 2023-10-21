package com.online.Edviron;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Edviron {

	
		@PostMapping("/api/register")
		public String register(String name, String email, String password, String address, String phone, String type) {
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Edviron", "root", "Pknice99@");

		        String query;
		        if(type.equals("student")) {
		            query = "INSERT INTO student (name, email, password, shipping_address, phone) VALUES (?, ?, ?, ?, ?)";
		        } else if(type.equals("school")) {
		            query = "INSERT INTO school (name, email, password, address, phone) VALUES (?, ?, ?, ?, ?)";
		        } else {
		            return "Invalid user type";
		        }

		        PreparedStatement pstmt = con.prepareStatement(query);
		        pstmt.setString(1, name);
		        pstmt.setString(2, email);
		        pstmt.setString(3, password);
		        pstmt.setString(4, address);
		        pstmt.setString(5, phone);

		        int i = pstmt.executeUpdate();

		        if (i > 0) {
		            return "You have been registered successfully as a " + type;
		        } else {
		            return "Error occurred while registering";
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return "";
		}
		
		
		
		
		
		
		@GetMapping("/api/login")
		public String login(String email,String password, String type) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Edviron", "root", "Pknice99@");

			Statement stmt= con.createStatement();
			String query="";
			if(type.equals("student")) {
	            query = "select password from student where email='"+email+"'";
	        } else if(type.equals("school")) {
	            query = "select password from school where email='"+email+"'";
	        } else {
	            return "Invalid user type";
	        }
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next()) {
				String pwd=rs.getString("password");
				if(pwd.equals(password)) {
					return"you are valid user";
				}else {
					return"password is wrong";
				}
					
			}
			else {
				return"you are not registered, first registered youself";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
			return "";
		}
		
	
		
		
		
		
		
		
		

		@PostMapping("/api/student/update")
		public String updatestudent(String name, String email, String password, String shipping_address, String phone) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Edviron", "root", "Pknice99@");
				String query = "UPDATE student SET name = ?, password = ?, shipping_address = ?, phone = ? WHERE email = ?";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, name);
				pstmt.setString(2, password);
				pstmt.setString(3, shipping_address);
				pstmt.setString(4, phone);
				pstmt.setString(5, email);
				int i = pstmt.executeUpdate();
				if (i > 0) {
					return "Your record has been updated successfully";
				} else {
					return "Error occurred while updating your record";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
		
		
		
		
		
		
		
		
		
		
		

		@PostMapping("/api/school/update")
		public String updateschool(String name, String email, String password, String address, String phone) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Edviron", "root", "Pknice99@");
				String query = "UPDATE school SET name = ?, password = ?, address = ?, phone = ? WHERE email = ?";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, name);
				pstmt.setString(2, password);
				pstmt.setString(3, address);
				pstmt.setString(4, phone);
				pstmt.setString(5, email);
				int i = pstmt.executeUpdate();
				if (i > 0) {
					return "Your record has been updated successfully";
				} else {
					return "Error occurred while updating your record";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}

		
		
		
		
		@DeleteMapping("/api/delete")
		public String deletestudent(String email,String type) {
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Edviron", "root", "Pknice99@");
		        String query = "DELETE FROM student WHERE email = ?";
		        if(type.equals("student")) {
		            query = "DELETE FROM student WHERE email = ?";
		        } else if(type.equals("school")) {
		            query = "DELETE FROM school WHERE email = ?";
		        } else {
		            return "Invalid user type";
		        }
		        PreparedStatement pstmt = con.prepareStatement(query);
		        pstmt.setString(1, email);
		        int i = pstmt.executeUpdate();
		        if (i > 0) {
		            return "Your record has been deleted successfully";
		        } else {
		            return "Error occurred while deleting your record";
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return "";
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
}
		
		
		
		

		