package sj;
import java.io.*;
import java.net.*;
import java.util.*;

public class GameServer {

    static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(5000);

        System.out.println("Server Started");

        while(true) {

            Socket socket = server.accept();

            ClientHandler client = new ClientHandler(socket);

            clients.add(client);

            new Thread(client).start();
        }
    }

    static class ClientHandler implements Runnable {

        Socket socket;
        BufferedReader in;
        PrintWriter out;

        public ClientHandler(Socket socket) throws Exception {

            this.socket = socket;

            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);
        }

        public void run() {

            try {

                String msg;

                while((msg = in.readLine()) != null) {

                    broadcast(msg);
                }

            } catch(Exception e) {}
        }

        void broadcast(String msg) {

            for(ClientHandler c : clients) {

                c.out.println(msg);
            }
        }
    }
}