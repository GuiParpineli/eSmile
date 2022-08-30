package com.esmile.appEsmile.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Address {

    private String street, number, neighborhood, zipcode, city, state;

}
