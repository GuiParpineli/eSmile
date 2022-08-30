package com.esmile.appEsmile.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Dentist {

    private int id;
    private  String name, lastname, cro;

    public Dentist(String name, String lastname, String cro) {
        this.name = name;
        this.lastname = lastname;
        this.cro = cro;
    }
}
