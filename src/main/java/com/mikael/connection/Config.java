package com.mikael.connection;

import lombok.Getter;
@Getter
public class Config {
    private String drive = "org.mariadb.jdbc.Driver";
    private String url = "jdbc:mariadb://localhost:3306/dbusuarios";
    private String usuario = "root";
    private String senha = "";

}
