package com.esmile.appEsmile.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {

    public List<T> getAll() throws SQLException;

    public T get(int id) throws SQLException;

    public T save(T t) throws SQLException;

    public void update(T t) throws SQLException;

    public void delete(T t) throws SQLException;
}
