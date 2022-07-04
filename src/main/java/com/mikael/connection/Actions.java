package com.mikael.connection;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.PreparedStatement;

@Getter
@Setter
@Data
public class Actions {
    private String usuario;
    private String email;
    private String senha;

    public void inserir(){
        DataBaseConnector con = new DataBaseConnector();
        String creat = "INSERT INTO usuarios (usuario,email,senha) values (?,?,?)";

        try{
            //Abrir a conexão
           con.conectar();
           //Preparando a query para execução do banco de dados
            PreparedStatement pst = con.conectar().prepareStatement(creat);
            //Substituir os parâmetros (?) pelo conteúdo das variaveis acima
            pst.setString(1,usuario);
            pst.setString(2,email);
            pst.setString(3,senha);
            //dando update na query
            pst.executeUpdate();
            //Encerrando a conexão com o banco
            con.close();
            System.out.println("Usuário inserido com sucesso");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

        }catch (Exception e){

        }
    }

}
