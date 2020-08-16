<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login</h1>
	
	<form method="post" action="LoginInt.jsp">
		Enter email:<input type="text" name="email"/><Br/>
		Enter pass:<input type="password" name="pass"/><Br/>
		<br/>
		<input type="submit"/>
	</form>
	
	${errorMsg}
</body>
</html>






