package codetoon.method;

import java.util.HashMap;

import codetoon.server.Server;
import codetoon.system.*;

public class Attack extends MyMethod{
    @Override
    public void action(int i) {
        System.out.println("attack");
        Memorys.opponentMemory.get(0).changeColor();
        Server.server.sendOpponentCopy();
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
