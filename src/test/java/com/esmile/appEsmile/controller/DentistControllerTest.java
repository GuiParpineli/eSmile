package com.esmile.appEsmile.controller;


import com.esmile.appEsmile.AppEsmileApplicationTests;
import com.esmile.appEsmile.entity.Dentist;
import com.esmile.appEsmile.repository.IDentistRepository;
import com.esmile.appEsmile.service.impl.DentistService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import io.restassured.http.ContentType;
import jdk.jfr.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DentistController.class)
class DentistControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

//    @MockBean
//    IDentistRepository dentistRepository;

    @MockBean
    DentistService service;

    Dentist RECORD_01 = new Dentist(1L, "Filipe", "Farias", "123456");
    Dentist RECORD_02 = new Dentist(2L, "Guilherme", "Parpineli", "654321");
    Dentist RECORD_03 = new Dentist(3L, "Lucas", "Rosa", "134625");

//    @Autowired
//    private DentistController controller;
//
//    @MockBean
//    private DentistService service;

//    @BeforeEach
//    public void setup() {
//        standaloneSetup(this.controller);
//    }

    @Test
    @DisplayName("Deve Retornar OK - Salvar dentista")
    void save() {
//        Dentist dentist = new Dentist(
//                1L,
//                "Maiara",
//                "Martinelli",
//                "643152"
//        );

        Dentist dentist1 = Dentist.builder()
                .name("Maiara")
                .lastname("Martinelli")
                .cro("643152")
                .build();

        when(service.save(dentist1))
                .thenReturn(dentist1);

        try {
            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                    .post("/dentista")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(this.mapper.writeValueAsString(dentist1));

            mockMvc.perform(mockRequest)
                    .andExpect(status().isOk());
//                    .andExpect(jsonPath("$", notNullValue()))
//                    .andExpect(jsonPath("$.name", is("Maiara")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Deve Retornar OK - Buscar dentista")
    void get() {
        when(service.get(RECORD_01.getId()))
                .thenReturn(Optional.of(RECORD_01));

        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/dentista?id=1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", notNullValue()))
                    .andExpect(jsonPath("$.name", is("Filipe")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        when(this.service.get(1L))
//                .thenReturn(Optional.of(new Dentist(
//                        1L,
//                        "Filipe",
//                        "Farias",
//                        "123456"
//                )));
//
//        given().accept(ContentType.JSON)
//                .when().get("/dentista?id=1")
//                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deve Retornar OK - Buscar todos dentistas")
    void getAll() {
        List<Dentist> dentists = new ArrayList<>(Arrays.asList(RECORD_01, RECORD_02, RECORD_03));

        when(service.getAll())
                .thenReturn(dentists);

        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/dentista/todos")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(3)))
                    .andExpect(jsonPath("$[1].name", is("Guilherme")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}