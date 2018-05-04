package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import database.ConnectionFactory;
import helperClasses.GetDetails;

/**
 * Servlet implementation class ExportServlet
 */
@WebServlet("/ExportServlet")
public class ExportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportServlet() {
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
		   
		if (request.getParameter("export") != null) {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String loggedinuser = (String)request.getSession(false).getAttribute("userName");
			String userID = GetDetails.getUserID(loggedinuser);
			
			String lunchhours = "";
			String breakhours = "";
			String officehours = "";
			String workedhours = "";
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition", "attachment; filename=sampleName.xlsx");
			try{
				
				String excelFileName = "/home/nikhilbhole/Documents/Testsheet.xlsx";//name of excel file
				
				List<String> dates  = getValues("date",fromDate,toDate,userID);
				List<String> dayStart  = getValues("daystart",fromDate,toDate,userID);
				List<String> lunchStart  = getValues("lunchstart",fromDate,toDate,userID);
				List<String> lunchEnd  = getValues("lunchend",fromDate,toDate,userID);
				List<String> breakStart  = getValues("breakstart",fromDate,toDate,userID);
				List<String> breakEnd  = getValues("breakend",fromDate,toDate,userID);
				List<String> dayend  = getValues("dayend",fromDate,toDate,userID);
				List<String> officeHours  = getValues("officehours",fromDate,toDate,userID);
				List<String> lunchHours  = getValues("lunchhours",fromDate,toDate,userID);
				List<String> breakHours  = getValues("breakhours",fromDate,toDate,userID);
				List<String> hoursWorked  = getValues("workhours",fromDate,toDate,userID);
				List<String> casualLeave  = getValues("casualleave",fromDate,toDate,userID);
				List<String> sickLeave  = getValues("sickleave",fromDate,toDate,userID);
				List<String> holidays  = getValues("holidays",fromDate,toDate,userID);
				List<String> lwp  = getValues("leavewithoutpay",fromDate,toDate,userID);
				
				FileInputStream file = new FileInputStream(new File(excelFileName));
				XSSFWorkbook wb = new XSSFWorkbook(file);
				XSSFSheet sheet = wb.getSheetAt(0);
				
				Cell cell = null;
				cell = sheet.getRow(0).getCell(1);
				cell.setCellValue(getFullName(userID));
				
				cell = sheet.getRow(1).getCell(1);
				cell.setCellValue(fromDate);
				
				cell = sheet.getRow(2).getCell(1);
				cell.setCellValue(toDate);
				
				for(int i=0;i<dates.size();i++){
					cell = sheet.getRow(5).getCell(i+1);
					cell.setCellValue(dates.get(i));
				}
				for(int i=0;i<dayStart.size();i++){
					cell = sheet.getRow(6).getCell(i+1);
					cell.setCellValue(dayStart.get(i));
				}
				for(int i=0;i<lunchStart.size();i++){
					cell = sheet.getRow(7).getCell(i+1);
					cell.setCellValue(lunchStart.get(i));
				}
				for(int i=0;i<lunchEnd.size();i++){
					cell = sheet.getRow(8).getCell(i+1);
					cell.setCellValue(lunchEnd.get(i));
				}
				for(int i=0;i<breakStart.size();i++){
					cell = sheet.getRow(9).getCell(i+1);
					cell.setCellValue(breakStart.get(i));
				}
				for(int i=0;i<breakEnd.size();i++){
					cell = sheet.getRow(10).getCell(i+1);
					cell.setCellValue(breakEnd.get(i));
				}
				for(int i=0;i<dayend.size();i++){
					cell = sheet.getRow(11).getCell(i+1);
					cell.setCellValue(dayend.get(i));
				}
				for(int i=0;i<officeHours.size();i++){
					cell = sheet.getRow(12).getCell(i+1);
					cell.setCellValue(officeHours.get(i));
				}
				for(int i=0;i<lunchHours.size();i++){
					cell = sheet.getRow(13).getCell(i+1);
					cell.setCellValue(lunchHours.get(i));
				}
				for(int i=0;i<breakHours.size();i++){
					cell = sheet.getRow(14).getCell(i+1);
					cell.setCellValue(breakHours.get(i));
				}
				for(int i=0;i<hoursWorked.size();i++){
					cell = sheet.getRow(15).getCell(i+1);
					cell.setCellValue(hoursWorked.get(i));
				}
				for(int i=0;i<casualLeave.size();i++){
					cell = sheet.getRow(16).getCell(i+1);
					cell.setCellValue(casualLeave.get(i));
				}
				for(int i=0;i<sickLeave.size();i++){
					cell = sheet.getRow(17).getCell(i+1);
					cell.setCellValue(sickLeave.get(i));
				}
				for(int i=0;i<holidays.size();i++){
					cell = sheet.getRow(18).getCell(i+1);
					cell.setCellValue(holidays.get(i));
				}
				for(int i=0;i<lwp.size();i++){
					cell = sheet.getRow(19).getCell(i+1);
					cell.setCellValue(lwp.get(i));
				}
				for(int i=0;i<hoursWorked.size();i++){
					cell = sheet.getRow(22).getCell(i+1);
					cell.setCellValue(hoursWorked.get(i));
				}
				wb.write(response.getOutputStream());
				wb.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	     }
		
	}
	
	public static List<String> getValues(String columnname,String fromDate,String toDate,String userid){
		String columnName = columnname;
		List<String> dates  = new ArrayList<>();
		Connection con = null;
		ResultSet ds=null;
		PreparedStatement preparedStatement = null;
		try{
			con = ConnectionFactory.createConnection();
			preparedStatement=con.prepareStatement("SELECT" + " " + columnName + " " + " from view_dailyrecord WHERE  date between" + " " +"'"+fromDate +"'"+" "+ "AND" + " " +"'"+toDate+"'" +" " + "AND user_id = '" + userid  + "' ORDER BY date" );
			ds= preparedStatement.executeQuery(); 
			ResultSetMetaData rsmd=ds.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
				while(ds.next()){
					for (int i = 1; i <= columnsNumber; i++) {
				      dates.add(ds.getString(i));
				    }
				}
		}
		catch(SQLException e){
		e.printStackTrace();
		}
		finally {
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
		
		return dates;
	}
	
	public String getFullName(String userID){
		
		ResultSet ls = null; 
		 String a="";
		 Connection con = null;
		 PreparedStatement preparedStatement = null;
		 con = ConnectionFactory.createConnection();
		 String query = "select firstname,lastname from users where user_id = '" + userID  + "'";
		 try {
			preparedStatement = con.prepareStatement(query);
			ls= preparedStatement.executeQuery();
			 while(ls.next()){
			 a= ls.getString(1);
			 a= a + " " + ls.getString(2);
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
		return a;
	}
}
