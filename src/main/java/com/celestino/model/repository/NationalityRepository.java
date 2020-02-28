package com.celestino.model.repository;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.celestino.model.Nationality;
import com.celestino.model.util.ConnectionClinic;

public class NationalityRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Nationality> findAll() {

		List<Nationality> nationalities = new ArrayList<Nationality>();

		Statement stmt;

		try {
			String sql = "Select * from CLINIC_DB.nationality";
			stmt = ConnectionClinic.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Nationality n = new Nationality();
				n.setId(rs.getInt(1));
				n.setDescription(rs.getString(2));
				n.setCountry(rs.getString(3));
				
				nationalities.add(n);
			}
			ConnectionClinic.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nationalities;
	}
}
