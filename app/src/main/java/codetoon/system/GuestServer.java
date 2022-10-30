package codetoon.system;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GuestServer extends Server {
    public static GuestServer server = new GuestServer();
    public GuestServer() {
        super();
    }

    public void connect(String ipAdress) {
        boolean connect = false;
        runServer = true;
        while (connect == false) {
            try {
                sock = new Socket(ipAdress, myPORT);
                returnSock = new Socket(ipAdress, opponentPORT);

                myOutStream = new ObjectOutputStream(sock.getOutputStream());
                opponentOutStream = new ObjectOutputStream(returnSock.getOutputStream());

                opponentReception = new Reception(sock, false);
                returnReception = new Reception(returnSock, true);
                System.out.println("connected");
                connect = true;
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e2) {
                    e.printStackTrace();
                }
            }
        }
        Thread thread = new Thread(this);
        thread.start();

    }

    public void end() {
        runServer = false;
        try {
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}