package com.esmile.appEsmile.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDTO {

    private String dentistName;

    private String patientName;

    private Timestamp appointmentDate;

//    private String cro;
//
//    private String cpf;



}
