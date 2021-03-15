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
	
	public static Employee findByEmail(String email) {
		List<Employee> all = eDao.findAll();
		
		for (Employee e : all) { 
			if (e.getEmail().equals(email)) {
				return e;
			}
		}
		
		return null;
	}
	
	public static Employee confirmLogin(String email, String password) {
		
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
