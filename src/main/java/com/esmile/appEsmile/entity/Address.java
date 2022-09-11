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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String  number;
    @Column(nullable = false)
    private String  neighborhood;

    private String  zipcode;
    @Column(nullable = false)
    private String  city;
    @Column(nullable = false)
    private String  state;

    public Address(String street, String number, String neighborhood, String zipcode, String city, String state) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
    }
}
