package com.esmile.appEsmile.service;

import com.esmile.appEsmile.entity.Patient;
import com.esmile.appEsmile.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IService<Patient>{

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
        patientRepository.delete(patient);
    }
}
