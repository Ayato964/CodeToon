package codetoon.method;

import java.util.HashMap;
import codetoon.argument.*;
import codetoon.system.*;

public class Attack extends MyMethod{
    @Override
    public void action(int i) {
        System.out.println("attack");
        HostServer.server.updateOpponentTest();
        GuestServer.server.updateOpponentTest();
        Memorys.opponentMemory.get(0).changeColor();
        
    }
    @Override
    public int getCount() {
        return 1;
    }
    @Override
    public String set(HashMap<Integer, String> map) {
        return null;
    }
    @Override
    public Object newInstance() {
        return new Attack();
    }
}
