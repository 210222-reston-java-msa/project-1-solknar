package com.revature.repositories;

import java.util.List;

import com.revature.models.Expense;

public interface ExpenseDAO {
	
	public boolean insert(Expense ex); 
	public int update(Expense ex);
	
	public List<Expense> findAll(); 

	
}


