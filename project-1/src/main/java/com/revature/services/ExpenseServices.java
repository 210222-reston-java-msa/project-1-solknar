package com.revature.services;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Expense;
import com.revature.repositories.ExpenseDAO;
import com.revature.repositories.ExpenseDAOImpl;

public class ExpenseServices {
	
	public static ExpenseDAO newExpDAO = new ExpenseDAOImpl();
	
	
	public static List<Expense> findAll() {
		return newExpDAO.findAll();
	}
	
	
	public static void main(String[] args) {
		Expense newExp = new Expense(1, 20, "carro", "pending", "");
		System.out.println(newExp);
		newExpDAO.insert(newExp);
		
		
		

	}
}
