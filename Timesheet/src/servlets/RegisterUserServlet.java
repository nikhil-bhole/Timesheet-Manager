package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import dao.UserRegistrationDao;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserServlet() {
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
		UserBean userBean = new UserBean();
		UserRegistrationDao userdao = new UserRegistrationDao();
		String firstname = request.getParameter("txt_firstname");
		String lastname = request.getParameter("txt_lastname");
		String username = request.getParameter("txt_username");
		String email = request.getParameter("txt_email");
		String password = request.getParameter("txt_password");
		
		userBean.setFirstname(firstname);
		userBean.setLastname(lastname);
		userBean.setUsername(username);
		userBean.setEmail(email);
		userBean.setPassword(password);
		
		String isUserRegistered = userdao.addUser(userBean);
		
		if(isUserRegistered.equals("SUCCESS"))   //On success, you can display a message to user on Home page
        {
        	//ServletContext context= getServletContext();
        	//RequestDispatcher rd= context.getRequestDispatcher("/Home.jsp");
        	//rd.forward(request, response);
        	response.sendRedirect("Success.jsp");
 
        }
        else   //On Failure, display a meaningful message to the User.
        {
        	//ServletContext context= getServletContext();
        	//RequestDispatcher rd= context.getRequestDispatcher("/Home.jsp");
        	//rd.forward(request, response);
        	response.sendRedirect("login.jsp");
        	
        }
	}

}
