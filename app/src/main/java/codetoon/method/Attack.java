package codetoon.method;

import java.awt.*;
import java.util.HashMap;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.server.Server;
import codetoon.system.*;
import org.jetbrains.annotations.NotNull;

public class Attack extends MyMethod{
    Player enemy;
    private int pass = 0;
    @Override
    public void action(int i) {
        //Memorys.opponentMemory.get(y * CodeToon.MEMORY_SIZE + x).changeColor();
        Message.addMessage(enemy.getName() + "に攻撃を仕掛けました", Color.black);
        if(enemy instanceof Memory){
            ((Memory) enemy).hacking(pass);
        }
    }
    @Override
    public int getCount() {
        return 1;
    }
    @Override
    public String set(@NotNull HashMap<Integer, String> map)
    {
        enemy = (Memory) ObjectArgument.getInstance().indentification(map.get(0));
        if(map.get(1) != null){
            pass = IntegerArgument.getInstance().indentification(map.get(1));
        }

        return null;
    }
    @Override
    public Object newInstance() {
        return new Attack();
    }
}
