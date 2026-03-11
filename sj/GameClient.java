package sj;
import java.io.*;
import java.net.*;

public class GameClient {

    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public GameClient(String host) throws Exception {

        socket = new Socket(host,5000);

        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(() -> {

            try {

                String msg;

                while((msg = in.readLine()) != null) {

                    System.out.println(msg);
                }

            } catch(Exception e){}
        }).start();
    }

    public void send(String msg) {

        out.println(msg);
    }
}