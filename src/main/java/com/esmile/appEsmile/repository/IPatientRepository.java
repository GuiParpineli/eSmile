package com.esmile.appEsmile.repository;

import com.esmile.appEsmile.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Repository

public interface IPatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByName();
    Patient finByCpf();
}
