package com.esmile.appEsmile.entity;

import lombok.*;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;
    @OneToOne
    @JoinColumn(name = "id_dentist")
    private Dentist dentist;

    //@Column(nullable = false)
    //private Date appointmentDate;
    private Timestamp appointmentDate;

//    public Appointment(Patient patient, Dentist dentist,  Timestamp appointmentDate) {
//        this.patient = patient;
//        this.dentist = dentist;
//        this.appointmentDate = appointmentDate;
//    }
}
