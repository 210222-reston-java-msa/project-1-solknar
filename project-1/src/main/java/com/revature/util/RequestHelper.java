package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.Expense;
import com.revature.models.LoginTemplate;
import com.revature.repositories.ExpenseDAO;
import com.revature.repositories.ExpenseDAOImpl;
import com.revature.services.EmployeeService;
import com.revature.services.ExpenseServices;

public class RequestHelper {
	
	private static Logger log = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();
	
	public static void processLogin(HttpServletRequest req, HttpServletResponse res) throws IOException { 
		
		// We want to turn whatever we receive as the request into a string to process
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		// logic to transfer everything from our reader to our string builder
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);
		
		
		// I'm going to build a model called LoginTemplate which holds a username and password
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class); // from JSON --> Java Object

		
		String username = loginAttempt.getUsername();
		String password = loginAttempt.getPassword();	
		
		log.info("User attempted to login with username: " + username);
		Employee e = EmployeeService.confirmLogin(username, password);
			
		if (e != null) {
			// get the current session OR create one if it doesn't exist
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			
			// Attaching the print writer to our response
			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			
			// this is converting our Java Object (with property firstName!) 
			// to JSON format....that means we can grab the firstName property
			// after we parse it. (We parse it in JavaScript)
			pw.println(om.writeValueAsString(e));

			
			log.info(username + " has successfully logged in");	
		} else {
			res.setStatus(204); // this means that we still have a connection, but no user is found
		}
		
		
	}
	
	
	public static void processLogout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession(false); // I'm capturing the session, but if there ISN'T one, I don't create a new one.
		
		
		log.info("Processing logout");
		
		
		if (session != null) {
			
			String username = (String) session.getAttribute("username");
			log.info( username + " has logged out");
							
			session.invalidate();
		}
		
		res.setStatus(200);
	
	}
	
	public static void processEmployees(HttpServletRequest req, HttpServletResponse res) throws IOException {
	
		
		log.info(EmployeeService.findAll());
		res.setContentType("application/json");
		
		List<Employee> allEmps = EmployeeService.findAll();
		
		String json = om.writeValueAsString(allEmps);
		
		PrintWriter pw = res.getWriter();
		pw.println(json);
		
		
	}	
	
public static void processTickets(HttpServletRequest req, HttpServletResponse res) throws IOException {
	
		
		log.info(ExpenseServices.findAll());
		res.setContentType("application/json");
		
		List<Expense> allExps = ExpenseServices.findAll();
		
		String json = om.writeValueAsString(allExps);
		
		PrintWriter pw = res.getWriter();
		pw.println(json);
		
		
	}	
	
	
	public static void processExpense(HttpServletRequest req, HttpServletResponse res) throws IOException {
				BufferedReader reader = req.getReader();
				StringBuilder s = new StringBuilder();
				
				String line = reader.readLine();
				while (line != null) {
					s.append(line);
					line = reader.readLine();
				}
				
				String body = s.toString();
				log.info(body);
				
				
				Expense expenseToAdd = om.readValue(body, Expense.class); 
				expenseToAdd.setExpenseStatus("pending");
				expenseToAdd.setReviewedBy("tba");
								
				log.info("User added expense " + expenseToAdd);
				
				if(expenseToAdd != null) {
					ExpenseDAO exDao = new ExpenseDAOImpl();
					
					if (exDao.insert(expenseToAdd)) {
						res.setStatus(200);
					}else {
						res.setStatus(204);
					}
				}
				
				
				;
	}
	
	public static void main(String[] args) {
		log.info(EmployeeService.findAll());
	}
	

}
