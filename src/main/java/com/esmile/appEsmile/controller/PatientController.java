package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.dao.impl.PatientDAOH2;
import com.esmile.appEsmile.model.Patient;
import com.esmile.appEsmile.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping
    public Patient savePatient(@RequestBody Patient patient) throws SQLException{
        return patientService.save(patient);
    }

    @GetMapping
    public Patient get(@RequestParam("id") int id) throws SQLException{
        return patientService.get(id);
    }

    @GetMapping("/todos")
    public List<Patient> getAll() throws SQLException {
        return patientService.getAll();
    }

    @PutMapping
    public void update(@RequestBody Patient patient) throws SQLException{
        patientService.update(patient);
    }

    @DeleteMapping
    public void excluir(@RequestBody Patient patient) throws SQLException{
        patientService.delete(patient);
    }

}
