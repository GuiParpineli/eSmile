package com.esmile.appEsmile.dao.impl;

import com.esmile.appEsmile.dao.ConfiguracaoJDBC;
import com.esmile.appEsmile.dao.IDao;
import com.esmile.appEsmile.model.Appointment;
import com.esmile.appEsmile.model.Dentist;
import com.esmile.appEsmile.model.Patient;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class AppointmentDAOH2 implements IDao<Appointment> {

    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger log = Logger.getLogger(AppointmentDAOH2.class);

    @Override
    public List<Appointment> getAll() throws SQLException {
        log.info("Abrindo conexão no banco");

        Connection connection = null;
        Statement stmt = null;

        final String query = "SELECT * FROM appointment";
        List<Appointment> appointments = new ArrayList<>();

        try {

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/esmile;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa", "");
            connection = configuracaoJDBC.getConnection();

            ResultSet resultSet = stmt.executeQuery(query);

            log.debug("Buscando todas suas consultas");

            while (resultSet.next())
                appointments.add(createObjectAppointment(resultSet));
        } catch (SQLException e) {
            log.error("Erro ao buscar");
            e.printStackTrace();
        } finally {
            log.debug("Fechando conexão...");
            connection.close();
        }
        return appointments;
    }

    @Override
    public Appointment get(int id) throws SQLException {

        log.info("Abrindo uma conexão");

        final String SQLSelect = "SELECT * FROM  appointment WHERE id = ?";

        Connection connection = null;
        Appointment appointment = null;

        try {
            log.info("Buscando...");

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/esmile;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa", "");
            connection = configuracaoJDBC.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(SQLSelect);

            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                appointment = createObjectAppointment(resultSet);
            }
        } catch (SQLException e) {
            log.error("Erro");
            e.printStackTrace();
        } finally {
            log.info("conexão finalizada");
            connection.close();
        }
        return appointment;
    }

    @Override
    public Appointment save(Appointment appointment) throws SQLException {

        log.info("Abrindo Conexão");

        final String SQLInsert = "INSERT INTO appointment (patientId, destistId, appointmentDate) VALUES (?,?,?)";

        Connection connection = null;

        try {
            log.info("Agendando Consulta do paciente :" + appointment.getPatient().getName());

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/esmile;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa", "");
            connection = configuracaoJDBC.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(SQLInsert, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = ps.getGeneratedKeys();

            if (resultSet.next()) {
                appointment.setId(resultSet.getInt(1));
            }

            ps.setInt(1, appointment.getPatient().getId());
            ps.setInt(2, appointment.getDentist().getId());
            ps.setDate(3, (Date) appointment.getAppointmentDate());
            ps.execute();

            connection.setAutoCommit(true);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Erro ao agendar consulta do paciente: " + appointment.getPatient().getName());

        } finally {
            log.info("Conexao Finalizada");
            connection.close();
        }

        return appointment;

    }

    @Override
    public void update(Appointment appointment) throws SQLException {

        log.info("Abrindo Conexao");

        final String SQLUpdate = "UPDATE appointment SET appointmentDate = ? WHERE  " +
                "id = ?";

        Connection connection = null;

        try {
            log.info(" Reagendando consulta para : " + appointment.getAppointmentDate());

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/esmile;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa", "");
            connection = configuracaoJDBC.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(SQLUpdate);

            ps.setDate(1, (Date) appointment.getAppointmentDate());
            ps.setInt(2, appointment.getId());

            ps.execute();
            connection.setAutoCommit(true);

        } catch (Exception e) {
            log.error("Erro ao tentar atualizar");
            e.printStackTrace();
        } finally {
            log.info("Fechando conexão");
            connection.close();
        }
    }

    @Override
    public void delete(Appointment appointment) throws SQLException {
        log.info("Abrindo Conexao");

        final String SQLDelete = "DELETE FROM appointment WHERE id = ?";

        Connection connection = null;

        try {
            log.info("Deletando consulta : " + appointment.getPatient() + "do dia:" + appointment.getAppointmentDate());

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/esmile;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa", "");
            connection = configuracaoJDBC.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(SQLDelete);

            ps.setInt(1, appointment.getId());
            ps.execute();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            log.error("Erro ao excluir");
            e.printStackTrace();
        } finally {
            log.info("Fechando conexão");
            connection.close();
        }
    }

    public Appointment createObjectAppointment(ResultSet result) throws SQLException {

        Integer id = result.getInt("id");
        Integer patientId = result.getInt("patientId");
        Integer dentistId = result.getInt("dentistId");
        Date appointmentDate = result.getDate("appointmentDate");

        PatientDAOH2 patientDAOH2 = new PatientDAOH2();
        Patient patient = null;
        patient = patientDAOH2.get(patientId);

        DentistDAOH2 dentistDAOH2 = new DentistDAOH2();
        Dentist dentist = null;
        dentist = dentistDAOH2.get(dentistId);

        return new Appointment(id, patient, dentist, appointmentDate);
    }
}
