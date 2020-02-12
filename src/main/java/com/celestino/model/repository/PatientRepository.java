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

            patients.add(p);
        }
        ConnectionConsultorio.getConnection().close();

        return patients;
    }

    public void savePatient() throws SQLException {
        Patient p = new Patient();

        if (p == null)
            throw new SQLException("O valor passado não pode ser nulo!!!");

        try {

            String sql = "Insert into patient(patient_name,gender,born_date,marital_status,doc_type" +
                    "doc_number,nationality,email.phone_number,address,postal_code,country)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?)";


            Statement stmt = ConnectionConsultorio.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);


        }
    }

