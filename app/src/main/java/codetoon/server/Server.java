package codetoon.server;
import codetoon.system.CodeToon;
import codetoon.system.Memories;
import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.system.Rule;

import java.io.IOException;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server implements Runnable {
    public static boolean isHost;
    public static boolean IS_SEND = false;
    int myPORT = 50000;
    int opponentPORT = 60000;
    String ipAdress;
    Rule rule;

    public boolean runServer = false;
    public boolean startServer = false;

    Socket sock;
    Socket returnSock;
    ServerSocket svSock;
    ServerSocket svReturnSock;

    ObjectOutputStream myOutStream;
    ObjectOutputStream opponentOutStream;

    Thread opponentReception;
    Thread returnReception;

    public static Server server = new Server();


    public void startServer(String _ipAdress, Rule rule){
        ipAdress = _ipAdress;
        this.rule = rule;
        startServer = true;
        if(isHost)
            IS_SEND = true;


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
            CodeToon.RULE = rule;
            setUpServer();
        }else{
            System.out.println("My Server Property is Not Host");
            System.out.println("Adress : " + ipAdress);
            connect(ipAdress);
        }
        if(runServer){
            get_reception();
            try {
                if(isHost) {
                    Main.getInstance().run(new PazzleStage(rule));
                    sendMyCopy();
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMyCopy() {

        if(runServer && !CodeToon.DEBUG) {
            try {
                testClassWrapper testWrapper = new testClassWrapper(Memories.memory == null ? new ArrayList<>() : Memories.memory, rule);
                myOutStream.reset();
                System.out.println("SendCopy!!");
                myOutStream.writeObject(testWrapper);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendOpponentCopy() {
        if(runServer && !CodeToon.DEBUG) {
            try {
                testClassWrapper testWrapper = new testClassWrapper(Memories.opponentMemory, rule);
                opponentOutStream.reset();
//                System.out.println("Send Enemy Copy:" + testWrapper.memory.get(0).getName() + "    "  + testWrapper.memory.get(0).isClient());
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

    public void stopConnection(){
        runServer = false;
        startServer = false;
        try {
            svSock.close();
            svReturnSock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}