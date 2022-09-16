package com.esmile.appEsmile.service.impl;

import com.esmile.appEsmile.entity.Patient;
import com.esmile.appEsmile.exception.ResourceNotFoundException;
import com.esmile.appEsmile.repository.IPatientRepository;
import com.esmile.appEsmile.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IService<Patient> {

    @Autowired
    IPatientRepository patientRepository;

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> get(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void update(Patient patient) {

        patientRepository.saveAndFlush(patient);
    }

    @Override
    public void delete(Patient patient) {

//        try {
//            patientRepository.deleteById(patient.getId());
//            return ResponseEntity.ok("Patient Deleted");
//        } catch (Exception ex) {
//            throw new ResourceNotFoundException("Error to find Patient");
//        }
        if (get(patient.getId()).isPresent()) {
            System.out.println("Encontrou ID Paciente");
            patientRepository.delete(patient);
            //return ;
        }

    }
}
