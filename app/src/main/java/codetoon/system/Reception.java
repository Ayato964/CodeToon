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
        try {

            try {
                testClassWrapper _testWrapper = (testClassWrapper) in.readObject();
                if (_testWrapper.valid == true) {
                    if(isReturnReception){
                        _testWrapper.cangeTrueCliant();
                        Memorys.memory =_testWrapper.memory;
                    }else{
                        _testWrapper.cangeFalseCliant();
                        Memorys.opponentMemory = _testWrapper.memory;

                    }
                    System.out.println("receivedData: " + Memorys.memory.get(0).color);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
