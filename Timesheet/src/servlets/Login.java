package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import database.ConnectionFactory;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        
        String userName=request.getParameter("txt_username");  
        String password=request.getParameter("txt_password"); 
        
        HttpSession session = request.getSession(false);
        if(session==null){
        	System.err.println("FileUploadServlet, session was null, just returning.");
            return;
        }
        else{
        	 session.setAttribute("userName", userName);
        }
        
        try {
			if(validateUsernamePassword(userName,password)){  
				response.sendRedirect("Home.jsp");
			}  
			else{  
			     response.sendRedirect("login.jsp");  
			     out.print("<p style=\"color:red\">Sorry username or password error</p>");  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
        
        }
	
	private boolean validateUsernamePassword(String username, String password) throws SQLException{
		 ResultSet rs = null; 
		 boolean status = false;
		 Connection con = null;
		 PreparedStatement preparedStatement = null;
		 con = ConnectionFactory.createConnection();
		 String query = "select * from users where username=? and password=?";
		 preparedStatement = con.prepareStatement(query);
		 preparedStatement.setString(1, username);
		 preparedStatement.setString(2, password);
		 rs= preparedStatement.executeQuery();
		 status = rs.next();
		 System.out.println(status);
		 return status;
	}

}
