package com.esmile.appEsmile.repository;

import com.esmile.appEsmile.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
}
