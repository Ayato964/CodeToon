package codetoon.method.getter;

import codetoon.argument.ObjectArgument;
import codetoon.method.MyMethod;
import codetoon.system.CodeToon;
import codetoon.system.EnumMemoryStates;
import codetoon.system.Memory;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class IsHashMemory extends MyMethod<Boolean> {
    String m;
    @Override
    public Object newInstance() {
        return new IsHashMemory();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        m = map.get(CodeToon.PARCENT_ARGUMENT);
        return null;
    }

    @Override
    public Boolean returnAction(Player host) {
        Memory memory = (Memory) ObjectArgument.getInstance().indentification(m, host);
        if(memory != null)
            return memory.getStates() == EnumMemoryStates.HASHMODE;
        else
            return false;
    }
}
