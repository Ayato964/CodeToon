package codetoon.method;

import java.util.HashMap;

import codetoon.argument.IntegerArgument;
import codetoon.server.Server;
import codetoon.system.*;

public class Attack extends MyMethod{
    int x, y;
    @Override
    public void action(int i) {
        System.out.println("attack");
        Memorys.opponentMemory.get(y * CodeToon.MEMORY_SIZE + x).changeColor();
        Server.server.sendOpponentCopy();
    }
    @Override
    public int getCount() {
        return 1;
    }
    @Override
    public String set(HashMap<Integer, String> map)
    {
        x = IntegerArgument.getInstance().indentification(map.get(0));
        y = IntegerArgument.getInstance().indentification(map.get(1));

        return null;
    }
    @Override
    public Object newInstance() {
        return new Attack();
    }
}
