package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Student;

public class StudentDAO {
	public Student getStudentByLoginAndPassword(String loginID, String password) {
		
		try {
			// make connection to DB
			String url =  "jdbc:mysql://localhost:3306/student_db?serverTimezone=Australia/Adelaide"; // Replace with your database details
			String dbUsername = "root"; // Replace with your MySQL username
			String dbPassword = ""; // Replace with your MySQL password
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
			
			// execute statement
			String SqlQuery = "SELECT name FROM students WHERE login_id = '" + loginID + "' AND password = '" + password + "'";
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SqlQuery);
			
			// read resultset
			while(resultSet.next()){
				return Student.builder().name(resultSet.getString("name")).build();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
