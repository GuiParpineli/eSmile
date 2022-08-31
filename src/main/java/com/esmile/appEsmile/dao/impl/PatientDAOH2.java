package com.esmile.appEsmile.dao.impl;

import com.esmile.appEsmile.dao.ConfiguracaoJDBC;
import com.esmile.appEsmile.dao.IDao;
import com.esmile.appEsmile.model.Patient;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Configuration
public class PatientDAOH2 implements IDao<Patient> {

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
        log.info("Abrindo Conexao");


        final String SQLInsert = "INSERT INTO patient (firstName, lastName, cpf, address) VALUES(?,?,?,?)";

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
    }

    @Override
    public void update(Patient patient) throws SQLException {

    }

    @Override
    public void delete(Patient patient) throws SQLException {

    }
}
