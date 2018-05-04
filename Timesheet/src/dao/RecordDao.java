package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;



import bean.RecordBean;
import database.ConnectionFactory;



public class RecordDao {
	
	public String addRecord(RecordBean recordBean)
	 {
	 
	 String date = recordBean.getdate();
	 String dayStartTime = recordBean.getTdayStart();
	 String userid = recordBean.getID();
	 String lunchStartTime = recordBean.getTlunchStart();
	 String lunchEndTime = recordBean.getTlunchend();
	 String breakStartTime = recordBean.getTbreakStart();
	 String breakEndTime = recordBean.getTbreakEnd();
	 String dayEndTime = recordBean.gettDayEnd();
	 String casualleave = recordBean.getCasualleave();
	 String sickleave = recordBean.getSickleave();
	 String holidays = recordBean.getHolidays();
	 String leavewithoutpay = recordBean.getLeavewithoutpay();
	 Connection con = null;
	 PreparedStatement preparedStatement = null;
	 
	 try{
	 CallableStatement cstmt = null;
	 con = ConnectionFactory.createConnection();
	 String SQL = "{call sp_setrecords (?,?,?,?,?,?,?,?,?,?,?,?)}";
	 cstmt = con.prepareCall (SQL);
	 cstmt.setString(1, userid);
	 cstmt.setString(2, date);
	 cstmt.setString(3, dayStartTime);
	 cstmt.setString(4, lunchStartTime);
	 cstmt.setString(5, lunchEndTime);
	 cstmt.setString(6, breakStartTime);
	 cstmt.setString(7, breakEndTime);
	 cstmt.setString(8, dayEndTime);
	 cstmt.setString(9, casualleave);
	 cstmt.setString(10, sickleave);
	 cstmt.setString(11, holidays);
	 cstmt.setString(12, leavewithoutpay);
	 int i= cstmt.executeUpdate();
	 cstmt.close();
	 if (i!=0){ 
	 return "SUCCESS";
	 }
	 else{
		 
	 }
	 }catch(SQLException e){
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
	 
	 
	 return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
	 }
}
