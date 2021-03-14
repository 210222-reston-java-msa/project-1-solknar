package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.models.Expense;
import com.revature.util.ConnectionUtil;

public class ExpenseDAOImpl implements ExpenseDAO{

	private static Logger log = Logger.getLogger(EmployeeDAOImpl.class);
	
	@Override
	public boolean insert(Expense ex) {
		
		PreparedStatement stmt = null;

		try {

			Connection conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO expenses (user_id, expense_ammount, expense_description, expense_status, reviewed_by) VALUES (?, ?, ?, ?, ?)";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ex.getUserId());
			stmt.setDouble(2, ex.getExpenseAmmount());
			stmt.setString(3, ex.getExpenseDescription());
			stmt.setString(4, ex.getExpenseStatus());
			stmt.setString(5, ex.getReviewedBy());

			if (!stmt.execute()) {
				return false;
			}

		} catch (SQLException exc) {
			log.warn("Unable to insert expense", exc);
			return false;
		}
		// If everything is successful, we return true
		return true;
	
	}

	@Override
	public int update(Expense ex) {
		System.out.println(ex);
		try {
			Connection conn = ConnectionUtil.getConnection();

			String sql = "UPDATE expenses set user_id = ?,expense_ammount = ?,expense_description = ?,expense_status = ?,reviewed_by = ? WHERE expense_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, ex.getUserId());
			stmt.setDouble(2, ex.getExpenseAmmount());
			stmt.setString(3, ex.getExpenseDescription());
			stmt.setString(4, ex.getExpenseStatus());
			stmt.setString(5, ex.getReviewedBy());
			stmt.setInt(6, ex.getId());
			
			log.info("Expense updated: " + ex);
			return stmt.executeUpdate();

		} catch (SQLException exc) {
			exc.printStackTrace();
			log.warn(exc);
		}

		return 0;
	}

	@Override
	public List<Expense> findAll() {

		List<Expense> list = new ArrayList<Expense>();

		try {
			
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "SELECT * FROM expenses";
			
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("expense_id");
				int userId = rs.getInt("user_id");
				Double expenseAmmount = rs.getDouble("expense_ammount");
				String expenseDescription = rs.getString("expense_description");
				String expenseStatus = rs.getString("expense_status");
				String reviewedBy = rs.getString("reviewed_by");
				
				Expense e = new Expense(id, userId, expenseAmmount, expenseDescription, expenseStatus, reviewedBy);
				list.add(e);
			}

		} catch (SQLException ex) {
			log.warn("Unable to retrieve all users", ex);

		}

		return list;
	}

	
}
