package com.revature;

import com.revature.models.Employee;
import com.revature.models.Expense;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.EmployeeDAOImpl;
import com.revature.repositories.ExpenseDAO;
import com.revature.repositories.ExpenseDAOImpl;

public class Driver {
	public static EmployeeDAO eDao = new EmployeeDAOImpl();
	public static ExpenseDAO exDao = new ExpenseDAOImpl();

	public static void main(String[] args) {

		Employee emp = new Employee(15, "aa", "bb", "ab@mail.com", "1234", "employee");

		Expense exp = new Expense(1, 1, 20, "carro", "approved", "Rodrigo");
		
		Expense exp2 = new Expense( 1, 20, "trip", "pending", "");
		//eDao.update(emp);
		//System.out.println(eDao.findAll());
		exDao.insert(exp2);
		System.out.println(exDao.findAll());
		
	}
}
