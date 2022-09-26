package com.esmile.appEsmile.service;

import com.esmile.appEsmile.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

    public List<T> getAll() ;

    public Optional<T> get(Long id) throws Exception;

    public T save(T t) ;

    public void update(T t) ;

    public void delete(Long id) throws ResourceNotFoundException;
}

