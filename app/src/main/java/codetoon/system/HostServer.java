package codetoon.system;
import java.io.IOException;
import java.net.ServerSocket;
import java.io.ObjectOutputStream;

public class HostServer extends Server{

    public static HostServer server = new HostServer();

    ServerSocket svSock;
    ServerSocket svReturnSock;

    public HostServer(){
        super();
    }

    public void setUpServer() {
        runServer = true;
        try {
            svSock = new ServerSocket(myPORT);
            svReturnSock = new ServerSocket(opponentPORT);
            
            sock = svSock.accept();
            returnSock = svReturnSock.accept();

            myOutStream = new ObjectOutputStream(sock.getOutputStream());
            opponentOutStream = new ObjectOutputStream(returnSock.getOutputStream());

            opponentReception = new Reception(sock, false);
            returnReception = new Reception(returnSock, true);
            
            System.out.println("connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(this);
        thread.start();
    }

    public void end() {
        runServer = false;
        try {
            sock.close();
            svSock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}