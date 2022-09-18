package com.esmile.appEsmile.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String street;
    @NonNull
    private String  number;
    @NonNull
    private String  neighborhood;

    private String  zipcode;
    @NonNull
    private String  city;
    @NonNull
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
