package com.esmile.appEsmile.service.impl;

import com.esmile.appEsmile.entity.AppUser;
import com.esmile.appEsmile.entity.Patient;
import com.esmile.appEsmile.exception.ResourceNotFoundException;
import com.esmile.appEsmile.login.UserRoles;
import com.esmile.appEsmile.repository.IAppUserRepository;
import com.esmile.appEsmile.repository.IPatientRepository;
import com.esmile.appEsmile.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IService<Patient> {

    private final IPatientRepository patientRepository;
    private final IAppUserRepository userRepository;

    @Autowired
    public PatientService(IPatientRepository patientRepository, IAppUserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

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
        Patient p = patientRepository.save(patient);
        userRepository.save(
                AppUser.builder()
                        .username(patient.getName())
                        .password(patient.getPassword())
                        .email(patient.getEmail())
                        .userRoles(UserRoles.ROLE_PATIENT)
                        .build()
        );
        return p;
    }

    @Override
    public void update(Patient patient) {
        patientRepository.saveAndFlush(patient);
    }

    @Override
    public void delete(Long id) {

//        if (get(patient.getId()) != null ) {
//            patientRepository.deleteById(patient.getId());
//        }
        patientRepository.deleteById(id);

    }
}
