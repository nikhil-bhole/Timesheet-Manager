package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ConnectionFactory;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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
		if (request.getParameter("btn_delete") != null) {
			String key = request.getParameter("param2");
			String loggeduser = request.getParameter("loggedUser");
			System.out.println(loggeduser);
			System.out.println(key); 
			Connection con = null;
			 PreparedStatement preparedStatement = null;
			 con = ConnectionFactory.createConnection();
			 String query = "delete from dailyrecord where date = '" + key + "' and user_id = '" + loggeduser + "'" ;
			 try {
					preparedStatement = con.prepareStatement(query);
					int i = preparedStatement.executeUpdate();
					System.out.println(i);
					if(i>0){
						response.sendRedirect("Home.jsp");
					}else{
						response.sendRedirect("error.jsp");
					}
				  } catch(SQLException e){
					 e.printStackTrace();
				 }finally {
					    if (preparedStatement != null) {
					        try {
					        	preparedStatement.close();
					        } catch (SQLException e) { /* ignored */}
					    }
					    if (con != null) {
					        try {
					            con.close();
					        } catch (SQLException e) { /* ignored */}
					    }
					}
			
		}
	}

}
