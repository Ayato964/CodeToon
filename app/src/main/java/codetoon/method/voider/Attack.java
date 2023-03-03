package codetoon.method.voider;

import java.awt.*;
import java.util.HashMap;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.method.MyMethod;
import codetoon.system.*;
import org.jetbrains.annotations.NotNull;

public class Attack extends MyMethod {
    String enemyString;
    String hostString;
    private String passString = "0";
    @Override
    public void action(Player host) {
        int pass;
        Memory enemy = (Memory) ObjectArgument.getInstance().indentification(enemyString, host);
        pass = IntegerArgument.getInstance().indentification(passString, host);
        if(enemy != null) {
            Message.addMessage(new String[]{enemy.getName()}, "method.attack.mes", Color.black);
            enemy.hacking(pass, host.getSerialID());
        }
    }
    @Override
    public int getCount() {
        return 1;
    }
    @Override
    public String set(@NotNull HashMap<Integer, String> map)
    {
        if(map.get(1) != null)
            passString = map.get(1);
        hostString = map.get(CodeToon.HOST_MAP);
        enemyString = map.get(0);

        return null;
    }
    @Override
    public Object newInstance() {
        return new Attack();

    }
}
