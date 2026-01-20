import java.io.*;
import java.net.*;

public class Main {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        System.out.println("Server started. Waiting for two clients to connect...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            // Accept two client connections
            Socket client1 = serverSocket.accept();
            System.out.println("Client 1 connected.");
            Socket client2 = serverSocket.accept();
            System.out.println("Client 2 connected.");

            // Create a handler for communication between clients
            ChatHandler chat = new ChatHandler(client1, client2);
            chat.startChat();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
