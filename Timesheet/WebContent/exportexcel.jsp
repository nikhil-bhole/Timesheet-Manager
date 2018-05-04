<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Timesheet | Export Utility</title>
<link rel="stylesheet" href="css/stylesheet.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.min.js"></script>
<script src="js/custom.js"></script>
</head>
<body>
<h5>It is recomended (beta functionality) that you should import the file only after completeing a week.</h5>
<form name="exporttoexcel" action="ExportServlet" method="post">
<h5>From date</h5>
<input type="date" name="fromDate">
<h5>To Date</h5>
<input type="date" name="toDate">
<input type="submit" name="export" value="Export to excel">
</form>
<a href="Home.jsp">go to home</a>.
</body>
</html>