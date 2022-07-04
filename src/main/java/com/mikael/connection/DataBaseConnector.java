package com.mikael.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
    Config config = new Config();
    public Connection conectar() {
        try {
            Class.forName(config.getDrive());
            Connection con = DriverManager.getConnection(config.getUrl(),config.getUsuario(), config.getSenha());
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("Conexão com o Banco de Dados realizado com exito");

            return con;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection close() throws ClassNotFoundException, SQLException {

            Class.forName(config.getDrive());
            Connection con = DriverManager.getConnection(config.getUrl(),config.getUsuario(), config.getSenha());
            con.close();
            return con;


    }
}


