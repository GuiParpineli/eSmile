package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.dto.PatientDTO;
import com.esmile.appEsmile.entity.Patient;
import com.esmile.appEsmile.exception.ResourceNotFoundException;
import com.esmile.appEsmile.exception.UserCadastradoExecption;
import com.esmile.appEsmile.service.impl.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/paciente")
public class PatientController {

    private final PatientService service;

    @Autowired
    public PatientController(PatientService service) {this.service = service;}

    @PostMapping
    public ResponseEntity<?> savePatient(@RequestBody Patient patient) throws UserCadastradoExecption {
        try {
            service.save(patient);
        } catch (Exception e) {
            throw new UserCadastradoExecption("Paciente ja existente ou dado invalido");
        }
        return ResponseEntity.ok(String.format("Paciente %s salvo com sucesso", patient.getName()));
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam("id") Long id) throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Optional<Patient> patientOptional = service.get(id);
            return ResponseEntity.ok(mapper.convertValue(patientOptional.get(), PatientDTO.class));
        } catch (Exception e) {
            throw new ResourceNotFoundException("Paciente nao encontrado");
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<?> getAll() throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        List<PatientDTO> patientDTOS = new ArrayList<>();
        try {
            List<Patient> patients = service.getAll();
            for (Patient p : patients) {
                patientDTOS.add(mapper.convertValue(p, PatientDTO.class));
            }
            return new ResponseEntity(patientDTOS, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Nenhum paciente cadastrado");
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Patient patient) throws ResourceNotFoundException {
        try {
            service.update(patient);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Usuario nao atualizado");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") Long id) throws ResourceNotFoundException {
        try {
            if (service.get(id).isPresent())
                service.delete(id);
            return new ResponseEntity("Usuario deltado", HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("id nao encontrado");
        }
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<?> get(@RequestParam("name") String name) throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        List<PatientDTO> patientDTOS = new ArrayList<>();
        try {
            List<Patient> patients = service.findByName(name);
            for (Patient p : patients) {
                patientDTOS.add(
                        mapper.convertValue(p, PatientDTO.class)
                );
            }
            return ResponseEntity.ok(patientDTOS);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Nada encontrado");
        }
    }
}
