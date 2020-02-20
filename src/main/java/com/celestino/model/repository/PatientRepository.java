package com.celestino.model.repository;

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

public class PatientRepository {

    public static List<Patient> findAll() throws SQLException {
        String sql = "Select * from CLINIC_DB.patient";

        List<Patient> patients = new ArrayList<Patient>();

        Statement stmt = ConnectionClinic.getConnection().createStatement();
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

        return patients;
    }

    public void savePatient(Patient patient) throws ClinicException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        if (patient == null)
            throw new ClinicException("Value cannot be null!!");
        try {

            conn = ConnectionClinic.getConnection();

            String sql = "Insert into patient(patient_name,gender,born_date,marital_status,doc_type" +
                    "doc_number,nationality,email.phone_number,address,postal_code,country)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
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

        } catch (SQLException sql) {
            throw new ClinicException("Error entering information" + sql);
        } finally {
            ConnectionClinic.getConnection().close();
        }

    }

    public void updatePatiente(Patient patient) throws ClinicException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        if (patient == null)
            throw new ClinicException("Value cannot be null!!");
        try {

            conn = ConnectionClinic.getConnection();

            String sql = "Update patient set patient_name=? " + "gender=?" + "born_date=?" + "marital_status=?" + "doc_type=?"
                    + "doc_number=?" + "nationality=?" + "email=?" + "phone_number=?" + "address=?" + "postal_code" + "country=?" + "where id=?";

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

        } catch (SQLException sql) {
            throw new ClinicException("Error updating information: " + sql);
        } finally {
            ConnectionClinic.getConnection().close();
        }
    }

    public void deletePatient(Patient patient) throws ClinicException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        if (patient == null)
            throw new ClinicException("Value cannot be null!!");
        try {
            conn = ConnectionClinic.getConnection();
            ps = conn.prepareStatement("Delete from patient where id=?");
            ps.setInt(1, (int) patient.getId());
            ps.executeUpdate();
            System.out.println("Record: " + patient.getId());
        } catch (SQLException sql) {
            throw new ClinicException("Error deleting patient: " + sql);
        } finally {
            ConnectionClinic.closeConnection(conn, ps);
        }

    }
}



