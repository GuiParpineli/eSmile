package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.dto.PatientDTO;
import com.esmile.appEsmile.entity.Patient;
import com.esmile.appEsmile.exception.ResourceNotFoundException;
import com.esmile.appEsmile.service.impl.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/paciente")
public class PatientController {

    private final PatientService service;

    @Autowired
    public PatientController(PatientService service) {this.service = service;}

    @PostMapping
    public ResponseEntity savePatient(@RequestBody Patient patient) {
        try {
            service.save(patient);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Paciente ja existente ou dado invalido");
        }
        return ResponseEntity.ok(String.format("Paciente %s salvo com sucesso", patient.getName()));
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
//    public void delete(@RequestBody Dentist dentist) {
    public void delete(@RequestParam("id") Long id) throws ResourceNotFoundException {
        if (service.get(id).isEmpty()) {
            throw new ResourceNotFoundException("id nao encontrado");
        }
        service.delete(id);
        //        service.delete(dentist);
    }

    @GetMapping("/name")

    public List<Patient> get(@RequestParam("name") String name) throws ResourceNotFoundException {


        return service.findByName(name);
    }
}
