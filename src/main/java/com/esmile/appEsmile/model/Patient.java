package com.esmile.appEsmile.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Patient {

    private int id;
    private String name, lastname, cpf;
    private Address address;

    public Patient(String name, String lastname, String cpf, Address address) {
        this.name = name;
        this.lastname = lastname;
        this.cpf = cpf;
        this.address = address;
    }
}
