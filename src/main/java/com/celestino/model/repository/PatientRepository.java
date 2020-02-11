package com.celestino.model.repository;

import com.celestino.model.Patient;
import com.celestino.model.util.ConnectionConsultorio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository {

    public static List<Patient> findAll() throws SQLException {
        String sql = "Select * from CLINIC_DB.patient";

        List<Patient> patients = new ArrayList<Patient>();

        Statement stmt = ConnectionConsultorio.getConnection().createStatement();
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
        ConnectionConsultorio.getConnection().close();

        return patients;
    }
}

