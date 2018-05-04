<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page import="java.sql.*" %>
<%@ page import="database.ConnectionFactory" %>
<%@ page import="helperClasses.*" %>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%String loggedinuser = (String)request.getSession(false).getAttribute("userName"); %>
<%ResultSet rs =null;%>
<%String fromdate =null;%>
<%String todate =null;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/stylesheet.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.min.js"></script>
<title>View previous time Sheets</title>
</head>
<body>
<%
  if (request.getSession().getAttribute("userName") == null)
  {
    String address = "login.jsp";
    response.sendRedirect(address);
  }
%>
<%
if (request.getParameter("btn_getData") != null) {
fromdate = request.getParameter("fromdate");
todate = request.getParameter("todate");
Connection con = null;
ResultSet ds=null;
PreparedStatement preparedStatement = null;
try{
con = ConnectionFactory.createConnection(); 
String query = "SELECT * FROM  view_dailyrecord WHERE  date between " + " " +"'"+fromdate +"'"+" "+ "AND" + " " +"'"+todate+"'" +" " + " AND user_id = '" + GetDetails.getUserID(loggedinuser) + "' ORDER BY date";
System.out.println(query);
preparedStatement=con.prepareStatement(query);
rs= preparedStatement.executeQuery(); 
}

catch(SQLException e){
e.printStackTrace();
}

}
%>
<%!
public static String getValueOrDefault(String value,String defaultValue) {
    return isNotNullOrEmpty(value) ? value : defaultValue;
}
%>

<%!
private static boolean isNotNullOrEmpty(String str) {
    return str != null && !str.isEmpty();
}
%>


<div id="to-from" class="container-fluid">
<form name = "test" action="previoustimesheets.jsp">
  <div class="form-group">
    <label for="exampleInputEmail1">From Date</label>
    <input type="date" class="form-control" name="fromdate">
    <small id="emailHelp" class="form-text text-muted">Select date from which you wish to see the records.</small>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">To Date</label>
    <input type="date" class="form-control" name="todate">
  </div>
  <button type="submit" name = "btn_getData" class="btn btn-primary">Submit</button>
</form>
</div>


<div id="displaytimesheet" class="container-fluid">
<h6>Time Sheet from: <%=fromdate%> to <%=todate%></h6>
<table style="width:200%" class="table table-bordered table-hover table-sm text-center">
  <thead class="thead-dark">
  <tr>
    <th>Date</th>
    <th>Day Start</th> 
    <th>Lunch Start</th>
    <th>Lunch End</th>
    <th>Break Start</th>
    <th>Break End</th>
    <th>Day End</th>
    <th>Casual Leave</th>
    <th>Sick Leave</th>
    <th>Holidays Leave</th>
    <th>Leave Without Pay</th>
    <th>Office Hours</th>
    <th>Lunch Hours</th>
    <th>Break Hours</th>
    <th>Hours worked</th>
  </tr>
  </thead>
<%if(rs!=null) {%>
<%while (rs.next()){%>
<TR>
<TD><%=getValueOrDefault(rs.getString(2),"")%></TD>
<TD><%=getValueOrDefault(rs.getString(3),"")%></TD>
<TD><%=getValueOrDefault(rs.getString(4),"")%></TD>
<TD><%=getValueOrDefault(rs.getString(5),"")%></TD>
<TD><%=getValueOrDefault(rs.getString(6),"")%></TD>
<TD><%=getValueOrDefault(rs.getString(7),"")%></TD>
<TD><%=getValueOrDefault(rs.getString(8),"")%></TD>
<TD><%=getValueOrDefault(rs.getString(9),"")%></TD>
<TD><%=getValueOrDefault(rs.getString(10),"")%></TD>
<TD><%=getValueOrDefault(rs.getString(11),"")%></TD>
<TD><%=getValueOrDefault(rs.getString(12),"")%></TD>
<TD><%=getValueOrDefault(rs.getString(13),"")%></TD>
<TD><%=getValueOrDefault(rs.getString(14),"")%></TD>
<TD><%=getValueOrDefault(rs.getString(15),"")%></TD>
<TD><%=getValueOrDefault(rs.getString(16),"")%></TD>
</TR>
<% } %>
<%} %>
</table>
</div>
<div id="summary" class="container-fluid">
<h6>Summary</h6>
<table style="width:40%" class="table table-bordered table-hover table-sm text-center">
<thead class="">
  <tr>
    <th>Total Office Hours</th>
    <th>Total Lunch Hours</th> 
    <th>Total Break Hours</th>
    <th>Total Work Hours</th>
  <TR>
  <TR>
<td></td>
<td></td>
<td></td>
<td></td>
</TR>
</table>
</div>
</body>
</html>