package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.dto.AppointmentDTO;
import com.esmile.appEsmile.entity.Appointment;
import com.esmile.appEsmile.exception.AppointmentErrorException;
import com.esmile.appEsmile.exception.ResourceNotFoundException;
import com.esmile.appEsmile.exception.UserCadastradoExecption;
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
    private final AppointmentService service;

    @Autowired
    public AppointmentController(AppointmentService service) {this.service = service;}

    @PostMapping
    public ResponseEntity save(@RequestBody Appointment appointment) throws AppointmentErrorException {
        Appointment appointmentSave = null;
        try {
            service.save(appointment);
        } catch (Exception e) {
            throw new AppointmentErrorException("Erro ao agendar consulta");
        }
        return new ResponseEntity(appointmentSave, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity get(@RequestParam("id") Long id) {

        Optional<Appointment> appointmentOptional = service.get(id);
        if (appointmentOptional.isEmpty()) {
            return new ResponseEntity("Consulta não encontrada no sitema", HttpStatus.NOT_FOUND);
        }

        ObjectMapper mapper = new ObjectMapper();

        AppointmentDTO appointmentDTO = mapper.convertValue(appointmentOptional, AppointmentDTO.class);

        return new ResponseEntity(appointmentDTO, HttpStatus.OK);
    }

    @GetMapping("/todos")
    public ResponseEntity getAll() {
        List<Appointment> appointments = service.getAll();

        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();

        if (appointments.isEmpty()) {
            return new ResponseEntity("Nenhuma consulta encontratdo", HttpStatus.NOT_FOUND);
        }

        for (Appointment a : appointments) {
            appointmentDTOS.add(new AppointmentDTO(a.getDentist().getName() + " " + a.getDentist().getLastname(), a.getPatient().getName() + " " + a.getPatient().getLastname(), a.getAppointmentDate()));
        }
        return new ResponseEntity(appointmentDTOS, HttpStatus.OK);
    }

    @PutMapping
    public void update(@RequestBody Appointment appointment) {
        if (service.get(appointment.getId()).isEmpty()) {
            new ResponseEntity("Nao existem consultas com o parametro informado, nada foi alterado", HttpStatus.NOT_FOUND);
        }
        service.update(appointment);
    }

    @DeleteMapping
    public void delete(@RequestParam("id") Long id) throws ResourceNotFoundException {
        try {
            service.delete(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Usuario nao encontrado");
        }
    }
}