<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New User Registration</title>
<link rel="stylesheet" href="css/stylesheet.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="reg-form-body" id="reg-new-useru">
<form class="form-horizontal" action='RegisterUserServlet' method="POST">
  <fieldset>
    <div id="legend">
      <legend >Register New User</legend>
    </div>
     <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="username">First Name</label>
      <div class="controls">
        <input type="text" id="username" name="txt_firstname" placeholder="" required class="form-control">
        <p class="help-block">Username can contain any letters or numbers, without spaces</p>
      </div>
    </div>
    
     <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="">Last Name</label>
      <div class="controls">
        <input type="text" id="username" name="txt_lastname" placeholder="" required class="form-control">
        <p class="help-block">Username can contain any letters or numbers, without spaces</p>
      </div>
    </div>
    
    
    <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="t">Username</label>
      <div class="controls">
        <input type="text" id="username" name="txt_username" placeholder="" required class="form-control">
        <p class="help-block">Username can contain any letters or numbers, without spaces</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- E-mail -->
      <label class="control-label" for="email">E-mail</label>
      <div class="controls">
        <input type="text" id="email" name="txt_email" placeholder="" required class="form-control">
        <p class="help-block">Please provide your E-mail</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- Password-->
      <label class="control-label" for="password">Password</label>
      <div class="controls">
        <input type="password" id="password" name="txt_password" placeholder="" required class="form-control">
        <p class="help-block">Password should be at least 4 characters</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- Password -->
      <label class="control-label"  for="password_confirm">Password (Confirm)</label>
      <div class="controls">
        <input type="password" id="password_confirm" name="password_confirm" placeholder="" required class="form-control">
        <p class="help-block">Please confirm password</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- Button -->
      <div class="controls">
       <button type="submit" class="btn btn-success btn-md " align="center"><b>Register User</b></button>
      </div>
    </div>
  </fieldset>
</form>
</div>
</body>
</html>