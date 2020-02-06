package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {

    private static Socket clienteSocket;
    private DataOutputStream dos;
    private DataInputStream dis;
    String result;

    public void clienteConnect(String ip, int puerto) {
        clienteSocket = new Socket();
        InetSocketAddress addr = new InetSocketAddress(ip, puerto);
        try {
            clienteSocket.connect(addr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String clienteCalcular(String num1, String num2, String operador) {
        try {
            dis = new DataInputStream(clienteSocket.getInputStream());
            dos = new DataOutputStream(clienteSocket.getOutputStream());

            String mensaje = num1 + "," + num2 + "," + operador + ",";
            // Enviamos datos.
            dos.write(mensaje.getBytes());

            //Recogemos datos.
            byte[] resultado = new byte[25];
            dis.read(resultado);
            String[] datos = new String(resultado).split(",");
            result = datos[0];


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public void clienteDisconnect(String ip, int puerto) {
        try {
            clienteSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
