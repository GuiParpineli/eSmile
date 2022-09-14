package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.entity.Dentist;
import com.esmile.appEsmile.service.impl.DentistService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DentistController.class)
class DentistControllerTest {

    private MockMvc mvc;

//    @Autowired
//    private DentistService controller;
//
//    @BeforeEach
//    void setUp() {
//        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
//    }

    @Test
    void save() throws Exception {
        Dentist dentist = new Dentist(null, "Filipe", "Farias", "123456");

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(dentist);

        this.mvc.perform(MockMvcRequestBuilders.post("/dentista")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string(
                        "location",
                        Matchers.containsString("http://localhost:8080/dentista")));
    }

    @Test
    void get() {
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