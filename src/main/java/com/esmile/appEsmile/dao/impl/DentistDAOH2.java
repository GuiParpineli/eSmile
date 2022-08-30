package com.esmile.appEsmile.dao.impl;

import com.esmile.appEsmile.dao.ConfiguracaoJDBC;
import com.esmile.appEsmile.dao.IDao;
import com.esmile.appEsmile.model.Dentist;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.List;

@Configuration
public class DentistDAOH2 implements IDao<Dentist> {

    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger log = Logger.getLogger(DentistDAOH2.class);

    @Override
    public List<Dentist> getAll() throws SQLException {
        return null;
    }

    @Override
    public Dentist get(int id) throws SQLException {
        return null;
    }

    @Override
    public Dentist save(Dentist dentist) throws SQLException {
        return null;
    }

    @Override
    public void update(Dentist dentist) throws SQLException {

    }

    @Override
    public void delete(Dentist dentist) throws SQLException {

    }
}
