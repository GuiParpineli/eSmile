package com.esmile.appEsmile.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Patient {

    private int id;
    private String name, lastname, cpf;
    private String address;

    public Patient(String name, String lastname, String cpf, String address) {
        this.name = name;
        this.lastname = lastname;
        this.cpf = cpf;
        this.address = address;
    }
}
