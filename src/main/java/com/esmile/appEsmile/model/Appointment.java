package com.esmile.appEsmile.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Appointment {

    private int id;
    private Patient patient;
    private Dentist dentist;

    private Date appointmentDate;

    public Appointment(Patient patient, Dentist dentist,  Date appointmentDate) {
        this.patient = patient;
        this.dentist = dentist;
        this.appointmentDate = appointmentDate;
    }
}
