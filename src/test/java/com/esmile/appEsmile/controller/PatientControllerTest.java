package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.entity.*;
import com.esmile.appEsmile.entity.Patient;
import com.esmile.appEsmile.exception.ResourceNotFoundException;
import com.esmile.appEsmile.exception.UserCadastradoExecption;
import com.esmile.appEsmile.login.UserRoles;
import com.esmile.appEsmile.service.impl.PatientService;
import com.esmile.appEsmile.service.impl.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class PatientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    PatientService service;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    Address address = Address.builder()
            .city("BH")
            .neighborhood("Centro")
            .number("1234")
            .street("Rua a")
            .zipcode("123456.000")
            .state("MG")
            .build();

    Patient RECORD_01 = new Patient(
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
    Patient RECORD_02 = new Patient(
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
    Patient RECORD_03 = new Patient(
            3L,
            "Lucas",
            "Rosa",
            "323.456.789.00",
            address,
            AppUser.builder()
                    .username("admin")
                    .email("admin@email.com")
                    .password(bCryptPasswordEncoder.encode("admin"))
                    .userRoles(UserRoles.ROLE_ADMIN)
                    .build()
    );

    @Test
    @DisplayName("Salvar Paciente - Deve retornar OK")
    void savePatiente_success() {
        Patient patient = Patient.builder()
                .name("Gustavo")
                .lastname("Brock")
                .cpf("111.222.333.444-55")
                .build();

        when(service.save(patient))
                .thenReturn(patient);

        try{
            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                    .post("/paciente")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(this.mapper.writeValueAsString(patient));

            mockMvc.perform(mockRequest)
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Deve Retornar OK - Quando buscar paciente ")
    void getPatient_success() throws ResourceNotFoundException {
        when(service.get(RECORD_01.getId()))
                .thenReturn(Optional.ofNullable(RECORD_01));

                try{
                    mockMvc.perform(MockMvcRequestBuilders
                            .get("/paciente?id=1")
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$", notNullValue()))
                            .andExpect(jsonPath("$.lastname", is("Farias")));

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
    }

    @Test
    @DisplayName("Deve Retornar OK - Quando buscar todos os pacientes")
    void getAllPatient_success() throws ResourceNotFoundException {
        List<Patient> patients = new ArrayList<>(Arrays.asList(RECORD_01, RECORD_02, RECORD_03));

        when(service.getAll())
                .thenReturn(patients);

        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/paciente/todos")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(3)))
                    .andExpect(jsonPath("$[1].name", is("Guilherme")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}