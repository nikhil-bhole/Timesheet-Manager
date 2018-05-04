package helperClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;

public class SumHours {
	static String addTime(String[] workingTimes){
		int sum = 0;
		for( String hhmm : workingTimes ){
		  String[] split = hhmm.split( ":", 2 );
		  
		  int mins = Integer.valueOf(split[ 0 ]) * 60 + Integer.valueOf( split[ 1 ] );
		  sum += mins;
		}
		String formattedWorkingTime = (int)Math.floor(sum/60) + ":" + ( sum % 60 );
		return formattedWorkingTime;
	}
	
	public static String getTotalTime(String column,String fromdate,String todate,String userid){
		 ResultSet ls = null; 
		 String test = GetDetails.getUserID(userid);
		 String[] arr=null;
		 Connection con = null;
		 String formattedWorkingTime=null;
		 PreparedStatement preparedStatement = null;
		 con = ConnectionFactory.createConnection();
		 String query = "SELECT"+ " "+ column +" "+ "from view_dailyrecord where date between " + " " +"'"+fromdate +"'"+" "+ "AND" + " " +"'"+todate+"'" +" " + " AND user_id = '" + test + "'";
		 System.out.println(query);
		 try {
			preparedStatement = con.prepareStatement(query);
			ls= preparedStatement.executeQuery();
			List<String> rowValues = new ArrayList();
			while(ls.next()){
				String s = ls.getString(1);
				int k = s.indexOf(":", s.indexOf(":") + 1);
				String res = s.substring(0,k);
				rowValues.add(res);
			}
			arr = (String[]) rowValues.toArray(new String[rowValues.size()]);
			int sum = 0;
			for( String hhmm : arr ){
			  String[] split = hhmm.split( ":", 2 );
			  
			  int mins = Integer.valueOf(split[ 0 ]) * 60 + Integer.valueOf( split[ 1 ] );
			  sum += mins;
			}
			formattedWorkingTime = (int)Math.floor(sum/60) + ":" + ( sum % 60 );
			System.out.println("test" + formattedWorkingTime);
			
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
		 return formattedWorkingTime;	
	}
	
}
