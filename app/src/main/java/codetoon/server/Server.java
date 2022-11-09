package codetoon.server;
import codetoon.system.Memorys;

import java.io.IOException;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

public class Server implements Runnable {
    public static boolean isHost;
    int myPORT = 50000;
    int opponentPORT = 60000;

    boolean runServer = false;

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
        isHost = true;
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
        isHost = false;
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
        /* 
        while (runServer) {
            sendMyCopy();
            sendOpponentCopy();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        */
    }

    public void sendMyCopy() {
        try {
            testClassWrapper testWrapper = new testClassWrapper(Memorys.memory);
            myOutStream.reset();
            System.out.println("SendCopy:" + testWrapper.memory.get(0).getName() + "    " + testWrapper.memory.get(0).counter + "    " + testWrapper.memory.get(0).isClient());
            myOutStream.writeObject(testWrapper);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendOpponentCopy() {
        try {
            testClassWrapper testWrapper = new testClassWrapper(Memorys.opponentMemory);
            opponentOutStream.reset();
            System.out.println(testWrapper.memory.get(0).getName() + "    " + testWrapper.memory.get(0).counter);
            System.out.println("Send Enemy Copy:" + testWrapper.memory.get(0).getName() + "    " + testWrapper.memory.get(0).counter + "    " + testWrapper.memory.get(0).isClient());
            opponentOutStream.writeObject(testWrapper);

        } catch (IOException e) {
            e.printStackTrace();
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