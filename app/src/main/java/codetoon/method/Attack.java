package codetoon.method;

import java.awt.*;
import java.util.HashMap;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.server.Server;
import codetoon.system.*;
import org.jetbrains.annotations.NotNull;

public class Attack extends MyMethod{
    String enemyString;
    String hostString;
    private String passString = "0";
    @Override
    public void action(int i) {
        int pass = 0;
        Player host =(Player) ObjectArgument.getInstance().indentification(hostString);
        Memory enemy = (Memory) ObjectArgument.getInstance().indentification(enemyString, host);
        if(passString != null){
            pass = IntegerArgument.getInstance().indentification(passString, host);
        }
        Message.addMessage(new String[]{enemy.getName()},"method.attack.mes", Color.black);
        if(enemy instanceof Memory){
            ((Memory) enemy).hacking(pass, host.getSerialID());
        }
    }
    @Override
    public int getCount() {
        return 1;
    }
    @Override
    public String set(@NotNull HashMap<Integer, String> map)
    {   passString = map.get(1);
        hostString = map.get(CodeToon.HOST_MAP);
        enemyString = map.get(0);

        return null;
    }
    @Override
    public Object newInstance() {
        return new Attack();

    }
}
