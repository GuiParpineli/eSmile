package com.esmile.appEsmile.dao.impl;

import com.esmile.appEsmile.dao.ConfiguracaoJDBC;
import com.esmile.appEsmile.dao.IDao;
import com.esmile.appEsmile.model.Dentist;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Configuration
public class DentistDAOH2 implements IDao<Dentist> {

    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger log = Logger.getLogger(DentistDAOH2.class);

    @Override
    public List<Dentist> getAll() throws SQLException {
        return null;
    }

    @Override
    public Dentist get(int id) throws SQLException {
        return null;
    }

    @Override
    public Dentist save(Dentist dentist) throws SQLException {
        log.info("Abrindo Conexao");


        final String SQLInsert = "INSERT INTO dentist (firstName, lastName, cro) VALUES(?,?,?)";

        Connection connection = null;

        try{
            log.info("Registrando Dentista: " + dentist.getName());

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/esmile;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa","");
            connection = configuracaoJDBC.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(SQLInsert, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = ps.getGeneratedKeys();

            if(resultSet.next()){
                dentist.setId(resultSet.getInt(1));
            }

            ps.setString(1,dentist.getName());
            ps.setString(2,dentist.getLastname());
            ps.setString(3,dentist.getCro());
            ps.execute();

            connection.setAutoCommit(true);

        }catch (Exception e){
            e.printStackTrace();
            log.error("Erro ao inserir o dentista : " + dentist.getName());

        }finally {
            log.info("Conexao Finalizada");
            connection.close();
        }
        return dentist;
    }

    @Override
    public void update(Dentist dentist) throws SQLException {

    }

    @Override
    public void delete(Dentist dentist) throws SQLException {

    }
}
