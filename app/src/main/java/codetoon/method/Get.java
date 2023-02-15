package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.system.CodeToon;
import codetoon.system.HashMemory;
import codetoon.system.Memory;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Get extends MyMethod<Object>{
    String strMemory;
    String a;
    String pass = "0";
    @Override
    public Object newInstance() {
        return new Get();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        a = map.get(0);
        strMemory = map.get(CodeToon.PARCENT_ARGUMENT);
        if(map.get(1) != null)
            pass = map.get(1);
        return null;
    }

    @Override
    public Object returnAction(Player host) {
        Memory m = (Memory) ObjectArgument.getInstance().indentification(strMemory, host);
        int p = IntegerArgument.getInstance().indentification(pass, host);
        if(m instanceof HashMemory<?,?>)
            return ((HashMemory) m).get(a, p, host);
        return null;
    }
}
