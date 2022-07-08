package com.mikael.connection;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
    Config config = new Config();
    /*
     * Método para se conectar ao banco de dados
     */
    @SneakyThrows
    public Connection connection() {
            Class.forName(config.getDrive());
            Connection con = DriverManager.getConnection(config.getUrl(),config.getUsuario(), config.getSenha());
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("Conexão com o Banco de Dados realizado com exito");
            return con;
    }
    /*
     * Método para encerrar a conexão com o banco de dados
     */
    public Connection close() throws ClassNotFoundException, SQLException {
            Class.forName(config.getDrive());
            Connection con = DriverManager.getConnection(config.getUrl(),config.getUsuario(), config.getSenha());
            con.close();
            return con;
    }
}


