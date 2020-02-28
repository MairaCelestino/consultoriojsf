package com.celestino.model.repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.celestino.model.Patient;
import com.celestino.model.util.ClinicException;
import com.celestino.model.util.ConnectionClinic;

public class PatientRepository implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Patient> findAll() {

		List<Patient> patients = new ArrayList<Patient>();

		Statement stmt;

		try {
			String sql = "Select * from CLINIC_DB.patient";
			stmt = ConnectionClinic.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Patient p = new Patient();
				p.setId(rs.getInt(1));
				p.setPatientName(rs.getString(2));
				p.setGender(rs.getString(3));
				p.setBornDate(rs.getDate(4));
				p.setMarital_status(rs.getString(5));
				p.setDocType(rs.getString(6));
				p.setDocNumber(rs.getString(7));
				p.setNationality(rs.getString(8));
				p.setEmail(rs.getString(9));
				p.setPhoneNumber(rs.getString(10));
				p.setAddress(rs.getString(11));
				p.setPostal_code(rs.getInt(12));
				p.setCountry(rs.getString(13));
				p.setCreatedAt(rs.getTimestamp(14));

				patients.add(p);
			}
			ConnectionClinic.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patients;
	}

	public void deleteAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM clinic_db.patient";
			conn = ConnectionClinic.getConnection();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void savePatient(Patient patient) throws ClinicException {
		Connection conn = null;
		PreparedStatement ps = null;

		if (patient == null)
			throw new ClinicException("Value cannot be null!!");
		try {
			String sql = "Insert into patient(patient_name,gender,born_date,marital_status,doc_type,doc_number,nationality,email,phone_number,address,postal_code,country)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
			conn = ConnectionClinic.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, patient.getPatientName());
			ps.setString(2, patient.getGender());
			ps.setDate(3, (Date) patient.getBornDate());
			ps.setString(4, patient.getMarital_status());
			ps.setString(5, patient.getDocType());
			ps.setString(6, patient.getDocNumber());
			ps.setString(7, patient.getNationality());
			ps.setString(8, patient.getEmail());
			ps.setString(9, patient.getPhoneNumber());
			ps.setString(10, patient.getAddress());
			ps.setInt(11, patient.getPostal_code());
			ps.setString(12, patient.getCountry());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePatiente(Patient patient) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String sql = "Update patient set patient_name=? " + "gender=?" + "born_date=?" + "marital_status=?"
					+ "doc_type=?" + "doc_number=?" + "nationality=?" + "email=?" + "phone_number=?" + "address=?"
					+ "postal_code" + "country=?" + "where id=?";
			conn = ConnectionClinic.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, patient.getPatientName());
			ps.setString(2, patient.getGender());
			ps.setDate(3, (Date) patient.getBornDate());
			ps.setString(4, patient.getMarital_status());
			ps.setString(5, patient.getDocType());
			ps.setString(6, patient.getDocNumber());
			ps.setString(7, patient.getNationality());
			ps.setString(8, patient.getEmail());
			ps.setString(9, patient.getPhoneNumber());
			ps.setString(10, patient.getAddress());
			ps.setInt(11, patient.getPostal_code());
			ps.setString(12, patient.getCountry());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteById(Long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "Delete from patient where id=?";
			conn = ConnectionClinic.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Patient findByName(String name) {
		Connection conn = null;
		PreparedStatement ps = null;
		Patient patient = null;

		String sql = "SELECT * FROM clinic_db.patient WHERE clinic_db.user.patient_name = ?";
		try {
			conn = ConnectionClinic.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Patient p = new Patient();
				p.setId(rs.getInt(1));
				p.setPatientName(rs.getString(2));
				p.setGender(rs.getString(3));
				p.setBornDate(rs.getDate(4));
				p.setMarital_status(rs.getString(5));
				p.setDocType(rs.getString(6));
				p.setDocNumber(rs.getString(7));
				p.setNationality(rs.getString(8));
				p.setEmail(rs.getString(9));
				p.setPhoneNumber(rs.getString(10));
				p.setAddress(rs.getString(11));
				p.setPostal_code(rs.getInt(12));
				p.setCountry(rs.getString(13));
				p.setCreatedAt(rs.getTimestamp(14));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patient;
	}
}