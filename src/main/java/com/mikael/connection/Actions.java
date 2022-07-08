package com.mikael.connection;

import com.mikael.message.Message;
import lombok.*;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Actions {

    private String idcon;
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
    public DataBaseConnector listarUsuarios(){
        ArrayList<Actions> usuarios = new ArrayList<>();
        Message msg = new Message(null, null);
        DataBaseConnector con = new DataBaseConnector();
        String read = "select * from usuarios order by usuario";
        try{
            con.conectar();
            PreparedStatement pst = con.close().prepareStatement(read);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String idcon = rs.getString(1);
                String usuario = rs.getString(2);
                String email = rs.getString(3);
                String senha = rs.getString(4);
                usuarios.add(new Actions(idcon,usuario,email,senha));
                msg.enviarMensagem();

            }
            con.close();
            return con;
        }catch (Exception f){
        }
        return con;
    }


}
