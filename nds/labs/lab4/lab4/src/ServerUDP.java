import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Jose Torres Postigo
 */
public class ServerUDP {
    public static String extractText(String text) {

        String result = "";
        int step = Character.getNumericValue(text.charAt(0));

        for (int i = 1; i < text.length(); i += step + 1) {
            result += text.charAt(i);
        }

        return result;
    }

    public static void main(String[] args) {
        // SERVER DATA:

        // * FIXED: as comments if they are provided as command prompt input
        int port = 54322; // server port

        // * VARIABLES: uncomment them if they are provided as command prompt input
        // int port = Integer.parseInt(args[0]); // server port

        // SOCKET
        DatagramSocket server = null;

        // Create and initialise the server socket
        try {
            server = new DatagramSocket(port);

            // MAIN server function
            while (true) {
                // Create and initialise the EMPTY datagram to recieve the reply (max 400 bytes)
                byte[] buffer = new byte[400];
                DatagramPacket respone = new DatagramPacket(buffer, buffer.length);

                // Recieve the replied datagram
                server.receive(respone);

                // Get the recieved text
                String line = new String(
                        respone.getData(),
                        respone.getOffset(),
                        respone.getLength(),
                        StandardCharsets.UTF_8);

                // Print on screen the client's socket address (IP and port) and text
                System.out.println(
                        "Message: " + line + " received from " + respone.getAddress() + ":" + respone.getPort());

                // We analyse the line
                line = extractText(line);

                // Create reply datagram
                DatagramPacket reply = new DatagramPacket(
                        line.getBytes(StandardCharsets.UTF_8),
                        line.getBytes(StandardCharsets.UTF_8).length,
                        respone.getAddress(),
                        respone.getPort());

                // Send reply datagram
                server.send(reply);
            } // Service Loop End
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
