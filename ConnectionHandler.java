package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectionHandler implements Runnable{

    Socket socket = null;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while (true) {
                if (!((message = in.readLine()) != null))
                    break;
                System.out.println(message);
                enviarParaClientes(in,message);

            }
        }catch (IOException e){
            System.out.println("Erro ao se comunicar com o servidor");
        }
    }

    public void enviarParaClientes(BufferedReader bufferedReader, String message){

    }

}
