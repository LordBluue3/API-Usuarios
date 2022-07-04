package com.mikael;

import com.mikael.connection.DataBaseConnector;
import com.mikael.message.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;
import java.util.List;

public class ServerSockett {

        public static void main(String[] args) throws IOException {

            ServerSocket ss = new ServerSocket(5000); //A porta que você colocou na sua aplicação
            List<String> listOfMessages = null;
            Socket socket;


            while(true) {
                System.out.println("ServerSocket está conectando...");
                System.out.println("Server conectado, aguardando requisições!!!");
                DataBaseConnector db = new DataBaseConnector();
                socket = ss.accept(); //faz com que o while não continue a menos que uma mensagem seja requisitada
                System.out.println("Conectado ao " + socket + "!");
                db.conectar();
                //ler mensagem do cliente
                List listOfmessagen = new ArrayList<String>();
                Message mensagem = new Message(socket, listOfMessages);
                mensagem.lerMensagens();
                mensagem.imprimirMensagem();
                socket.close();

            }
        }
}
