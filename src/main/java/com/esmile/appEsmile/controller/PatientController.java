package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.dto.PatientDTO;
import com.esmile.appEsmile.entity.Patient;
import com.esmile.appEsmile.service.impl.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/paciente")
public class PatientController {

    @Autowired
    PatientService service;

    @PostMapping
    public Patient savePatient(@RequestBody Patient patient) {
//        patient.setAddress(address);
        return service.save(patient);
    }

    @GetMapping
    public ResponseEntity get(@RequestParam("id") Long id) {
        ObjectMapper mapper = new ObjectMapper();

        Optional<Patient> patientOptional = service.get(id);
        if (patientOptional.isEmpty()) {
            return new ResponseEntity("Nenhum paciente encontrado", HttpStatus.NOT_FOUND);
        }

        PatientDTO patientDTO = mapper.convertValue(patientOptional, PatientDTO.class);

        return new ResponseEntity(patientDTO, HttpStatus.OK);
    }

    @GetMapping("/todos")
    public ResponseEntity getAll() {

        ObjectMapper mapper = new ObjectMapper();
        List<PatientDTO> patientDTOS = new ArrayList<>();

        List<Patient> patients = service.getAll();

        if (patients.isEmpty()) {
            return new ResponseEntity("Nenhum paciente cadastrado", HttpStatus.NOT_FOUND);
        }

        for (Patient p : patients) {
        patientDTOS.add(mapper.convertValue(p, PatientDTO.class));
        }

        return new ResponseEntity(patientDTOS, HttpStatus.OK);

    }

    @PutMapping
    public void update(@RequestBody Patient patient) {
        service.update(patient);
    }

    @DeleteMapping
    public void excluir(@RequestBody Patient patient) {
        service.delete(patient);
    }

}
