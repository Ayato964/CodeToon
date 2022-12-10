package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.server.Server;
import codetoon.system.AbstractLockerPlayer;
import codetoon.system.CodeToon;
import codetoon.system.Message;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;

public class Lock extends MyMethod{
    private AbstractLockerPlayer parcent = null;
    private int pass = 0;
    private int parcent_pass = 0;
    @Override
    public Object newInstance() {
        return new Lock();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        Object t = ObjectArgument.getInstance().indentification(map.get(CodeToon.PARCENT_ARGUMENT));
        if(t instanceof Player){
            parcent = (AbstractLockerPlayer) t;
            if(map.get(1) != null){
                parcent_pass = IntegerArgument.getInstance().indentification(map.get(0));
                pass = IntegerArgument.getInstance().indentification(map.get(1));
            }else{
                pass = IntegerArgument.getInstance().indentification(map.get(0));
            }
        }
        return null;
    }
    @Override
    public void action(int i) {
        if(parcent != null){
            parcent.setPassWord(pass, parcent_pass);

        }
    }
}
