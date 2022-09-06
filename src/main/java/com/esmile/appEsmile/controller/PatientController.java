package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.entity.Patient;
import com.esmile.appEsmile.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping
    public Patient savePatient(@RequestBody Patient patient) {
        return patientService.save(patient);
    }

    @GetMapping
    public Optional<Patient> get(@RequestParam("id") Long id) {
        return patientService.get(id);
    }

    @GetMapping("/todos")
    public List<Patient> getAll() {
        return patientService.getAll();
    }

    @PutMapping
    public void update(@RequestBody Patient patient) {
        patientService.update(patient);
    }

    @DeleteMapping
    public void excluir(@RequestBody Patient patient) {
        patientService.delete(patient);
    }

}
