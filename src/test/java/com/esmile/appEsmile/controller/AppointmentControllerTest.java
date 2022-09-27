package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.entity.*;
import com.esmile.appEsmile.exception.ResourceNotFoundException;
import com.esmile.appEsmile.login.UserRoles;
import com.esmile.appEsmile.service.impl.AppointmentService;
import com.esmile.appEsmile.service.impl.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AppointmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    AppointmentService service;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    Address address = Address.builder()
            .city("BH")
            .neighborhood("Centro")
            .number("1234")
            .street("Rua a")
            .zipcode("123456.000")
            .state("MG")
            .build();

    Patient PATIENT_01 = new Patient(
            1L,
            "Filipe",
            "Farias",
            "123.456.789.00",
            address,
            AppUser.builder()
                    .username("admin")
                    .email("admin@email.com")
                    .password(bCryptPasswordEncoder.encode("admin"))
                    .userRoles(UserRoles.ROLE_ADMIN)
                    .build()
    );
    Patient PATIENT_02 = new Patient(
            2L,
            "Guilherme",
            "Parpineli",
            "223.456.789.00",
            address,
            AppUser.builder()
                    .username("admin")
                    .email("admin@email.com")
                    .password(bCryptPasswordEncoder.encode("admin"))
                    .userRoles(UserRoles.ROLE_ADMIN)
                    .build()
    );

    Dentist DENTIST_03 = new Dentist(
            3L,
            "Lucas",
            "Rosa",
            "134625",
            AppUser.builder()
                    .username("admin")
                    .email("admin@email.com")
                    .password(bCryptPasswordEncoder.encode("admin"))
                    .userRoles(UserRoles.ROLE_ADMIN)
                    .build()
    );

    Appointment appointment1 = Appointment.builder()
            .id(1L)
            .dentist(DENTIST_03)
            .patient(PATIENT_01)
            .build();

    @Test
    @DisplayName("Salvar Consulta - Retorno N_OK - A criar")
    void save() {

    }

    @Test
    @DisplayName("GET Consulta Teste badRequest 4xx- Deve retornar OK")
    void get() throws ResourceNotFoundException {
        when(service.get(appointment1.getId()))
                .thenReturn(Optional.ofNullable(appointment1));

        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/agendamento?id=678")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is4xxClientError());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("A criar 1")
    void getAll() {
    }

    @Test
    @DisplayName("A criar 2")
    void update() {
    }

    @Test
    @DisplayName("A criar 3")
    void delete() {
    }
}