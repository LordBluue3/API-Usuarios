package com.mikael;

import com.mikael.connection.DataBaseConnector;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.List;

public class ServerSockett {

        public static int contador = 1;

        public static void main(String[] args) throws IOException {

            ServerSocket ss = new ServerSocket(5000);
            List<String> listOfMessages = null;
            Socket socket;


            while(true) {
                System.out.println("ServerSocket está conectando...");
                System.out.println("Server conectado, aguardando requisições!!!");
                DataBaseConnector db = new DataBaseConnector();
                db.conectar();
                socket = ss.accept(); //faz com que o while não continue a menos que uma mensagem seja requisitada
                System.out.println("Conectado ao " + socket + "!");

                socket.close();

            }
        }
}
