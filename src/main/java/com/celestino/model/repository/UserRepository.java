package com.celestino.model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.celestino.model.User;
import com.celestino.model.util.ConnectionClinic;

public class UserRepository {
	public static List<User> findAll() throws SQLException {
		String sql = "Select * from CLINIC_DB.user";

		List<User> users = new ArrayList<User>();

		Statement stmt = ConnectionClinic.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			User u = new User();
			u.setId(rs.getInt(1));
			u.setName(rs.getString(2));
			u.setLogin(rs.getString(3));
			u.setPassword(rs.getString(4));

			users.add(u);
		}
		ConnectionClinic.getConnection().close();

		return users;
	}

}
