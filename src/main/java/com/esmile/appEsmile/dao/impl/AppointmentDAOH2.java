package com.esmile.appEsmile.dao.impl;

import com.esmile.appEsmile.dao.ConfiguracaoJDBC;
import com.esmile.appEsmile.dao.IDao;
import com.esmile.appEsmile.model.Appointment;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
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

        log.info("Abrindo Conexão");

        final String SQLInsert = "INSERT INTO appointment (patientId, destistId, appointmentDate) VALUES (?,?,?)";

        Connection connection = null;

        try{
            log.info("Agendando Consulta do paciente :" + appointment.getPatient().getName());

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/esmile;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa","");
            connection = configuracaoJDBC.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(SQLInsert, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = ps.getGeneratedKeys();

            if(resultSet.next()){
                appointment.setId(resultSet.getInt(1));
            }

            ps.setInt(1,appointment.getPatient().getId());
            ps.setInt(2,appointment.getDentist().getId());
            ps.setDate(3, (Date) appointment.getAppointmentDate());
            ps.execute();

            connection.setAutoCommit(true);

        }catch (Exception e){
            e.printStackTrace();
            log.error("Erro ao agendar consulta do paciente: " + appointment.getPatient().getName());

        }finally {
            log.info("Conexao Finalizada");
            connection.close();
        }

        return appointment;

    }

    @Override
    public void update(Appointment appointment) throws SQLException {

    }

    @Override
    public void delete(Appointment appointment) throws SQLException {

    }
}
