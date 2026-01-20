import java.io.*;
import java.net.*;

public class ChatHandler {
    private Socket client1;
    private Socket client2;

    public ChatHandler(Socket c1, Socket c2) {
        this.client1 = c1;
        this.client2 = c2;
    }

    public void startChat() {
        try {
            BufferedReader in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
            PrintWriter out1 = new PrintWriter(client1.getOutputStream(), true);

            BufferedReader in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
            PrintWriter out2 = new PrintWriter(client2.getOutputStream(), true);

            // Welcome messages
            out1.println("Connected! You are Client 1. Start chatting...");
            out2.println("Connected! You are Client 2. Start chatting...");

            // Thread for Client 1 -> Client 2
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in1.readLine()) != null) {
                        out2.println("Client 1: " + msg);
                    }
                } catch (IOException e) {
                    System.out.println("Client 1 disconnected.");
                }
            }).start();

            // Thread for Client 2 -> Client 1
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in2.readLine()) != null) {
                        out1.println("Client 2: " + msg);
                    }
                } catch (IOException e) {
                    System.out.println("Client 2 disconnected.");
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
