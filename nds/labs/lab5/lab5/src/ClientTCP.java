
/**
 *
 * @author Torres Postigo, Jose
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientTCP {

    public static void main(String[] args) throws IOException {
        // ! SERVER DATA:
        // * FIXED
        String serverName = "127.0.0.1"; // local address

        int serverPort = 12345;
        // * VARIABLES
        // String serverName = args[0];
        // int serverPort = Integer.parseInt(args[1]);

        // ! SOCKET
        Socket serviceSocket = null;

        // ! FLOWS FOR SENDING AND RECEIVING
        PrintWriter out = null;
        BufferedReader in = null;

        // * Create socket and connect to server
        serviceSocket = new Socket(serverName, serverPort);

        // * Initialise I/O streams of connected socket in PW and BF variables
        out = new PrintWriter(serviceSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(serviceSocket.getInputStream()));

        // * Receive welcome message from server and display it
        System.out.println(in.readLine());

        // * Get text from keyboard
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        System.out.println("Enter a text to be sent (to finish enter a text without leading digit)");
        userInput = stdIn.readLine();

        // * Check if the user has started the end of the interaction
        while (Character.isDigit(userInput.charAt(0))) { // service loop
            // * Send text in userInput to the server via the output stream of the connected
            // socket
            out.println(userInput);

            // * Receive text sent by the server via the input stream of the connected
            // socket
            String line = null;
            line = in.readLine();
            System.out.println("Server response: " + line + "\n");

            // Read user text by keyboard
            System.out.println("Enter a text to be sent (to finish enter a text without leading digit)");
            userInput = stdIn.readLine();
        } // End of client service loop

        // We exit because the client wants to end the interaction, it has entered a
        // String without a leading digit.
        // * Send END to the server to indicate the end of the service.
        System.out.println("Sending 'FINISH' to the server as no leading digit was entered ...");
        out.println("FINISH");

        // * Receive OK from Server
        System.out.println("Server response: " + in.readLine());

        // * Close flows and socket
        out.close();
        in.close();
        stdIn.close();
        serviceSocket.close();
    }
}
