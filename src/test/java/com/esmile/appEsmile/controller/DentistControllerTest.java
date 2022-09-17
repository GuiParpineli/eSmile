package com.esmile.appEsmile.controller;


import com.esmile.appEsmile.AppEsmileApplicationTests;
import com.esmile.appEsmile.entity.Dentist;
import com.esmile.appEsmile.service.impl.DentistService;

import com.fasterxml.jackson.databind.ObjectMapper;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(DentistController.class)
class DentistControllerTest {

    @Autowired
    private DentistController controller;

    @MockBean
    private DentistService service;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.controller);
    }

    @Test
    void save() {
    }

    @Test
    void get() {
        when(this.service.get(1L))
                .thenReturn(Optional.of(new Dentist(
                        1L,
                        "Filipe",
                        "Farias",
                        "123456"
                )));

        given().accept(ContentType.JSON)
                .when().get("/dentista?id=1")
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}