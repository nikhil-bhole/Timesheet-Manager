package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.RecordBean;
import dao.RecordDao;
import database.ConnectionFactory;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loggedinuser = (String)request.getSession(false).getAttribute("userName");
		System.out.println((String)request.getSession(false).getAttribute("userName"));
		String userID = getUserID(loggedinuser);
		System.out.println("USER ID" + userID);
		String dayStartTime=null;
	    	String lunchStartTime=null;
	    	String lunchEndTime=null;
		String breakStartTime=null;
		String breakEndTime=null;
		String dayEndTime=null;
		String casualleave=null;
		String sickleave=null;
		String holidays=null;
		String leavewithoutpay=null;
		
		RecordDao rc = new RecordDao();
		response.setContentType("text/html");  
        	PrintWriter out = response.getWriter();
        	RecordBean recordBean = new RecordBean();
        	String date = request.getParameter("selectDate");
        
       		String selectType = request.getParameter("selectType");
	   	
        if(selectType.equals("starttime")){
	   		dayStartTime = request.getParameter("txtTime");
	   	}
	    if(selectType.equals("lunchstart")){
	   		lunchStartTime = request.getParameter("txtTime");
	   	}
	   	 if(selectType.equals("lunchend")){
	   		lunchEndTime = request.getParameter("txtTime");
	   	}
	   	 if(selectType.equals("breakstart")){
	   		breakStartTime = request.getParameter("txtTime");
	   	}
	   	 if(selectType.equals("breakend")){
	   		breakEndTime = request.getParameter("txtTime");
	   	}
	   	 if(selectType.equals("dayend")){
	   		dayEndTime = request.getParameter("txtTime");
	   	}
	   	 if(selectType.equals("casualleave")){
	   		casualleave = request.getParameter("txtTime");
	   	}
	   	 if(selectType.equals("sickleave")){
	   		sickleave = request.getParameter("txtTime");
	   	}
	   	 if(selectType.equals("holidays")){
	   		holidays = request.getParameter("txtTime");
	   	}
	   	 if(selectType.equals("leavewithoutpay")){
	   		leavewithoutpay = request.getParameter("txtTime");
	   	}
       	
	   	recordBean.setID(userID);
	   	recordBean.setDate(date);
	   	recordBean.setTdayStart(dayStartTime);
        	recordBean.setTlunchStart(lunchStartTime);
        	recordBean.setTlunchend(lunchEndTime);
        	recordBean.setTbreakStart(breakStartTime);
        	recordBean.setTbreakEnd(breakEndTime);
        	recordBean.settDayEnd(dayEndTime);
        	recordBean.setCasualleave(casualleave);
        	recordBean.setSickleave(sickleave);
        	recordBean.setHolidays(holidays);
        	recordBean.setLeavewithoutpay(leavewithoutpay);
        
        String userRegistered = rc.addRecord(recordBean);
        
        if(userRegistered.equals("SUCCESS"))   //On success, you can display a message to user on Home page
        {
        	//ServletContext context= getServletContext();
        	//RequestDispatcher rd= context.getRequestDispatcher("/Home.jsp");
        	//rd.forward(request, response);
        	response.sendRedirect("Home.jsp");
 
        }
        else   //On Failure, display a meaningful message to the User.
        {
        	//ServletContext context= getServletContext();
        	//RequestDispatcher rd= context.getRequestDispatcher("/Home.jsp");
        	//rd.forward(request, response);
        	response.sendRedirect("Home.jsp");
        }
     }
	
	public static String getUserID(String username){
		 ResultSet rs = null; 
		 String a="";
		 boolean status = false;
		 Connection con = null;
		 PreparedStatement preparedStatement = null;
		 con = ConnectionFactory.createConnection();
		 String query = "select user_id from users where username = '" + username + "'";
		 try {
			preparedStatement = con.prepareStatement(query);
			rs= preparedStatement.executeQuery();
			 while(rs.next()){
			 a= rs.getString(1);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		 return a;
	}
}
