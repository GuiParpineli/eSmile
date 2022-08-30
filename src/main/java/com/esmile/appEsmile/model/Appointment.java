package com.esmile.appEsmile.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Appointment {

    private int id;
    private Patient patient;
    private Dentist dentist;
    private LocalDate appointmentDate;
    private LocalDateTime appointmentTime;

    public Appointment(Patient patient, Dentist dentist, LocalDate appointmentDate, LocalDateTime appointmentTime) {
        this.patient = patient;
        this.dentist = dentist;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }
}
