package com.revature.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(LoginServlet.class);
    public LoginServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		log.info("User attempted to login with the username " + username);
		Employee e = EmployeeService.confirmLogin(username, password);
		
		
		if (e != null) {
			HttpSession session = request.getSession();
			
			session.setAttribute("username", username);
			
			RequestDispatcher rd = request.getRequestDispatcher("home.html"); 
			
			rd.forward(request, response);
			log.info(username + " has successfully logged in");
			
		} else {
			log.info(username + "has failed to log in");
		}
		
		
	
		
		
		
		
		
		
	}

}
