<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page import="java.sql.*" %>
<%@ page import="database.ConnectionFactory" %>
<%@ page import="helperClasses.*" %>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>

<%ResultSet rs =null;%> 
<%String loggedinuser = (String)request.getSession(false).getAttribute("userName"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to timesheet manager</title>
<link rel="stylesheet" href="css/stylesheet.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.min.js"></script>
<script src="js/custom.js"></script>
<script>
Date.prototype.getWeek = function(start)
{
    start = start || 0;
    var today = new Date(this.setHours(0, 0, 0, 0));
    var day = today.getDay() - start;
    var date = today.getDate() - day;
	var StartDate = new Date(today.setDate(date));
    var EndDate = new Date(today.setDate(date + 6));
    return [StartDate, EndDate];
}
var Dates = new Date().getWeek();
document.getElementById("p1").innerHTML = Dates[0].toLocaleDateString() + ' to '+ Dates[1].toLocaleDateString();
</script>
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
Connection con = null;
ResultSet ds=null;
PreparedStatement preparedStatement = null;
try{
con = ConnectionFactory.createConnection();
preparedStatement=con.prepareStatement("SELECT * FROM  view_dailyrecord WHERE  YEARWEEK(`date`, 1) = YEARWEEK(CURDATE(), 1) AND user_id = '" + GetDetails.getUserID(loggedinuser) + "' ORDER BY date" );
rs= preparedStatement.executeQuery(); 
}
catch(SQLException e){
e.printStackTrace();
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
<div id="navigation" class="container">
	<nav class="navbar navbar-expand-md navbar-dark fixed-top navbar-custom">
      	<a class="navbar-brand" href="#">Timesheet Manager(beta)</a>
      	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        	<span class="navbar-toggler-icon"></span>
      	</button>
      	<div class="collapse navbar-collapse" id="navbarCollapse">
	        <ul class="navbar-nav mr-auto">
		          <li class="nav-item active">
		            	<a class="nav-link" href="Home.jsp">Home <span class="sr-only">(current)</span></a>
		          </li>
		          <li class="nav-item active">
		            	<a class="nav-link" href="exportexcel.jsp">Export to Excel <span class="sr-only">(current)</span></a>
		          </li>
		          <li class="nav-item active">
		            	<a class="nav-link" href="userprofile.jsp">My Profile<span class="sr-only">(current)</span></a>
		          </li>
		           <li class="nav-item active">
		            	<a class="nav-link" href="previoustimesheets.jsp">Previous Timesheets<span class="sr-only">(current)</span></a>
		          </li>
		          <li class="nav-item active">
		            	<a class="nav-link" href="about.jsp">About <span class="sr-only">(current)</span></a>
		          </li>
		    </ul>
	        <form class="form-inline mt-2 mt-md-0" action="LogoutController" method="post">
	         	
	         	<h6>Welcome: <%= request.getSession().getAttribute("userName")%></h6>&nbsp &nbsp
	         	<button type="submit" class="btn btn-danger btn-md">Logout</button>
	        </form>
      	</div>
    </nav>
</div>
<div class="container-fluid" id="time" ng-app="myApp" ng-controller="Ctrl">
<h7><i>{{clock | date:'medium'}}</i></h7> 

</div>


<div class="container-fluid" id="form-log-time">
  	<div class="row">
			<div class="col-lg-6">
				<form action="TestServlet" method="post">
					<div class="form-row">
						    <div class="form-group col-md-3">
						      		<label for="inputCity">Select Date</label>
						      		<input class="form-control form-control-sm" type="date" name="selectDate" required>
						    </div>
						    
						    <div class="form-group col-md-3">
						      		<label for="inputState">Log Time For</label>
						      		<select name="selectType" class="form-control form-control-sm" required>
						        			<option selected>Choose...</option>
						        			<option value="starttime">Day Start Time</option>
		  									<option value="lunchstart">Lunch Start Time</option>
							  				<option value="lunchend">Lunch End Time</option>
							  				<option value="breakstart">Break Start Time</option>
							  				<option value="breakend">Break End Time</option>
							  				<option value="dayend">Day End Time</option>
							  				<option value="casualleave">Casual Leave</option>
							  				<option value="sickleave">Sick Leave</option>
							  				<option value="holidays">Holidays</option>
							  				<option value="leavewithoutpay">Leave Without Pay</option>
						        	</select>
						    </div>
						    
						    <div class="form-group col-md-3">
						      		<label for="inputZip">Enter Time(24 hour format)</label>
						      		<input type="text" class="form-control form-control-sm" name = "txtTime"  pattern="([0-1]{1}[0-9]{1}|20|21|22|23):[0-5]{1}[0-9]{1}" placeholder="HH:MM" required>
						    </div>
					 </div>
					 <button type="submit" class="btn btn-primary btn-sm">Log Time</button>
					 <input type="hidden" name="pID" value="${request.getSession().getAttribute("userName")}">
			  </form>
		</div>
		
		<div class="col-lg-6">
      			
    	</div>
	</div>
</div>


<div id="displaytimesheet" class="container-fluid">
<p id="p1"></p>
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
    <th>Holiday</th>
    <th>Leave Without Pay</th>
    <th>Office Hours</th>
    <th>Lunch Hours</th>
    <th>Break Hours</th>
    <th>Hours worked</th>
    <th>Edit</th>
    <th>Delete</th>
  </tr>
  </thead>

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
<TD>
<button type="button" class="btn btn-primary btn-sm">Edit</button>
</TD>
<TD>
<form action="DeleteServlet" method="post">
<input type="hidden" name="param2" value=<%=rs.getString(2)%>>
<input type="hidden" name="loggedUser" value=<%=GetDetails.getUserID(loggedinuser)%>>
<button type="submit" name="btn_delete" class="btn btn-danger btn-sm">Delete</button>
</form>
</TD>
</TR>
<% } %>
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
<td><%=GetDetails.getWeeklyData(loggedinuser, 1) %></td>
<td><%=GetDetails.getWeeklyData(loggedinuser, 2) %></td>
<td><%=GetDetails.getWeeklyData(loggedinuser, 3) %></td>
<td><%=GetDetails.getWeeklyData(loggedinuser, 4) %></td>
</TR>
</table>
</div>
</body>
</html>