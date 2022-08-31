package com.esmile.appEsmile.service;

import com.esmile.appEsmile.dao.ConfiguracaoJDBC;
import com.esmile.appEsmile.dao.IDao;
import com.esmile.appEsmile.dao.impl.AppointmentDAOH2;
import com.esmile.appEsmile.dao.impl.PatientDAOH2;
import com.esmile.appEsmile.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    IDao<Appointment> appointmentIDao;

    public List<Appointment> getAll() throws SQLException {
        return appointmentIDao.getAll();
    }

    public Appointment get(int id) throws SQLException{
        return appointmentIDao.get(id);
    }

    public Appointment save(Appointment appointment) throws SQLException {
       return appointmentIDao.save(appointment);
    }

    public void update(Appointment appointment)throws SQLException {
        appointmentIDao.update(appointment);
    }

    public void delete(Appointment appointment) throws SQLException{
        appointmentIDao.delete(appointment);
    }

}
