package src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    //Puerto al que se conecta el socket del servidor y los otros dos
    static final int PUERTO_PRINCIPAL = 5000;
    static final int PUERTO_SERVIDOR1 = 5001;
    static final int PUERTO_SERVIDOR2 = 5002;
    static String HOST = "127.0.0.1";

    public static void main(String[] args) {
        //Socket del servidor
        ServerSocket serverSocket;
        //Socket del cliente
        Socket customerSocket;
        //Canal de comunicación de los sockets
        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;



        try {
            //El socket del servidor se conecta al puerto
            serverSocket = new ServerSocket(PUERTO_PRINCIPAL);
            System.out.println("Servidor Principal conectado en el puerto " + PUERTO_PRINCIPAL);
            while(true){
                //El socket servidor espera o escuha las peticiones del cliente, se guarda el socket cliente
                customerSocket = serverSocket.accept();
                System.out.println("El cliente se ha conectado");

                //Comunicación del cliente al servidor, se lee al cliente, se reciben mensajes
                dataInputStream = new DataInputStream(customerSocket.getInputStream());
                //Comunicación del servidor al cliente, se escribe al cliente, se envian mensajes
                dataOutputStream = new DataOutputStream(customerSocket.getOutputStream());

                //El servidor espera a que el cliente envie un mensaje
                String dato1 = dataInputStream.readUTF();
                String dato2 = dataInputStream.readUTF();

                double cateto1 = Integer.parseInt(dato1);
                double cateto2 = Integer.parseInt(dato2);

                // Enviar a Servidor1 para calcular suma de cuadrados
                double sumaCuadrados = enviarAServidor1(cateto1, cateto2);

                // Enviar a Servidor2 para calcular raíz cuadrada
                double hipotenusa = enviarAServidor2(sumaCuadrados);

                //El servidor le envia un mensaje al cliente
                dataOutputStream.writeUTF(String.valueOf(hipotenusa));

                //Cerrando el socket cliente
                customerSocket.close();
                System.out.println("El cliente se ha desconectado");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static double enviarAServidor1(double cateto1, double cateto2) throws IOException {
        Socket socket = new Socket(HOST, PUERTO_SERVIDOR1);
        DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
        DataInputStream entrada = new DataInputStream(socket.getInputStream());

        salida.writeDouble(cateto1);
        salida.writeDouble(cateto2);

        double resultado = entrada.readDouble();
        socket.close();
        return resultado;
    }

    private static double enviarAServidor2(double sumaCuadrados) throws IOException {
        Socket socket = new Socket(HOST, PUERTO_SERVIDOR2);
        DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
        DataInputStream entrada = new DataInputStream(socket.getInputStream());

        salida.writeDouble(sumaCuadrados);

        double resultado = entrada.readDouble();
        socket.close();
        return resultado;
    }


}