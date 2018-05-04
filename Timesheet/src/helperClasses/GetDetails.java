package helperClasses;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.ConnectionFactory;

public class GetDetails {
	public static String getUserID(String username){
		 ResultSet ls = null; 
		 String a="";
		 Connection con = null;
		 PreparedStatement preparedStatement = null;
		 con = ConnectionFactory.createConnection();
		 String query = "select user_id from users where username = '" + username + "'";
		 try {
			preparedStatement = con.prepareStatement(query);
			ls= preparedStatement.executeQuery();
			 while(ls.next()){
			 a= ls.getString(1);
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
	
	public static String getWeeklyData(String loggedinuser,int columnname){
		 ResultSet ls = null; 
		 String res=null;
		 Connection con = null;
		 String userid = getUserID(loggedinuser);
		 PreparedStatement preparedStatement = null;
		 con = ConnectionFactory.createConnection();
		 try {
			 CallableStatement cstmt = null;
			 con = ConnectionFactory.createConnection();
			 String SQL = "{call sp_getSumHours(?)}";
			 cstmt = con.prepareCall (SQL);
			 cstmt.setString(1,userid);
			 cstmt.execute();
			 ls= cstmt.getResultSet();
			 while (ls.next()) {
	               res = ls.getString(columnname);
	          }
	          ls.close();
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
		return res;
	}
	
}
