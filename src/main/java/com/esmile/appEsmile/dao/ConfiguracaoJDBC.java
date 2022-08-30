package com.esmile.appEsmile.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;

@AllArgsConstructor
public class ConfiguracaoJDBC {

    private String driverJDBC, urlJDBC, user, password;

    public Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName(this.driverJDBC);
            connection = DriverManager.getConnection(this.urlJDBC, this.user, this.password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  connection;
    }
}
