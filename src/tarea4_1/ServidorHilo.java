package tarea4_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import static tarea4_1.Servidor.numero;

/**
 *
 * @author Jesús Alvarez<jjalvarezgar@gmail.com>
 */
public class ServidorHilo extends Thread {

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int idSessio;

    public ServidorHilo(Socket socket, int idSessio) throws IOException {
        this.socket = socket;
        this.idSessio = idSessio;

        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {

        String mensaje = dis.readUTF();
        int mensajeAEntero = Integer.parseInt(mensaje);

        if (numero == mensajeAEntero) {
            tfServidor.setText("felicidades has acertado!!!");
            String mensajeDeSalida = "felicidades has acertado!!!";
            dos.writeUTF(mensajeDeSalida);
            misocket.close();

        } else if (numero < mensajeAEntero) {

            String mensajeDeSalida = "No has acertado el numero es MENOR";
            dos.writeUTF(mensajeDeSalida);
            tfServidor.setText("No es el número, prueba de nuevo...");
            Ssocket.close();

        } else if (numero > mensajeAEntero) {

            String mensajeDeSalida = "No has acertado el numero es MAYOR";
            dos.writeUTF(mensajeDeSalida);
            tfServidor.setText("No es el número, prueba de nuevo...");
            Ssocket.close();

        }

    }

}
