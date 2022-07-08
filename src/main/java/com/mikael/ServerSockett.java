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

    /*
    * Método main que faz um loop que aguarda uma requisição da aplicação,
    * quando for requisitado algo será chamado o método make
    */
        public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(5000);
            List<String> listOfMessages = new ArrayList<String>();
            DataBaseConnector dataBaseConnector = new DataBaseConnector();
            Socket socket;

            while(true) {
                System.out.println("ServerSocket está conectando...");
                System.out.println("Server conectado, aguardando requisições!!!");
                socket = serverSocket.accept();
                System.out.println("Conectado ao " + socket + "!");
                make(dataBaseConnector, socket, listOfMessages);
                socket.close();

            }
        }
        /*
         * Método make diz todas as ações que o socket tem que fazer.
         * Chama o método conectar.
         * Chama o método ler mensagens.
         * Chama o método imprimir mensagem.
         */
        public static void make(DataBaseConnector dataBaseConnector, Socket socket, List listOfMessages){
            dataBaseConnector.connection();
            Message message = new Message(socket, listOfMessages);
            message.readMessage();
            message.PrintOutMessage();

        }
}
