package com.esmile.appEsmile.dao.impl;

import com.esmile.appEsmile.dao.ConfiguracaoJDBC;
import com.esmile.appEsmile.dao.IDao;
import com.esmile.appEsmile.model.Patient;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.List;

@Configuration
public class PatientDAOH2  implements IDao<Patient> {

    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger log = Logger.getLogger(PatientDAOH2.class);

    @Override
    public List<Patient> getAll() throws SQLException {
        return null;
    }

    @Override
    public Patient get(int id) throws SQLException {
        return null;
    }

    @Override
    public Patient save(Patient patient) throws SQLException {
        return null;
    }

    @Override
    public void update(Patient patient) throws SQLException {

    }

    @Override
    public void delete(Patient patient) throws SQLException {

    }
}
