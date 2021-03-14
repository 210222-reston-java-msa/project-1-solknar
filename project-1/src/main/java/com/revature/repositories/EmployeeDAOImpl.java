package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static Logger log = Logger.getLogger(EmployeeDAOImpl.class);

	public boolean insert(Employee e) {

		PreparedStatement stmt = null;

		try {

			Connection conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO employee (first_name, last_name, e_mail, pass_word, ro_le) VALUES (?, ?, ?, ?, ?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, e.getFirstName());
			stmt.setString(2, e.getLastName());
			stmt.setString(3, e.getPassword());
			stmt.setString(4, e.getEmail());
			stmt.setString(5, e.getRole());

			if (!stmt.execute()) {
				return false;
			}

		} catch (SQLException ex) {
			log.warn("Unable to insert user", ex);
			return false;
		}
		// If everything is successful, we return true
		return true;
	}

	public int update(Employee e) {
		System.out.println(e);
		try {
			Connection conn = ConnectionUtil.getConnection();

			String sql = "UPDATE employee set first_name = ?,last_name = ?,e_mail = ?,pass_word = ?,ro_le = ? WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, e.getFirstName());
			stmt.setString(2, e.getLastName());
			stmt.setString(3, e.getEmail());
			stmt.setString(4, e.getPassword());
			stmt.setString(5, e.getRole());
			stmt.setInt(6, e.getId());
			
			log.info("Employee updated: " + e);
			return stmt.executeUpdate();

		} catch (SQLException exc) {
			exc.printStackTrace();
			log.warn(exc);
		}

		return 0;
	}

	public List<Employee> findAll() {

		List<Employee> list = new ArrayList<Employee>();

		try {
			
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "SELECT * FROM employee";
			
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("e_mail");
				String password = rs.getString("pass_word");
				String role = rs.getString("ro_le");
				
				Employee e = new Employee(id, first_name, last_name, email, password, role);
				list.add(e);
			}

		} catch (SQLException ex) {
			log.warn("Unable to retrieve all users", ex);

		}

		return list;
	}

}
