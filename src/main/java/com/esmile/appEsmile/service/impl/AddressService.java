package com.esmile.appEsmile.service.impl;

import com.esmile.appEsmile.entity.Address;
import com.esmile.appEsmile.exception.ResourceNotFoundException;
import com.esmile.appEsmile.repository.IAdressRepository;
import com.esmile.appEsmile.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService implements IService<Address> {

    @Autowired
    IAdressRepository repository;

    @Override
    public List<Address> getAll() {
        return  repository.findAll();
    }

    @Override
    public Optional<Address> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public Address save(Address address) {
        return repository.save(address);
    }

    @Override
    public void update(Address address) {
        repository.saveAndFlush(address);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        if (repository.findById(id).isEmpty())
            throw new ResourceNotFoundException("");
        repository.deleteById(id);
    }
}
