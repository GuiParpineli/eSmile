package com.esmile.appEsmile.service.impl;

import com.esmile.appEsmile.entity.Patient;
import com.esmile.appEsmile.repository.IPatientRepository;
import com.esmile.appEsmile.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IService<Patient> {

    private final IPatientRepository patientRepository;

    @Autowired
    public PatientService(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> get(Long id) throws Exception {
        if (patientRepository.findById(id).isEmpty())
            throw new Exception();
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
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    public List<Patient> findByName(String name) {
        return patientRepository.findByName(name);
    }

    public Optional<Patient> findByCpf(String cpf) {
        return patientRepository.findByCpf(cpf);
    }


}
