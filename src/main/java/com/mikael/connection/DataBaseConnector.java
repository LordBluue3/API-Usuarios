package com.mikael.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
    private String drive = "org.mariadb.jdbc.Driver";
    private String url = "jdbc:mariadb://localhost:3306/dbusuarios";
    private String usuario = "root";
    private String senha = "";

    Connection con =null;
    public Connection conectar() {
        try {
            Class.forName(drive);
            con = DriverManager.getConnection(url,usuario,senha);
            System.out.println("connection on");
            return con;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}


