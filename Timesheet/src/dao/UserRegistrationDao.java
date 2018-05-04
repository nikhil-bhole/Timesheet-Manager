package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import bean.UserBean;
import database.ConnectionFactory;

public class UserRegistrationDao {
	public String addUser(UserBean userBean){
		String firstname = userBean.getFirstname();
		String lastname= userBean.getLastname();
		String username= userBean.getUsername();
		String email=userBean.getEmail();
		String password=userBean.getPassword();
		Connection con = null;
		try{
			 CallableStatement cstmt = null;
			 con = ConnectionFactory.createConnection();
			 String SQL = "{call sp_registeruser (?,?,?,?,?)}";
			 cstmt = con.prepareCall (SQL);
			 cstmt.setString(1, firstname);
			 cstmt.setString(2, lastname);
			 cstmt.setString(3, username);
			 cstmt.setString(4, email);
			 cstmt.setString(5, password);
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
				    
				    
				    if (con != null) {
				        try {
				            con.close();
				        } catch (SQLException e) { /* ignored */}
				    }
				}
			 
			 
			 return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
			 }
	}

