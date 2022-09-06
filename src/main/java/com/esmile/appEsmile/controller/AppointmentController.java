package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.entity.Appointment;
import com.esmile.appEsmile.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendamento")
public class AppointmentController {


    AppointmentService appointmentService;

    @PostMapping
    public Appointment save(@RequestBody Appointment appointment) {
        return appointmentService.save(appointment);
    }

    @GetMapping
    public Optional<Appointment> get(@RequestParam("id") Long id) {
        return appointmentService.get(id);
    }

    @GetMapping("/todos")
    public List<Appointment> getAll() {
        return appointmentService.getAll();
    }

    @PutMapping
    public void update(@RequestBody Appointment appointment) {
        appointmentService.update(appointment);
    }

    @DeleteMapping
    public void delete(@RequestBody Appointment appointment) {
        appointmentService.delete(appointment);
    }
}
