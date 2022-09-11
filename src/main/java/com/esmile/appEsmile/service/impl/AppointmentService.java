package com.esmile.appEsmile.service.impl;

import com.esmile.appEsmile.entity.Appointment;
import com.esmile.appEsmile.repository.IAppointmentRepository;
import com.esmile.appEsmile.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements IService<Appointment> {

    @Autowired
    IAppointmentRepository appointmentRepository;


    @Override
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> get(Long id) {
        return appointmentRepository.findById(id) ;
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void update(Appointment appointment) {
        appointmentRepository.saveAndFlush(appointment);
    }

    @Override
    public void delete(Appointment appointment) {
        appointmentRepository.delete(appointment);
    }
}
