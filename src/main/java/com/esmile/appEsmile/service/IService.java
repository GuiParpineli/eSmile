package com.esmile.appEsmile.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

    public List<T> getAll() ;

    public Optional<T> get(Long id) ;

    public T save(T t) ;

    public void update(T t) ;

    public void delete(T t) ;
}

