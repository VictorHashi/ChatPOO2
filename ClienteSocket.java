package Chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSocket {

    public static void main(String[] args) throws IOException {
        final int PORTA = 7000;
        final String IP = "localhost";
        Scanner scan = new Scanner(System.in);
        System.out.print("Nome: ");
        String nome = scan.nextLine();
        String texto = "";
        Socket cliente = null;
        PrintStream saida = null;


        try{
            cliente = new Socket(IP,PORTA);
            saida = new PrintStream(cliente.getOutputStream());
            do {
                texto = scan.nextLine();
                saida.println(texto);
            }while (!"sair".equalsIgnoreCase(texto));

        } catch (IOException e) {
            System.out.println("Erro: "+e.getMessage());
        }finally {
            cliente.close();
        }

    }

}
