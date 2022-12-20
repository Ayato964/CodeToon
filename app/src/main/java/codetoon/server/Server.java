package codetoon.server;
import codetoon.system.CodeToon;
import codetoon.system.Memories;
import codetoon.main.Main;
import codetoon.map.PazzleStage;

import java.io.IOException;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

public class Server implements Runnable {
    public static boolean isHost;
    int myPORT = 50000;
    int opponentPORT = 60000;
    String ipAdress;

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

    public void startServer(String _ipAdress){
        ipAdress = _ipAdress;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void setUpServer() {
        runServer = true;
        isHost = true;
        try {
            svSock = new ServerSocket(myPORT);
            svReturnSock = new ServerSocket(opponentPORT);

            sock = svSock.accept();
            returnSock = svReturnSock.accept();
            System.out.println("connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect(String ipAdress) {
        boolean connect = false;
        isHost = false;
        runServer = true;
        while (connect == false) {
            try {
                sock = new Socket(ipAdress, myPORT);
                returnSock = new Socket(ipAdress, opponentPORT);
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
        

    }

    public void get_reception(){
        try {
            myOutStream = new ObjectOutputStream(sock.getOutputStream());
            opponentOutStream = new ObjectOutputStream(returnSock.getOutputStream());
            opponentReception = new Reception(sock, false);

            opponentReception.start();
            returnReception = new Reception(returnSock, true);
            returnReception.start();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Not Connet Server");
        }
        
    }

    public void run() {
        System.out.println("run server");
        if(isHost){
            System.out.println("My Server Property is HOST");
            setUpServer();
        }else{
            System.out.println("My Server Property is Not Host");
            connect(ipAdress);
        }
        get_reception();
        Main.getInstance().run(new PazzleStage(5));
        sendMyCopy();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendMyCopy() {

        if(runServer && !CodeToon.DEBUG) {
            try {
                testClassWrapper testWrapper = new testClassWrapper(Memories.memory);
                myOutStream.reset();
                System.out.println("SendCopy:" + testWrapper.memory.get(0).getName() + "    " + testWrapper.memory.get(0).showPass());
                myOutStream.writeObject(testWrapper);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendOpponentCopy() {
        if(runServer && !CodeToon.DEBUG) {
            try {
                testClassWrapper testWrapper = new testClassWrapper(Memories.opponentMemory);
                opponentOutStream.reset();
                System.out.println(testWrapper.memory.get(0).getName() + "    " + testWrapper.memory.get(0).counter);
                System.out.println("Send Enemy Copy:" + testWrapper.memory.get(0).getName() + "    " + testWrapper.memory.get(0).counter + "    " + testWrapper.memory.get(0).isClient());
                opponentOutStream.writeObject(testWrapper);

            } catch (IOException e) {
                e.printStackTrace();
            }
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