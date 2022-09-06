package com.esmile.appEsmile.controller;

import com.esmile.appEsmile.model.Dentist;
import com.esmile.appEsmile.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistController {
    @Autowired
    DentistService dentistService;

    @PostMapping
    public Dentist save(@RequestBody Dentist dentist) throws SQLException {
        return dentistService.save(dentist);
    }

    @GetMapping
    public Dentist get(@RequestParam("id") int id) throws SQLException {
        return dentistService.get(id);
    }

    @GetMapping("/todos")
    public List<Dentist> getAll() throws SQLException {
        return dentistService.getAll();
    }

    @PutMapping
    public void update(@RequestBody Dentist dentist) throws SQLException {
        dentistService.update(dentist);
    }

    @DeleteMapping
    public void delete(@RequestBody Dentist dentist) throws SQLException {
        dentistService.delete(dentist);
    }
}

