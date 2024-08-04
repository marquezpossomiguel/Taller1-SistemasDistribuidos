import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        //Puerto al cual se conecta el cliente
        final String HOST = "127.0.0.1";//Host local
        final int PUERTO = 5000;
        //Socket del cliente
        Socket customerSocket = null;
        //Canal de comunicación de los sockets
        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;

        try {
            //La conexión se crea apartir del mismo socket
            customerSocket = new Socket(HOST, PUERTO);
            System.out.println("Cliente conectado");

            //Comunicación del cliente al servidor, se lee al cliente, se reciben mensajes
            dataInputStream = new DataInputStream(customerSocket.getInputStream());
            //Comunicación del servidor al cliente, se escribe al cliente, se envian mensajes
            dataOutputStream = new DataOutputStream(customerSocket.getOutputStream());

            //El cliente le envia un mensaje al servidor
            dataOutputStream.writeUTF("Hola mundo desde el cliente");
            //El cliente espera a que el servidor envie un mensaje
            String mensajeServidor = dataInputStream.readUTF();
            //Se imprime el mensaje leido del servidor
            System.out.println("Mensaje del servidor: " + mensajeServidor);

            //Cerrando el socket cliente
            customerSocket.close();
            System.out.println("Cliente desconectado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}