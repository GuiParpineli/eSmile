package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.entity.AppUser;
import com.esmile.appEsmile.entity.Dentist;
import com.esmile.appEsmile.login.UserRoles;
import com.esmile.appEsmile.service.impl.DentistService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class DentistControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    DentistService service;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    Dentist RECORD_01 = new Dentist(
            1L, "Filipe",
            "Farias",
            "123456",
            AppUser.builder()
                .username("admin")
                .email("admin@email.com")
                .password(bCryptPasswordEncoder.encode("admin"))
                .userRoles(UserRoles.ROLE_ADMIN)
                .build()
    );
    Dentist RECORD_02 = new Dentist(
            2L,
            "Guilherme",
            "Parpineli",
            "654321",
            AppUser.builder()
                .username("admin")
                .email("admin@email.com")
                .password(bCryptPasswordEncoder.encode("admin"))
                .userRoles(UserRoles.ROLE_ADMIN)
                .build()
    );
    Dentist RECORD_03 = new Dentist(
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

    @Test
    @DisplayName("Deve Retornar OK - Salvar dentista")
    void saveDentist_success() {
        Dentist dentist = Dentist.builder()
                .name("Maiara")
                .lastname("Martinelli")
                .cro("643152")
                .build();

        when(service.save(dentist))
                .thenReturn(dentist);

        try {
            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                    .post("/dentista")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(this.mapper.writeValueAsString(dentist));

            mockMvc.perform(mockRequest)
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Deve Retornar OK - Buscar dentista")
    void getDentist_success() {
        when(service.get(RECORD_03.getId()))
                .thenReturn(Optional.of(RECORD_03));

        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/dentista?id=3")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", notNullValue()))
                    .andExpect(jsonPath("$.name", is("Lucas")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Deve Retornar OK - Buscar todos dentistas")
    void getAllDentists_success() {
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
    @DisplayName("Deve Retornar OK - Atualizar dentista")
    void updateDentist_success() {
        Dentist updatedDentist = Dentist.builder()
                .id(1L)
                .name("Filipe")
                .lastname("Farias")
                .cro("357984")
                .build();

        when(service.get(RECORD_01.getId()))
                .thenReturn(Optional.of(RECORD_01));
        when(service.save(updatedDentist))
                .thenReturn(updatedDentist);

        try {
            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                    .post("/dentista")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(this.mapper.writeValueAsString(updatedDentist));

            mockMvc.perform(mockRequest)
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Deve Retornar OK - Deletar dentista")
    void deleteDentist_success() {
        when(service.get(RECORD_02.getId()))
                .thenReturn(Optional.of(RECORD_02));

        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .delete("/dentista?id=2")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}