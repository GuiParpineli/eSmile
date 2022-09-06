package com.esmile.appEsmile.service;

import com.esmile.appEsmile.dao.IDao;
import com.esmile.appEsmile.model.Dentist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DentistService {

    @Autowired
    IDao<Dentist> dentistIDao;

    public List<Dentist> getAll() throws SQLException {
        return dentistIDao.getAll();
    }

    public Dentist get(int id) throws SQLException {
        return dentistIDao.get(id);
    }

    public Dentist save(Dentist dentist) throws SQLException {
        return dentistIDao.save(dentist);
    }

    public void update(Dentist dentist) throws SQLException {
        dentistIDao.update(dentist);
    }

    public void delete(Dentist dentist) throws SQLException {
        dentistIDao.delete(dentist);
    }
}
