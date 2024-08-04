package src;

import java.io.*;
import java.net.*;

public class Servidor2 {
    private static final int PUERTO = 5002;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor2 conectado en el puerto " + PUERTO);

            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream entrada = new DataInputStream(socket.getInputStream());
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

                double sumaCuadrados = entrada.readDouble();

                double raizCuadrada = Math.sqrt(sumaCuadrados);

                salida.writeDouble(raizCuadrada);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}