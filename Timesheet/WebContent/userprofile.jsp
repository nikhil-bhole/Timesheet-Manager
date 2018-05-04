<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String loggedinuser = (String)request.getSession(false).getAttribute("userName"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Proflie</title>
</head>
<body>
<%
  if (request.getSession().getAttribute("userName") == null)
  {
    String address = "login.jsp";
    response.sendRedirect(address);
  }
%>

<h1>Hi</hi> 
</h1><h1><%= loggedinuser%></h1>
<h1>Page Under construction. Thank you.</h1>
</body>
</html>