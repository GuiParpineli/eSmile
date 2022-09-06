package com.esmile.appEsmile.service;

import com.esmile.appEsmile.entity.Dentist;
import com.esmile.appEsmile.repository.IDentistRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DentistService implements IService<Dentist>{

    IDentistRepository dentistRepository;
    @Override
    public List<Dentist> getAll() {
        return dentistRepository.findAll();
    }

    @Override
    public Optional<Dentist> get(Long id) {
        return dentistRepository.findById(id);
    }

    @Override
    public Dentist save(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    @Override
    public void update(Dentist dentist) {
        dentistRepository.saveAndFlush(dentist);
    }

    @Override
    public void delete(Dentist dentist) {
        dentistRepository.delete(dentist);
    }
}
