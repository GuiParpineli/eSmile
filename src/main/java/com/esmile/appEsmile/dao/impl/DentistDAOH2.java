package com.esmile.appEsmile.dao.impl;

import com.esmile.appEsmile.dao.ConfiguracaoJDBC;
import com.esmile.appEsmile.dao.IDao;
import com.esmile.appEsmile.model.Dentist;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DentistDAOH2 implements IDao<Dentist> {

    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger log = Logger.getLogger(DentistDAOH2.class);

    @Override
    public List<Dentist> getAll() throws SQLException {
        log.info("Abrindo conexão no banco");

        Connection connection = null;
        Statement stmt = null;

        final String query = "SELECT * FROM dentist";
        List<Dentist> dentist = new ArrayList<>();

        try {

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/esmile;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa", "");
            connection = configuracaoJDBC.getConnection();

            ResultSet resultSet = stmt.executeQuery(query);

            log.debug("Buscando todos os dentistas da clinica");

            while (resultSet.next())
                dentist.add(createobjectDentist(resultSet));
        } catch (SQLException e) {
            log.error("Erro ao buscar");
            e.printStackTrace();
        } finally {
            log.debug("Fechando conexão...");
            connection.close();
        }
        return dentist;
    }

    @Override
    public Dentist get(int id) throws SQLException {
        log.info("Abrindo uma conexão");

        final String SQLSelect = "SELECT * FROM dentist WHERE id = ?";

        Connection connection = null;
        Dentist dentist = null;

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
                dentist = createobjectDentist(resultSet);
            }
        } catch (SQLException e) {
            log.error("Erro");
            e.printStackTrace();
        } finally {
            log.info("conexão finalizada");
            connection.close();
        }
        return dentist;
    }

    @Override
    public Dentist save(Dentist dentist) throws SQLException {
        log.info("Abrindo Conexao");


        final String SQLInsert = "INSERT INTO dentist (firstName, lastName, cro) VALUES(?,?,?)";

        Connection connection = null;

        try {
            log.info("Registrando Dentista: " + dentist.getName());

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/esmile;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa", "");
            connection = configuracaoJDBC.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(SQLInsert, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = ps.getGeneratedKeys();

            if (resultSet.next()) {
                dentist.setId(resultSet.getInt(1));
            }

            ps.setString(1, dentist.getName());
            ps.setString(2, dentist.getLastname());
            ps.setString(3, dentist.getCro());
            ps.execute();

            connection.setAutoCommit(true);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Erro ao inserir o dentista : " + dentist.getName());

        } finally {
            log.info("Conexao Finalizada");
            connection.close();
        }
        return dentist;
    }

    @Override
    public void update(Dentist dentist) throws SQLException {

        log.info("Abrindo Conexao");

        final String SQLUpdate = "UPDATE dentist SET firstName = ?, lastName = ?, cro = ?, WHERE  " +
                "id = ?)";

        Connection connection = null;

        try {
            log.info("Alterando Dados do Dentista : " + dentist.getName());

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/esmile;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa", "");
            connection = configuracaoJDBC.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(SQLUpdate);

            ps.setString(1, dentist.getName());
            ps.setString(2, dentist.getLastname());
            ps.setString(3, dentist.getCro());

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
    public void delete(Dentist dentist) throws SQLException {

        log.info("Abrindo Conexao");

        final String SQLDelete = "DELETE FROM dentist WHERE id = ?";

        Connection connection = null;

        try {
            log.info("Deletando o Dentista : " + dentist.getName());

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/esmile;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa", "");
            connection = configuracaoJDBC.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(SQLDelete);

            ps.setInt(1, dentist.getId());
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
    public Dentist createobjectDentist(ResultSet result) throws SQLException {

        Integer id = result.getInt("id");
        String name = result.getString("firstName");
        String lastName = result.getString("lastName");
        String cro = result.getString("cro");

        return new Dentist(id, name, lastName, cro);

    }
}
