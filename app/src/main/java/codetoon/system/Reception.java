package codetoon.system;
import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Reception extends Thread {
    Socket sock;
    ObjectInputStream in;
    boolean isReturnReception;

    Reception(Socket _sock, boolean _isReturnReception) {
        sock = _sock;
        isReturnReception = _isReturnReception;
        try {
            in = new ObjectInputStream(sock.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(true){
        try {

            try {
                testClassWrapper _testWrapper = (testClassWrapper) in.readObject();
                System.out.println("Recive  " + _testWrapper.memory.get(0).getName() + "    " + _testWrapper.memory.get(0).isClient());
                if (_testWrapper.valid == true) {
                    if(isReturnReception){
                        Memorys.memory =_testWrapper.memory;
                    }else{
                        Memorys.opponentMemory = _testWrapper.memory;

                    }
                    System.out.println("receivedData: " + Memorys.opponentMemory.get(0).color);
                    System.out.println(Memorys.opponentMemory.get(0).getName());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
}
