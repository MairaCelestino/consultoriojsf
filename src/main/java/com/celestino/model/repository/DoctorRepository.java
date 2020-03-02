package com.celestino.model.repository;

import com.celestino.model.Doctor;
import com.celestino.model.util.ClinicException;
import com.celestino.model.util.ConnectionClinic;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Doctor> findAll() {

		List<Doctor> doctors = new ArrayList<Doctor>();

		Statement stmt;

		try {
			String sql = "Select * from CLINIC_DB.doctor";
			stmt = ConnectionClinic.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Doctor d = new Doctor();
				d.setId(rs.getInt(1));
				d.setDoctorName(rs.getString(2));
				d.setGender(rs.getString(3));
				d.setBornDate(rs.getDate(4));
				d.setMaritalStatus(rs.getString(5));
				d.setCrmNumber(rs.getInt(6));
				d.setSpecialty(rs.getString(7));
				d.setDocType(rs.getString(8));
				d.setDocNumber(rs.getString(9));
				d.setNationality(rs.getString(10));
				d.setEmail(rs.getString(11));
				d.setPhoneNumber(rs.getString(12));
				d.setAddress(rs.getString(13));
				d.setPostalCode(rs.getInt(14));
				d.setCountry(rs.getString(15));
				d.setCreatedAt(rs.getTimestamp(16));

				doctors.add(d);
			}
			ConnectionClinic.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctors;
	}

	public void deleteAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM clinic_db.doctor";
			conn = ConnectionClinic.getConnection();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveDoctor(Doctor doctor) throws ClinicException {
		Connection conn = null;
		PreparedStatement ps = null;

		if (doctor == null)
			throw new ClinicException("Value cannot be null!!");
		try {
			String sql = "Insert into doctor(doctor_name,gender,born_date,marital_status,crm_number,specialty,doc_type,doc_number,nationality,email,phone_number,address,postal_code,country)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			conn = ConnectionClinic.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, doctor.getDoctorName());
			ps.setString(2, doctor.getGender());
			ps.setDate(3, new Date(doctor.getBornDate().getTime()));
			ps.setString(4, doctor.getMaritalStatus());
			ps.setInt(5, doctor.getCrmNumber());
			ps.setString(6, doctor.getSpecialty());
			ps.setString(7, doctor.getDocType());
			ps.setString(8, doctor.getDocNumber());
			ps.setString(9, doctor.getNationality());
			ps.setString(10, doctor.getEmail());
			ps.setString(11, doctor.getPhoneNumber());
			ps.setString(12, doctor.getAddress());
			ps.setInt(13, doctor.getPostalCode().intValue());
			ps.setString(14, doctor.getCountry());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateDoctor(Doctor doctor) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String sql = "UPDATE doctor set doctor_name=?, gender=?, born_date=?, marital_status=?, crm_number=?, specialty=?, doc_type=?, doc_number=?, nationality=?, email=?, phone_number=?, address=?, postal_code=?, country=?"
					+ "WHERE id=?";
			conn = ConnectionClinic.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, doctor.getDoctorName());
			ps.setString(2, doctor.getGender());
			ps.setDate(3, new Date(doctor.getBornDate().getTime()));
			ps.setString(4, doctor.getMaritalStatus());
			ps.setInt(5, doctor.getCrmNumber());
			ps.setString(6, doctor.getSpecialty());
			ps.setString(7, doctor.getDocType());
			ps.setString(8, doctor.getDocNumber());
			ps.setString(9, doctor.getNationality());
			ps.setString(10, doctor.getEmail());
			ps.setString(11, doctor.getPhoneNumber());
			ps.setString(12, doctor.getAddress());
			ps.setInt(13, doctor.getPostalCode().intValue());
			ps.setString(14, doctor.getCountry());
			ps.setLong(15, doctor.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteById(Long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "Delete from doctor where id=?";
			conn = ConnectionClinic.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Doctor findByName(String name) {
		Connection conn = null;
		PreparedStatement ps = null;
		Doctor doctor = null;

		String sql = "SELECT * FROM clinic_db.doctor WHERE clinic_db.doctor.doctor_name = ?";
		try {
			conn = ConnectionClinic.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				doctor = new Doctor();
				doctor.setId(rs.getInt(1));
				doctor.setDoctorName(rs.getString(2));
				doctor.setGender(rs.getString(3));
				doctor.setBornDate(rs.getDate(4));
				doctor.setMaritalStatus(rs.getString(5));
				doctor.setCrmNumber(rs.getInt(6));
				doctor.setSpecialty(rs.getString(7));
				doctor.setDocType(rs.getString(8));
				doctor.setDocNumber(rs.getString(9));
				doctor.setNationality(rs.getString(10));
				doctor.setEmail(rs.getString(11));
				doctor.setPhoneNumber(rs.getString(12));
				doctor.setAddress(rs.getString(13));
				doctor.setPostalCode(rs.getInt(14));
				doctor.setCountry(rs.getString(15));
				doctor.setCreatedAt(rs.getTimestamp(16));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctor;
	}
}