<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Register</h1>
	
	<form method="post" action="RegInt.jsp">
		Enter name:<input type="text" name="uname"/><Br/>
		Enter email:<input type="text" name="email"/><Br/>
		Enter pass:<input type="password" name="pass"/><Br/>
		Repeat pass:<input type="password" name="rpass"/><Br/>
		<br/>
		<input type="submit"/>
	</form>
	
	${errorMsg}
</body>
</html>






