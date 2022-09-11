package com.esmile.appEsmile.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Dentist dentist;

    @Column(nullable = false)
    private Date appointmentDate;

    public Appointment(Patient patient, Dentist dentist,  Date appointmentDate) {
        this.patient = patient;
        this.dentist = dentist;
        this.appointmentDate = appointmentDate;
    }
}
