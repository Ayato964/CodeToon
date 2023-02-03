package codetoon.method;

import codetoon.argument.ObjectArgument;
import codetoon.system.CodeToon;
import codetoon.system.EnumMemoryStates;
import codetoon.system.Memory;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class IsHacked extends MyMethod<Boolean>{
    String strMemory;
    @Override
    public Boolean returnAction(Player o) {
        Memory memory = (Memory) ObjectArgument.getInstance().indentification(strMemory, o);
        return memory.getStates() == EnumMemoryStates.HACKED;
    }

    @Override
    public Object newInstance() {
        return new IsHacked();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        strMemory = map.get(CodeToon.PARCENT_ARGUMENT);
        return null;
    }
}
