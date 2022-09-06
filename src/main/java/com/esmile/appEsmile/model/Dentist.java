package com.esmile.appEsmile.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Dentist {

    private int id;
    private  String name, lastname, cro;

    public Dentist(String name, String lastname, String cro) {
        this.name = name;
        this.lastname = lastname;
        this.cro = cro;
    }
}
