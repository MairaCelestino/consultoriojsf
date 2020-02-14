package com.celestino.model.repository;

import com.celestino.model.Doctor;
import com.celestino.model.util.ClinicException;
import com.celestino.model.util.ConnectionClinic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {

    public static List<Doctor> findAll() throws SQLException {
        String sql = "Select * from CLINIC_DB.doctor";

        List<Doctor> doctors = new ArrayList<Doctor>();

        Statement stmt = ConnectionClinic.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Doctor d = new Doctor();
           d.setDoctorName(rs.getString(1));
           d.setGender(rs.getString(2));
           d.setBornDate(rs.getDate(3));
           d.setMarital_status(rs.getString(4));
           d.setCrmNumber(rs.getInt(5));

            doctors.add(d);
        }
        ConnectionClinic.getConnection().close();

        return doctors;
    }

    public void saveDoctor(Doctor doctor) throws ClinicException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        if (doctor == null)
            throw new ClinicException("Value cannot be null!!");
        try {

            conn = ConnectionClinic.getConnection();

            String sql = "Insert into doctor(doctor_name,gender,born_date,marital_status,crm_number,specialty,doc_type" +
                    "doc_number,nationality,email.phone_number,address,postal_code,country)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            ps = conn.prepareStatement(sql);

            ps.setString(1, doctor.getDoctorName());
            ps.setString(2, doctor.getGender());
            ps.setDate(3, (Date) doctor.getBornDate());
            ps.setString(4, doctor.getMarital_status());
            ps.setInt(5, doctor.getCrmNumber());
            ps.setString(6, doctor.getSpecialty());
            ps.setString(7, doctor.getDocType());
            ps.setString(8, doctor.getDocNumber());
            ps.setString(9, doctor.getNationality());
            ps.setString(10, doctor.getEmail());
            ps.setString(11, doctor.getPhoneNumber());
            ps.setString(12, doctor.getAddress());
            ps.setInt(13, doctor.getPostal_code());
            ps.setString(14, doctor.getCountry());
            ps.executeUpdate();

        } catch (SQLException sql) {
            throw new ClinicException("Error entering information" + sql);
        } finally {
            ConnectionClinic.getConnection().close();
        }

    }

    public void updateDoctor(Doctor doctor) throws ClinicException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        if (doctor == null)
            throw new ClinicException("Value cannot be null!!");
        try {

            conn = ConnectionClinic.getConnection();

            String sqle = "Update doctor set doctor_name=? " + "gender=?" + "born_date=?" + "marital_status=?" +"crm_number" + "specialty"+ "doc_type=?"
                    + "doc_number=?" + "nationality=?" + "email=?" + "phone_number=?" + "address=?" + "postal_code" + "country=?" + "where id=?";

            ps = conn.prepareStatement(sqle);
            ps.setString(1, doctor.getDoctorName());
            ps.setString(2, doctor.getGender());
            ps.setDate(3, (Date) doctor.getBornDate());
            ps.setString(4, doctor.getMarital_status());
            ps.setInt(5, doctor.getCrmNumber());
            ps.setString(6, doctor.getSpecialty());
            ps.setString(7, doctor.getDocType());
            ps.setString(8, doctor.getDocNumber());
            ps.setString(9, doctor.getNationality());
            ps.setString(10, doctor.getEmail());
            ps.setString(11, doctor.getPhoneNumber());
            ps.setString(12, doctor.getAddress());
            ps.setInt(13, doctor.getPostal_code());
            ps.setString(14, doctor.getCountry());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new ClinicException("Error updating information: " + sqle);
        } finally {
            ConnectionClinic.getConnection().close();
        }
    }

    public void deleteDoctor(Doctor doctor) throws ClinicException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        if (doctor == null)
            throw new ClinicException("Value cannot be null!!");
        try {
            conn = ConnectionClinic.getConnection();
            ps = conn.prepareStatement("Delete from doctor where id=?");
            ps.setInt(1, (int) doctor.getId());
            ps.executeUpdate();
            System.out.println("Record: " + doctor.getId());
        } catch (SQLException sqle) {
            throw new ClinicException("Error deleting doctor: " + sqle);
        } finally {
            ConnectionClinic.closeConnection(conn, ps);
        }

    }
}



