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
        log.info("Abrindo Conexao");


        final String SQLInsert = "INSERT INTO  appointment (firstName, lastName, cpf, address) VALUES(?,?,?,?)";

        Connection connection = null;

        try{
            log.info("Registrando Paciente : " + patient.getName());

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/esmile;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa","");
            connection = configuracaoJDBC.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(SQLInsert, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = ps.getGeneratedKeys();

            if(resultSet.next()){
                patient.setId(resultSet.getInt(1));
            }

            ps.setString(1, patient.getName());
            ps.setString(2,patient.getLastname());
            ps.setString(3,patient.getCpf());
            ps.setString(4,patient.getAddress().toString());
            ps.execute();

            connection.setAutoCommit(true);

        }catch (Exception e){
            e.printStackTrace();
            log.error("Erro ao inserir o paciente: " + patient.getName());

        }finally {
            log.info("Conexao Finalizada");
            connection.close();
        }


        return patient;
        return appointmentIDao.save(appointment);
    }

    public void update(Appointment appointment)throws SQLException {
        appointmentIDao.update(appointment);
    }

    public void delete(Appointment appointment) throws SQLException{
        appointmentIDao.delete(appointment);
    }

}
