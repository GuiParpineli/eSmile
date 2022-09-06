package com.esmile.appEsmile.service;

import com.esmile.appEsmile.dao.IDao;
import com.esmile.appEsmile.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    IDao<Patient> patientIDao;

    public List<Patient> getAll() throws SQLException{
        return patientIDao.getAll();
    }

    public Patient get(int id) throws  SQLException {
        return patientIDao.get(id);
    }

    public Patient save(Patient patient) throws SQLException{
        return patientIDao.save(patient);
    }

    public void update(Patient patient) throws SQLException{
        patientIDao.update(patient);
    }

    public void delete(Patient patient) throws SQLException{
        patientIDao.delete(patient);
    }
}
