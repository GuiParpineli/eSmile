package com.esmile.appEsmile.service.impl;

import com.esmile.appEsmile.entity.Dentist;
import com.esmile.appEsmile.exception.ResourceNotFoundException;
import com.esmile.appEsmile.repository.IDentistRepository;
import com.esmile.appEsmile.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistService implements IService<Dentist> {

    //    @Autowired
    private final IDentistRepository dentistRepository;

    public DentistService(IDentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

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
    public void delete(Long id) throws ResourceNotFoundException {
        if (dentistRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException("");
        dentistRepository.deleteById(id);
    }
}
