package com.celestino.test.model.repository;

import com.celestino.model.Patient;
import com.celestino.model.repository.PatientRepository;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class PatientRepositoryTest {

    @Test
    public void mustTestingFindAll() throws SQLException {

        List<Patient> patients = PatientRepository.findAll();
         System.out.println(patients.toString());
        Assert.assertEquals(2, patients.size());
    }
}
