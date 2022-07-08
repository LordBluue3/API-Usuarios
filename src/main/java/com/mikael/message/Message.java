package com.mikael.message;





import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

import com.mikael.connection.Actions;
import com.mikael.model.JavaBeans;
import lombok.SneakyThrows;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;



public class Message {



    JavaBeans javaBeans = new JavaBeans(null,null,null,null);

    private final Socket socket;
    private List<String> listOfMessages = null;

    public Message(Socket socket, List<String> listOfMessages) {
        this.socket = socket;
        this.listOfMessages = listOfMessages;

    }
    /*
     * Método para ler as mensagens que foram enviadas da aplicação
     *
     * 0btem o fluxo de entrada do soquete conectado
     * Cria um ObjectInputStream que possa ler os dados desse fluxo
     * Lê as mensagens que estão armazenadas na variavel objectInputStream
     */
    @SneakyThrows
    public void readMessage(){
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                listOfMessages = (List<String>) objectInputStream.readObject();
            System.out.println("Revisto [" + listOfMessages.size() + "] mensagem ao: " + socket);
    }
    /*
    *  Método para imprimir as mensagens
    *  foreach no ListOfMessages
    *  Transformando essa String em Json
    *  Transformando esse json em String e setando esses dados nas variaveis user, email e password
    *  Chamando o método da class Action inserir
    */
    @SneakyThrows
    public void PrintOutMessage(){
        Actions action = new Actions();
        for (String msg : listOfMessages) {

                JSONObject parsed = (JSONObject) new JSONParser().parse(msg);
                System.out.println("=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=");
                System.out.println("Mensagem Recebida: ");
                javaBeans.setUsuario((String) parsed.get("user"));
                javaBeans.setEmail((String) parsed.get("email"));
                javaBeans.setSenha((String) parsed.get("password"));
                System.out.println(javaBeans.getUsuario());
                System.out.println(javaBeans.getEmail());
                System.out.println(javaBeans.getSenha());
                action.inserir(javaBeans);
                sendMessage();
        }
    }
    /*
    * Método que envia mensagem a aplicação
    */
    @SneakyThrows
    public  void sendMessage(){
        DataOutputStream dataOutputStream = null;
        Actions actions = new Actions();

            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF("Usuário cadastrado com sucesso");
            System.out.println("enviando mensagem a aplicação");
    }
   /* public void enviarUsuarios(){
        DataOutputStream dos = null;
        Actions actions = new Actions();
        try{
            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("receba");
            System.out.println("enviando usuário");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    */


}
