package com.esmile.appEsmile.repository;

import com.esmile.appEsmile.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<Patient, Long> {
}
