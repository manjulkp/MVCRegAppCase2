<!-- 
	instantiate bean obj
	populate bean obj with user inputs from q.s in req
	save bean in scope
	forward to controller
	
	Q.S: uname=Ramu&email=Ramu@gmail.com&pass=123&rpass=123
	
	
 -->

<jsp:useBean id="reg" class="com.uttara.mvc.RegBean" scope="request">
	<jsp:setProperty name="reg" property="*"/>
</jsp:useBean>
<jsp:forward page="register.do"/>

<!-- 
	RegBean reg = new RegBean();
	reg.setUname(request.getParameter("uname"))
	reg.setEmail(request.getParameter("email"))
	reg.setPass(request.getParameter("pass"))
	reg.setRpass(request.getParameter("rpass"))
	request.setAttribute("reg",reg);
	RequestDispatcher rd = request.getRequestDispatcher("/register.do");
	rd.forward(request,response);
 -->



