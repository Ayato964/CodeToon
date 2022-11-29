package codetoon.method;

import java.util.HashMap;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.server.Server;
import codetoon.system.*;
import org.jetbrains.annotations.NotNull;

public class Attack extends MyMethod{
    Player enemy;
    @Override
    public void action(int i) {
        System.out.println("attack");
        //Memorys.opponentMemory.get(y * CodeToon.MEMORY_SIZE + x).changeColor();
        if(enemy instanceof Memory){
            ((Memory) enemy).changeColor();
        }
        Server.server.sendOpponentCopy();
    }
    @Override
    public int getCount() {
        return 1;
    }
    @Override
    public String set(@NotNull HashMap<Integer, String> map)
    {
        enemy = (Memory) ObjectArgument.getInstance().indentification(map.get(0));

        return null;
    }
    @Override
    public Object newInstance() {
        return new Attack();
    }
}
