package com.esmile.appEsmile.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false, length = 12)
    private String cpf;

    @Column(nullable = false)
    private String address;

    public Patient(String name, String lastname, String cpf, String address) {
        this.name = name;
        this.lastname = lastname;
        this.cpf = cpf;
        this.address = address;
    }
}
