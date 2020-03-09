package com.celestino.model.repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.celestino.model.User;
import com.celestino.model.util.ClinicException;
import com.celestino.model.util.ConnectionClinic;

public class UserRepository implements Serializable{

	private static final long serialVersionUID = 1L;

	public List<User> findAll() {

		List<User> users = new ArrayList<User>();

		Statement stmt;
		try {
			String sql = "SELECT * FROM clinic_db.user ORDER BY id DESC";
			stmt = ConnectionClinic.getConnection().createStatement();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public void save(User user) throws ClinicException {
		Connection conn = null;
		PreparedStatement ps = null;

		if (user == null)
			throw new ClinicException("Value cannot be null!!");

		try {
			String sql = "INSERT INTO user(name,login,password) VALUES (?,?,?)";
			conn = ConnectionClinic.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getLogin());
			ps.setString(3, user.getPassword());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM clinic_db.user";
			conn = ConnectionClinic.getConnection();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteById(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM clinic_db.user WHERE id=?";
			conn = ConnectionClinic.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE USER SET name=?, login=?, password=? WHERE id=?";
			conn = ConnectionClinic.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getLogin());
			ps.setString(3, user.getPassword());
			ps.setLong(4, user.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public User findByName(String name) {
		Connection conn = null;
		PreparedStatement ps = null;
		User user = null;

		String sql = "SELECT * FROM clinic_db.user WHERE clinic_db.user.name = ?";
		try {
			conn = ConnectionClinic.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setLogin(rs.getString(3));
				user.setPassword(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
