package Chat;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorSocket extends Thread{

    private static ServidorSocket servidorSocket;
    private static ServerSocket server;
    private static ArrayList<BufferedWriter> clients;
    private Socket connection;
    private InputStream inputStream;
    private BufferedReader bufferedReader;
    private InputStreamReader inputStreamReader;
    public String name;

    public static void main(String[] args) throws IOException {

        try {
            server = new ServerSocket(7000);
            clients = new ArrayList<BufferedWriter>();

            while (true){
                Socket connection = server.accept();
                Thread thread = new ServidorSocket(connection);
                thread.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            server.close();
        }


        /*try {
            servidor = new ServerSocket(7000);
            while (true) {
                conexao = servidor.accept();
                ConnectionHandler connectionHandler = new ConnectionHandler(conexao);
                new Thread(connectionHandler).start();
            }

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            conexao.close();
            servidor.close();
        }*/

    }

    public ServidorSocket(Socket connection) {
        this.connection = connection;
        try {
            inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            String message;
            Writer writer = new OutputStreamWriter(connection.getOutputStream());
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            clients.add(bufferedWriter);
            message = name = bufferedReader.readLine();

            while(message.equalsIgnoreCase("sair") && message != null){
                message = bufferedReader.readLine();
                System.out.println(message);
                printOnClients(bufferedWriter, message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printOnClients(BufferedWriter bufferedWriter, String message) throws IOException {

        BufferedWriter tempWriter;

        for (BufferedWriter client: clients) {
            tempWriter = (BufferedWriter) client;
            if (bufferedWriter != tempWriter){
                client.write(name + ": "+ message + "\r\n");
                client.flush();
            }
        }

    }

}
