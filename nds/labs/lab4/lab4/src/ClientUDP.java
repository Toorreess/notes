import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Jose Torres Postigo
 */

public class ClientUDP {
    public static void main(String[] args) throws IOException {
        // SERVER DATA:

        // * FIXED: as comments if they are provided as command prompt input
        String serverName = "127.0.0.1"; // localhost
        int serverPort = 54322;

        // * VARIABLES: uncomment them if they are provided as command prompt input
        // String serverName = args[0];
        // int serverPort = Integer.parseInt(args[1]);

        DatagramSocket serviceSocket = null;

        // Create socket
        serviceSocket = new DatagramSocket();

        // INITIALISE KEYBOARD INPUT
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        System.out.println("Type a text to send that starts with a digit (no initial digit means TERMINATE): ");
        userInput = stdIn.readLine(); /* STRING STORED IN userInput */

        // Check if the user wants to TERMINATE the service
        while (Character.isDigit(userInput.charAt(0))) {

            // Create a datagram with the given String
            DatagramPacket dp = new DatagramPacket(
                    userInput.getBytes(StandardCharsets.UTF_8),
                    userInput.getBytes(StandardCharsets.UTF_8).length,
                    InetAddress.getByName(serverName),
                    serverPort);

            // Send the datagram through the socket
            serviceSocket.send(dp);
            System.out.println("STATUS: Waiting for the reply");

            // Create and initialise an EMPTY datagram to recieve the reply (max 800 bytes)
            byte[] buffer = new byte[800];

            // Recieve the replied datagram
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            serviceSocket.receive(reply);
            // Extract the content of the datagram body in variable line
            String line = new String(
                    reply.getData(),
                    reply.getOffset(),
                    reply.getLength(),
                    StandardCharsets.UTF_8);

            System.out.println("echo: " + line);
            System.out.println("Type a text to send that starts with a digit (no initial digit means TERMINATE): ");
            userInput = stdIn.readLine();
        }

        System.out.println("STATUS: Closing client");

        // Close the client socket
        serviceSocket.close();
        System.out.println("STATUS: closed");
    }
}
