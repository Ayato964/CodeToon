package codetoon.server;
import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.system.Admin;
import codetoon.system.CodeToon;
import codetoon.system.Memories;

import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;

public class Reception extends Thread {
    Socket sock;
    ObjectInputStream in;
    boolean isReturnReception;

    Reception(Socket _sock, boolean _isReturnReception) {
        sock = _sock;

        System.out.println("Hello");
        isReturnReception = _isReturnReception;
        try {
            in = new ObjectInputStream(sock.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(Server.server.runServer){
        try {

            try {
                testClassWrapper _testWrapper = (testClassWrapper) in.readObject();
                //System.out.println("Recive  " + _testWrapper.memory.get(0).getName() + "    " + _testWrapper.memory.get(0).isClient());
                    if(isReturnReception){
                        Memories.equalsMemory(_testWrapper.memory);
                    }else{
                        if(!Server.isHost && CodeToon.RULE == null) {
                            CodeToon.RULE = _testWrapper.rule;
                            Memories.equalsOpponentMemory(_testWrapper.memory);
                            Main.getInstance().run(new PazzleStage(CodeToon.RULE));
                            Server.server.sendMyCopy();
                        }else {
                            if (!_testWrapper.memory.isEmpty()) {
                                Memories.equalsOpponentMemory(_testWrapper.memory);
                            }
                        }

                       // System.out.println("Repaired!!" + Memories.opponentMemory.get(0).showPass());

                    }
                    //System.out.println("receivedData: " + Memories.memory.get(0).serialID + "    " + Admin.getInstance().getSerialID());
            } catch (ClassNotFoundException e) {
          //      e.printStackTrace();
            }

            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
}
