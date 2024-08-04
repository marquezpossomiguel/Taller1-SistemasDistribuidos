import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        //Socket del servidor
        ServerSocket serverSocket;
        //Socket del cliente
        Socket customerSocket;
        //Canal de comunicación de los sockets
        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;
        //Puerto al que se conecta el socket del servidor
        final int PUERTO = 5000;

        try {
            //El socket del servidor se conecta al puerto
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor conectado");
            while(true){
                //El socket servidor espera o escuha las peticiones del cliente, se guarda el socket cliente
                customerSocket = serverSocket.accept();
                System.out.println("El cliente se ha conectado");

                //Comunicación del cliente al servidor, se lee al cliente, se reciben mensajes
                dataInputStream = new DataInputStream(customerSocket.getInputStream());
                //Comunicación del servidor al cliente, se escribe al cliente, se envian mensajes
                dataOutputStream = new DataOutputStream(customerSocket.getOutputStream());

                //El servidor espera a que el cliente envie un mensaje
                String mensajeCliente = dataInputStream.readUTF();

                //Se imprime el mensaje leido del cliente
                System.out.println("Mensaje del cliente: " + mensajeCliente);

                //El servidor le envia un mensaje al cliente
                dataOutputStream.writeUTF("Recibi el mensaje, desde el servidor");

                //Cerrando el socket cliente
                customerSocket.close();
                System.out.println("El cliente se ha desconectado");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}