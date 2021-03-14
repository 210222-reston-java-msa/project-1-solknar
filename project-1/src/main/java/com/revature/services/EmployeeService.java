package com.revature.services;

import java.util.List;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.EmployeeDAOImpl;

public class EmployeeService {
	
	public static EmployeeDAO eDao = new EmployeeDAOImpl();
	
	
	public static boolean insert(Employee e) {
		
		return eDao.insert(e);
	}
	
	public static int update(Employee e) {
		
		return eDao.update(e);
	}
	
	public static List<Employee> findAll() {
		return eDao.findAll();
	}
	
	// find by Email...
	public static Employee findByEmail(String email) {
		List<Employee> all = eDao.findAll();
		//List<Employee> all = findAll(); // another way to do it
		
		for (Employee e : all) { // filtering with an enhanced for-loop!
			if (e.getEmail().equals(email)) {
				return e; // we return the Employee object with a matching email
			}
		}
		
		return null;
	}
	
	// confirm login method
	public static Employee confirmLogin(String email, String password) {
		
		// we use the above method
		Employee e = findByEmail(email);
		
		if (e == null) {
			return null;
		}
		
		if (e.getPassword().equals(password)) {
			return e;
		} else {
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	

}
