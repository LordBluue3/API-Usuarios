package com.mikael.message;





import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

import com.mikael.connection.Actions;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;



public class Message {

    private final Socket socket;
    private List<String> listOfMessages = null;

    public Message(Socket socket, List<String> listOfMessages) {
        this.socket = socket;
        this.listOfMessages = listOfMessages;

    }
    public void lerMensagens(){

        try {
            //obter o fluxo de entrada do soquete conectado
            InputStream inputStream = socket.getInputStream();
            //crie um DataInputStream para que possamos ler os dados dele.
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            // Leia a lista de mensagens do socket

            try {
                listOfMessages = (List<String>) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Revisto [" + listOfMessages.size() + "] mensagem ao: " + socket);


        }catch (Exception e){
            System.out.println(e);

        }
    }

    public void imprimirMensagem(){
        Actions action = new Actions();
        for (String msg : listOfMessages) {

            try {
                JSONObject parsed = (JSONObject) new JSONParser().parse(msg);
                System.out.println("=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=");
                System.out.println("Mensagem Recebida: ");
                action.setUsuario((String) parsed.get("usuario"));
                action.setEmail((String) parsed.get("email"));
                action.setSenha((String) parsed.get("senha"));
                System.out.println(action.getUsuario());
                System.out.println(action.getEmail());
                System.out.println(action.getSenha());
                action.inserir();

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }


}
