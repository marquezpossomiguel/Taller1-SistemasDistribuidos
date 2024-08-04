package src;


import java.io.*;
import java.net.*;

public class Servidor1 {
    private static final int PUERTO = 5001;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor1 conectado en el puerto " + PUERTO);

            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream entrada = new DataInputStream(socket.getInputStream());
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

                double cateto1 = entrada.readDouble();
                double cateto2 = entrada.readDouble();

                double sumaCuadrados = Math.pow(cateto1, 2) + Math.pow(cateto2, 2);

                salida.writeDouble(sumaCuadrados);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
