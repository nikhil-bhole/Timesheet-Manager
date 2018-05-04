<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>About the project</title>
<link rel="stylesheet" href="css/stylesheet.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.min.js"></script>
<script src="js/custom.js"></script>
</head>
<body>
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
<h1>Timesheet Manager-Beta</h1>
<p>
This web application is preliminary developed to automate the manual process of maintaning 
Timesheet which takes a significant amount of time in re-writing, calculations and verification</p>
<p>
As of this web-app contains features like:
1. Adding daily time records.
2. automatic calculations.
3. Export to excel facility.
4. browse prevoius time-sheets
5. User Profile.
</p>
<p>
Remanining features:
1. As of now there are no hard-core validations implemented to validate the data on enter. But basics are covered.
2. Monthly calculations.
3. Holidays, leave analysis and specifications.
4. Deleting/editing a row in current record table.
</p>
<p>
<b>What you can do:</b>>
You can find the repository of this project on Github <a href="www.github.com">here</a> and can help the further development of this project.
Any suggestions are most welcomed!

</p>
<p>
<b>Technologies used:</b> <i>JAVA: JSP/SERVLETS, MY-SQL, HTML5,BOOTSTRAP,ANGULAR JS</i>
</p>
<p>
<b>Designed and developed by</b> <i>Nikhil Bhole(c)18-19</i>
</p>
<p>
<b>Dveloped for</b> <i>Doms IT Solutions Pvt. Ltd.</i>
</p>
</body>
</html>