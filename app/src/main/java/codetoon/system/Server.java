package codetoon.system;
import java.io.IOException;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

public class Server implements Runnable {
    int myPORT = 50000;
    int opponentPORT = 60000;

    boolean runServer = false;
    boolean myCopy_nextSendValid = true;
    boolean opponent_nextSendValid = true;

    Socket sock;
    Socket returnSock;
    ServerSocket svSock;
    ServerSocket svReturnSock;

    ObjectOutputStream myOutStream;
    ObjectOutputStream opponentOutStream;

    Thread opponentReception;
    Thread returnReception;

    public static Server server = new Server();

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
            opponentReception.start();
            returnReception = new Reception(returnSock, true);
            returnReception.start();
            System.out.println("connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(this);
        thread.start();
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
                opponentReception.start();
                returnReception = new Reception(returnSock, true);
                returnReception.start();
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

    public void run() {
        System.out.println("run server");
        sendMyCopy();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (runServer) {
            sendMyCopy();
            sendOpponentCopy();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void sendMyCopy() {
        try {
            testClassWrapper testWrapper = new testClassWrapper(myCopy_nextSendValid, Memorys.memory);
            if(myCopy_nextSendValid == true){
                myCopy_nextSendValid = false;
            }
            myOutStream.reset();
            testWrapper.cangeCliant(false);
            System.out.println("SendCopy:" + testWrapper.memory.get(0).getName() + "    " + testWrapper.memory.get(0).counter + "    " + testWrapper.memory.get(0).isCliant());
            myOutStream.writeObject(testWrapper);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendOpponentCopy() {
        try {
            testClassWrapper testWrapper = new testClassWrapper(opponent_nextSendValid, Memorys.opponentMemory);
            if(opponent_nextSendValid == true){
                opponent_nextSendValid = false;
            }
            opponentOutStream.reset();
            System.out.println(testWrapper.memory.get(0).getName() + "    " + testWrapper.memory.get(0).counter);
            testWrapper.cangeCliant(true);
            System.out.println("Send Enemy Copy:" + testWrapper.memory.get(0).getName() + "    " + testWrapper.memory.get(0).counter + "    " + testWrapper.memory.get(0).isCliant());
            opponentOutStream.writeObject(testWrapper);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateMyTest(){
        if(runServer){
            myCopy_nextSendValid = true;
        }
        
    }

    public void updateOpponentTest(){
        if(runServer){
            opponent_nextSendValid = true;
        }
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