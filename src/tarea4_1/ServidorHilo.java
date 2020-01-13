package tarea4_1;

import java.awt.TextField;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tarea4_1.Servidor.numero;

/**
 *
 * @author Jesús Alvarez<jjalvarezgar@gmail.com>
 */
public class ServidorHilo extends Thread {

    private Socket misocket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int idSessio;
    public static TextField tfServidor;
    
    

    public ServidorHilo(Socket socket, int idSessio) throws IOException {
        this.misocket = socket;
        this.idSessio = idSessio;

        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {

        try {
            String mensaje = dis.readUTF();
            int mensajeAEntero = Integer.parseInt(mensaje);
            
            if (numero == mensajeAEntero) {
                try {
                    String mensajeDeSalida = "felicidades has acertado!!!";
                    dos.writeUTF(mensajeDeSalida);
                    System.out.println("felicidades has acertado!!!");
                    
//                    tfServidor.setText("felicidades has acertado!!!");
                    
                    
                    misocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }  if (numero < mensajeAEntero) {
                
                try {
                    String mensajeDeSalida = "No has acertado el numero es MENOR";
                    dos.writeUTF(mensajeDeSalida);
                    System.out.println("No es el número, prueba de nuevo...");
//                    tfServidor.setText("No es el número, prueba de nuevo...");
                    misocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }  if (numero > mensajeAEntero) {
                
                try {
                    String mensajeDeSalida = "No has acertado el numero es MAYOR";
                    dos.writeUTF(mensajeDeSalida);
                    System.out.println("No es el número, prueba de nuevo...");
//                    tfServidor.setText("No es el número, prueba de nuevo...");
                    misocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
