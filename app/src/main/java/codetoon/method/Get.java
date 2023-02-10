package codetoon.method;

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
    @Override
    public Object newInstance() {
        return new Get();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        a = map.get(0);
        strMemory = map.get(CodeToon.PARCENT_ARGUMENT);
        return null;
    }

    @Override
    public Object returnAction(Player host) {
        Memory m = (Memory) ObjectArgument.getInstance().indentification(strMemory, host);
        if(m instanceof HashMemory<?,?>)
            return ((HashMemory) m).get(a, host);
        return null;
    }
}
