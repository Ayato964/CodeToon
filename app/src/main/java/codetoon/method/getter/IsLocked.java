package codetoon.method.getter;

import codetoon.argument.ObjectArgument;
import codetoon.method.MyMethod;
import codetoon.system.CodeToon;
import codetoon.system.Memory;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class IsLocked extends MyMethod<Boolean> {
    String strMemory;
    @Override
    public Boolean returnAction(Player host) {
        Memory memory = (Memory) ObjectArgument.getInstance().indentification(strMemory, host);
        //System.out.println(memory.getName() + "<----" + memory.pass);
        if(memory != null)
            return memory.pass != 0;
        else
            return false;
    }

    @Override
    public Object newInstance() {
        return new IsLocked();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        strMemory = map.get(CodeToon.PARCENT_ARGUMENT);

        return null;
    }
}
