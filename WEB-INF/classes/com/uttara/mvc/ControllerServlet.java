package com.uttara.mvc;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        System.out.println("in no-arg constr of CS");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("in doGet() of CS");
		process(request,response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("in process() of CS");
		//apply control logic
		try
		{
			String uri = request.getRequestURI();
			System.out.println("CS->uri = "+uri);
			RequestDispatcher rd = null;
			
			Model model = new Model();
			
			if(uri.contains("openDisplayUsers.do"))
			{
				List<RegBean> users = model.getUsersInfo();
				
				request.setAttribute("userDetails", users);
				rd = request.getRequestDispatcher("DisplayUsers.jsp");
				rd.forward(request, response);
			}
			
			if(uri.contains("/logout.do"))
			{
				HttpSession session  = request.getSession(false);
				if(session!=null)
				{
					session.removeAttribute("user");
					session.invalidate();
				}
				request.setAttribute("msg", "You have successfully logged out.<a href='HomePage.html'>Click to go back to HomePage</a>");
				rd =request.getRequestDispatcher("Success.jsp");
				rd.forward(request, response);
					
			}
			
			if(uri.contains("/login.do"))
			{
				LoginBean bean = (LoginBean) request.getAttribute("log");
				String msg = model.authenticate(bean);
				if(msg.equals(Constants.SUCCESS))
				{
					//create a session!
					HttpSession session = request.getSession(true);
					session.setAttribute("user", bean.getEmail());
					rd = request.getRequestDispatcher("Menu.jsp");
					rd.forward(request, response);
				}
				else
				{
					request.setAttribute("errorMsg", msg);
					rd = request.getRequestDispatcher("Login.jsp");
					rd.forward(request, response);
				
				}
				
			}
			
			if(uri.contains("/register.do"))
			{
				/*
				 * 1.pull bean obj from request scope
				 * 2.pass bean obj to Model->register()
				 * 3.if reg succeeds, forward to Success.jsp
				 * 4.if reg fails, forward to Register.jsp
				 * 5.Any data to be shared with JSP should be saved in scope 
				 * before forwarding
				 */
				RegBean bean = (RegBean) request.getAttribute("reg");
				System.out.println("CS->process()->bean = "+bean);
				
				String msg = model.register(bean);
				System.out.println("CS->process()->after returning from model.register() msg = "+msg);
				if(msg.equals(Constants.SUCCESS))
				{
					//registration has succeeded!
					//before forwarding store message to be displayed in scope
					request.setAttribute("msg", "Your registration has succeeded!!");
					rd = request.getRequestDispatcher("Success.jsp");
					rd.forward(request, response);
				}
				else
				{
					//registration has failed
					request.setAttribute("errorMsg", msg);
					rd = request.getRequestDispatcher("Register.jsp");
					rd.forward(request, response);
				}
				
			}
			
			if(uri.contains("/openRegisterView"))
			{
				//pre-processing
				rd = request.getRequestDispatcher("Register.jsp");
				rd.forward(request, response);
				
			}
			if(uri.contains("/openLoginView"))
			{
				rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
	
			}
		}
		catch(Throwable t)
		{
			t.printStackTrace();
			request.setAttribute("errorMsg", "Something bad has happened msg = "+t.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
			rd.forward(request, response);
		}
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("in doGet() of CS");
		process(request,response);
	}

}
