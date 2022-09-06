package com.esmile.appEsmile.dao.impl;

import com.esmile.appEsmile.dao.ConfiguracaoJDBC;
import com.esmile.appEsmile.dao.IDao;
import com.esmile.appEsmile.model.Address;
import com.esmile.appEsmile.model.Patient;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class PatientDAOH2 implements IDao<Patient> {

    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger log = Logger.getLogger(PatientDAOH2.class);

    @Override
    public List<Patient> getAll() throws SQLException {
        log.info("Abrindo conexão no banco");

        Connection connection = null;
        Statement stmt = null;

        final String query = "SELECT * FROM patient";
        List<Patient> patients = new ArrayList<>();

        try {

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/esmile;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa", "");
            connection = configuracaoJDBC.getConnection();

            ResultSet resultSet = stmt.executeQuery(query);

            log.debug("Buscando todos os pacientes da clinica");

            while (resultSet.next())
                patients.add(createobjectPatient(resultSet));
        } catch (SQLException e) {
            log.error("Erro ao buscar");
            e.printStackTrace();
        } finally {
            log.debug("Fechando conexão...");
            connection.close();
        }
        return patients;
    }

    @Override
    public Patient get(int id) throws SQLException {

        log.info("Abrindo uma conexão");

        final String SQLSelect = "SELECT * FROM patient WHERE id = ?";

        Connection connection = null;
        Patient patient = null;

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
                patient = createobjectPatient(resultSet);
            }
        } catch (SQLException e) {
            log.error("Erro");
            e.printStackTrace();
        } finally {
            log.info("conexão finalizada");
            connection.close();
        }
        return patient;
    }

    @Override
    public Patient save(Patient patient) throws SQLException {
        log.info("Abrindo Conexao");

        final String SQLInsert = "INSERT INTO patient (firstName, lastName, cpf, address) VALUES(?,?,?,?)";

        Connection connection = null;

        try {
            log.info("Registrando Paciente : " + patient.getName());

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/esmile;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa", "");
            connection = configuracaoJDBC.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(SQLInsert, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = ps.getGeneratedKeys();

            if (resultSet.next()) {
                patient.setId(resultSet.getInt(1));
            }

            ps.setString(1, patient.getName());
            ps.setString(2, patient.getLastname());
            ps.setString(3, patient.getCpf());
            ps.setString(4, patient.getAddress());
            ps.execute();

            connection.setAutoCommit(true);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Erro ao inserir o paciente: " + patient.getName());

        } finally {
            log.info("Conexao Finalizada");
            connection.close();
        }
        return patient;
    }

    @Override
    public void update(Patient patient) throws SQLException {

        log.info("Abrindo Conexao");

        final String SQLUpdate = "UPDATE patient SET firstName = ?, lastName = ?, cpf = ?, address =? WHERE  " +
                "id = ?";

        Connection connection = null;

        try {
            log.info("Alterando Dados do Paciente : " + patient.getName());

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/esmile;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa", "");
            connection = configuracaoJDBC.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(SQLUpdate);

            ps.setString(1, patient.getName());
            ps.setString(2, patient.getLastname());
            ps.setString(3, patient.getCpf());
            ps.setString(4, patient.getAddress());
            ps.setInt(5, patient.getId());

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
    public void delete(Patient patient) throws SQLException {

        log.info("Abrindo Conexao");

        final String SQLDelete = "DELETE FROM patient WHERE id = ?";

        Connection connection = null;

        try {
            log.info("Deletando o Paciente : " + patient.getName());

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/esmile;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa", "");
            connection = configuracaoJDBC.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(SQLDelete);

            ps.setInt(1, patient.getId());
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

    public Patient createobjectPatient(ResultSet result) throws SQLException {

        Integer id = result.getInt("id");
        String name = result.getString("firstName");
        String lastName = result.getString("lastName");
        String cpf = result.getString("cpf");
        String address = result.getString("address");

        return new Patient(id, name, lastName, cpf, address);

    }
}
