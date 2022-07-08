package com.mikael.connection;

import com.mikael.message.Message;
import com.mikael.model.JavaBeans;
import lombok.*;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Actions {


    /*
     * Método que insere um usuário no banco de dados.
     * Chama os atributos encapsulados na class JavaBeans.
     *
     * Chama o método da class DataBaseConnector conectar.
     * Prepara a query para a execução no banco de dados.
     * Substituir os parâmetros (?) pelo conteúdo das variaveis acima.
     * Dando update na query
     * Encerrando a conexão com o banco
     */
    @SneakyThrows
    public void inserir(JavaBeans javaBeans){
       String usuario = javaBeans.getUsuario();
       String email = javaBeans.getEmail();
       String senha = javaBeans.getSenha();

        DataBaseConnector dataBaseConnector = new DataBaseConnector();
        String creatNewUser = "INSERT INTO usuarios (usuario,email,senha) values (?,?,?)";

           dataBaseConnector.connection();
            PreparedStatement preparedStatement = dataBaseConnector.connection().prepareStatement(creatNewUser);
            preparedStatement.setString(1,usuario);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,senha);
            preparedStatement.executeUpdate();
            dataBaseConnector.close();
            System.out.println("Usuário inserido com sucesso");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
   /* public DataBaseConnector listarUsuarios(){
        ArrayList<JavaBeans> usuarios = new ArrayList<>();
        Message msg = new Message(null, null);
        DataBaseConnector con = new DataBaseConnector();
        String read = "select * from usuarios order by usuario";
        try{
            con.conectar();
            PreparedStatement pst = con.conectar().prepareStatement(read);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String idcon = rs.getString(1);
                String usuario = rs.getString(2);
                String email = rs.getString(3);
                String senha = rs.getString(4);
                usuarios.add(new JavaBeans(idcon,usuario,email,senha));
                msg.enviarUsuarios();
                System.out.println("passou aqui");
            }
            con.close();
            return con;
        }catch (Exception f){
            f.printStackTrace();
        }
        return con;
    }
    */

}
