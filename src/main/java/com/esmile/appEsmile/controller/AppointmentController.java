package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.dto.AppointmentDTO;
import com.esmile.appEsmile.dto.DentistDTO;
import com.esmile.appEsmile.entity.Appointment;
import com.esmile.appEsmile.entity.Dentist;
import com.esmile.appEsmile.service.impl.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendamento")
public class AppointmentController {
    @Autowired
    AppointmentService service;

    @PostMapping
    public ResponseEntity save(@RequestBody Appointment appointment) {
        Appointment appointmentSave = service.save(appointment);
        return new ResponseEntity(appointmentSave, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity get(@RequestParam("id") Long id) {
        Optional<Appointment> appointmentGet = service.get(id);
        if (appointmentGet.isEmpty()) {
            return new ResponseEntity("Consulta não encontrada no sitema", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(appointmentGet, HttpStatus.OK);
    }

    @GetMapping("/todos")
    public ResponseEntity getAll() {
        List<Appointment> appointments = service.getAll();
//        if (appointments.isEmpty()) {
//            return new ResponseEntity("Nenhuma consulta cadastrada no sistema", HttpStatus.NOT_FOUND);
//        }
            //return new ResponseEntity(appointments, HttpStatus.OK);

        //ObjectMapper mapper = new ObjectMapper();

        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();

        //List<Dentist> dentists = service.getAll();

        if(appointments.isEmpty()) {
            return new ResponseEntity("Nenhuma consulta encontratdo", HttpStatus.NOT_FOUND);
        }

        for(Appointment a: appointments) {
            appointmentDTOS.add(new AppointmentDTO(a.getDentist().getName()+" "+a.getDentist().getLastname(),a.getPatient().getName()+" "+a.getPatient().getLastname(),a.getAppointmentDate()));
        }



        return new ResponseEntity(appointmentDTOS, HttpStatus.OK);
    }

    @PutMapping
    public void update(@RequestBody Appointment appointment) {
         if(service.get(appointment.getId()).isEmpty()){
             new ResponseEntity("Nao existem consultas com o parametro informado, nada foi alterado", HttpStatus.NOT_FOUND);
         }

    }

    @DeleteMapping
    public void delete(@RequestBody Appointment appointment) {
        service.delete(appointment);
    }
}
