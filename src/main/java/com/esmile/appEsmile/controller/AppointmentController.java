package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.dao.IDao;
import com.esmile.appEsmile.model.Appointment;
import com.esmile.appEsmile.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AppointmentController {


    AppointmentService appointmentService;

    @PostMapping
    public Appointment save(@RequestBody Appointment appointment) throws SQLException{
        return appointmentService.save(appointment);
    }

    @GetMapping
    public Appointment get(@RequestParam("id") int id) throws  SQLException{
        return appointmentService.get(id);
    }

    @GetMapping("/todos")
    public List<Appointment> getAll() throws SQLException {
        return appointmentService.getAll();
    }

    @PutMapping
    public void update(@RequestBody Appointment appointment) throws SQLException {
        appointmentService.update(appointment);
    }

    @DeleteMapping
    public void delete(@RequestBody Appointment appointment) throws SQLException {
        appointmentService.delete(appointment);
    }
}
