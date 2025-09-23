package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home1")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		// create connect to mysql server
		ArrayList<Student> studentList = new ArrayList<Student>();
		
//		String names = "";
		try {
			String requestParameter = request.getParameter("class");
			
			if (requestParameter == null) {
				requestParameter = "be8";
			}
			
			String url =  "jdbc:mysql://localhost:3306/student_db?serverTimezone=Australia/Adelaide"; // Replace with your database details
			String username = "root"; // Replace with your MySQL username
			String password = ""; // Replace with your MySQL password
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			
			System.out.println("Database connected successfully!");
			// Perform database operations here
			
			// use the connection to execute the sql query
			String SqlQuery = "SELECT * FROM students WHERE class_name = '" + requestParameter + "'";
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SqlQuery);

			while(resultSet.next()){
				Student student = new Student(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("class_name"));
				studentList.add(student);
			}
			
	        request.setAttribute("studentList", studentList);

	        // Get the RequestDispatcher for the target JSP
	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student-list.jsp");

	        // Forward the request to the JSP
	        dispatcher.forward(request, response);
		
//			if (names == "") {
//				names = "there is no student";
//			}
		} catch (SQLException e) {
			System.err.println("Error connecting to the database: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			// to connect JSP file.

		}
        
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

