import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class ServerTCP {
    public static String extract_text(String s) {
        String res = "";
        int step = Character.getNumericValue(s.charAt(0));
        for (int i = 1; i < s.length(); i += step + 1)
            res += s.charAt(i);
        return res;
    }

    public static void main(String[] args) throws IOException {
        // ! SERVER DATA
        // * FIXED
        int port = 12345;
        // * VARIABLE
        // int port = Integer.parseInt(args[0]);

        // ! SOCKETS
        ServerSocket server = null; // Passive (receiving requests)
        Socket client = null; // Active (client service)

        // ! FLOWS FOR SENDING AND RECEIVING
        BufferedReader in = null;
        PrintWriter out = null;

        // * Create and initialise server socket
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("ServerTCP main() :: Could not listen on port " + port + ". " + e.getMessage());
            System.exit(-1);
        }

        System.out.println("ServerTCP main() :: Server started on port " + port + ". Waiting for connections ...");

        while (true) // Loop to receive incoming connections
        {
            // * Waiting for incoming connections
            try {
                client = server.accept();
            } catch (IOException e) {
                System.err.println("ServerTCP main() :: Accept failed. " + e.getMessage());
                System.exit(-1);
            }

            System.out.println(
                    "ServerTCP main() :: Connection accepted from " + client.getInetAddress() + ":" + client.getPort()
                            + "\n");

            // * Connection accepted, initialise I/O streams of the connected socket
            in = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
            out = new PrintWriter(client.getOutputStream(), true, StandardCharsets.UTF_8);

            out.println(
                    "Welcome to the server. Enter a text to be processed.");

            boolean exit = false;
            while (!exit) // Start loop of a client service
            {
                // * Receive inline text sent by the client via the input stream of the
                // connected socket
                String line = in.readLine();
                System.out.println("Client sent: " + line);

                // * Check if it is the end of connection
                if (line.compareTo("FINISH") != 0) {
                    // * Send text to the client via the outgoing stream of the connected socket
                    int step = Character.getNumericValue(line.charAt(0));
                    String res = "";

                    for (int i = 1; i < line.length(); i += step + 1)
                        res += line.charAt(i);

                    System.out.println("ServerTCP main() :: Sending to client: " + res + "\n");
                    out.println(res);

                } else { // Client wants to close connection, has sent FINISH
                    exit = true;
                }
            } // end of service
            out.println("OK");
            System.out.println("ServerTCP main() :: Connection closed.");

            // * Close flows and socket
            in.close();
            out.close();
            client.close();
        }
    }
}