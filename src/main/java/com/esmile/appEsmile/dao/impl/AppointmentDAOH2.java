package com.esmile.appEsmile.dao.impl;

import com.esmile.appEsmile.dao.ConfiguracaoJDBC;
import com.esmile.appEsmile.dao.IDao;
import com.esmile.appEsmile.model.Appointment;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.List;

@Configuration
public class AppointmentDAOH2 implements IDao<Appointment> {

    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger log = Logger.getLogger(AppointmentDAOH2.class);


    @Override
    public List<Appointment> getAll() throws SQLException {
        return null;
    }

    @Override
    public Appointment get(int id) throws SQLException {
        return null;
    }

    @Override
    public Appointment save(Appointment appointment) throws SQLException {
        return null;
    }

    @Override
    public void update(Appointment appointment) throws SQLException {

    }

    @Override
    public void delete(Appointment appointment) throws SQLException {

    }
}
