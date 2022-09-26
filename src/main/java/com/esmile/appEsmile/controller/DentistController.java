package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.dto.DentistDTO;
import com.esmile.appEsmile.entity.Dentist;
import com.esmile.appEsmile.exception.ResourceNotFoundException;
import com.esmile.appEsmile.exception.UserCadastradoExecption;
import com.esmile.appEsmile.repository.IDentistRepository;
import com.esmile.appEsmile.service.impl.DentistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentista")
public class DentistController {

    private final DentistService service;

    @Autowired
    public DentistController(DentistService service) {
        this.service = service;
    }

    @PostMapping
    public Dentist save(@RequestBody Dentist dentist) {
        return service.save(dentist);
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam("id") Long id) {
        ObjectMapper mapper = new ObjectMapper();
        Optional<Dentist> dentistOptional = service.get(id);
        if (dentistOptional.isEmpty()) {
            return new ResponseEntity("Nenhum dentista encontrado", HttpStatus.NOT_FOUND);
        }

        DentistDTO dentistDTO = mapper.convertValue(dentistOptional.get(), DentistDTO.class);

        return new ResponseEntity(dentistOptional, HttpStatus.OK);
    }

    @GetMapping("/todos")
    public ResponseEntity<?> getAll() {
        ObjectMapper mapper = new ObjectMapper();

        List<DentistDTO> dentistDTOS = new ArrayList<>();
        List<Dentist> dentists = service.getAll();

        if (dentists.isEmpty()) {
            return new ResponseEntity("Nenhum dentista encontrado", HttpStatus.NOT_FOUND);
        }
        for (Dentist d : dentists) {
            dentistDTOS.add(mapper.convertValue(d, DentistDTO.class));
        }
        return new ResponseEntity(dentistDTOS, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Dentist dentist) throws ResourceNotFoundException {
        try {
            service.update(dentist);
            return ResponseEntity.ok("Atualizado com sucesso");
        } catch (Exception e) {
            throw new ResourceNotFoundException("Usuario nao encontrado");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") Long id) throws UserCadastradoExecption {
        try {
            service.delete(id);
            return ResponseEntity.ok("Dentista deletado com sucesso");
        } catch (Exception e) {
            throw new UserCadastradoExecption("Usuario nao encontrado");
        }
    }
}

