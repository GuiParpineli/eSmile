package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.entity.Address;
import com.esmile.appEsmile.entity.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
class PatientControllerTest {

    @Autowired
    PatientController controller;

    static Patient patient;

    @BeforeAll
    static void doBefore() {
        patient = Patient.builder()
                .name("")
                .build();
    }


    @Test
    void savePatient() {
        Patient patientSaved = controller.savePatient(patient);

        Assertions.assertNotNull(patientSaved.getId());

    }

    @Test
    void get() {

        ResponseEntity patientSaved = controller.get(1L);

        Assertions.assertNotNull(patientSaved);
    }

    @Test
    void getAll() {

        List<ResponseEntity> patients = Arrays.asList(controller.getAll());

        Assertions.assertNotNull(patients);
    }

    @Test
    void update() {


    }

    @Test
    void excluir() {
    }
}