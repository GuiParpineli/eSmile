package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.entity.Dentist;
import com.esmile.appEsmile.service.impl.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentista")
public class DentistController {
    @Autowired
    DentistService dentistService;

    @PostMapping
    public Dentist save(@RequestBody Dentist dentist) {
        return dentistService.save(dentist);
    }

    @GetMapping
    public Optional<Dentist> get(@RequestParam("id") Long id) {
        return dentistService.get(id);
    }

    @GetMapping("/todos")
    public List<Dentist> getAll() {
        return dentistService.getAll();
    }

    @PutMapping
    public void update(@RequestBody Dentist dentist) {
        dentistService.update(dentist);
    }

    @DeleteMapping
    public void delete(@RequestBody Dentist dentist) {
        dentistService.delete(dentist);
    }
}

