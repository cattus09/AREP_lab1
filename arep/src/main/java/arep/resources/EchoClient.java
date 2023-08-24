package arep.resources;

import java.io.*;
import java.net.*;

public class EchoClient {
    /**
     * Este método principal inicia un cliente de socket que se conecta a un servidor en localhost.
     * Permite al usuario enviar mensajes al servidor y muestra las respuestas del servidor en la consola.
     * @param args main.
     * @throws IOException Se lanza una excepción en caso de error de E/S.
     */
    public static void main(String[] args) throws IOException {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            echoSocket = new Socket("127.0.0.1", 35000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don’t know about host!.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn’t get I/O for "
            + "the connection to: localhost.");
            System.exit(1);
        }
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    String userInput;
    while ((userInput = stdIn.readLine()) != null) {
        out.println(userInput);
        System.out.println("echo: " + in.readLine());
    }
    out.close();
    in.close();
    stdIn.close();
    echoSocket.close();
    }
}