package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.system.CodeToon;
import codetoon.system.HashMemory;
import codetoon.system.Memory;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;
import org.python.modules._hashlib;

import java.util.HashMap;

public class Put extends MyMethod<Object>{
    String strMemory;
    String a, b;
    String pass = "0";
    @Override
    public Object newInstance() {
        return new Put();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        strMemory = map.get(CodeToon.PARCENT_ARGUMENT);
        a = map.get(0);
        b = map.get(1);
        if(map.get(2) != null)
            pass = map.get(2);
        return null;
    }

    @Override
    public void action(Player host) {
        Memory m = (Memory) ObjectArgument.getInstance().indentification(strMemory, host);
        int p = IntegerArgument.getInstance().indentification(pass, host);
        if(m instanceof HashMemory<?,?>){
            HashMemory memory = (HashMemory) m;
            memory.set(changeToArgument(host, memory.t, a), changeToArgument(host, memory.v, b), p);
        }
    }
}
