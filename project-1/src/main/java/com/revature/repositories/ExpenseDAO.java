package com.revature.repositories;

import java.util.List;

import com.revature.models.Expense;

public interface ExpenseDAO {
	
	public boolean insert(Expense ex); // returns true if successfully inserted	
	public int update(Expense ex);
	
	public List<Expense> findAll(); // this will return ALL employee objects from the DB
	// we will use this in our service layer and filter it to return JUST one employee that matches
	// username + password

	
}


