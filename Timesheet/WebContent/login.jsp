<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign in to portal.</title>
<link rel="stylesheet" href="css/stylesheet.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
body {margin: 0px;padding:0px;
background-color: #f9f9f9;}
</style>
</head>
<body>
<h3 id="header" class="text-center">Please sign in to access the portal.</h3>
	<div class="auth-form-body">
			<form name="authuser" method="post" action="Login">
			  <div class="form-group center-block">
			    <label for="exampleInputEmail1">Username or email address</label>
			    <input type="text" class="form-control" name="txt_username" aria-describedby="emailHelp">
			  </div>
			  
			  <div class="form-group">
			    <label for="exampleInputPassword1">Password</label>
			    <input type="password" class="form-control" name="txt_password">
			  </div>
			  
			  <button type="submit" class="btn btn-success btn-md btn-block"><b>Sign In</b></button>
			
			</form>
	</div>
	<p class="create-account-callout mt-3">
	        New to Portal?
	        <a href="Registration.jsp">Create an account</a>.
	</p>
</body>
</html>