package com.esmile.appEsmile.repository;

import com.esmile.appEsmile.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IDentistRepository extends JpaRepository<Dentist, Long> {
}
