package codetoon.method;

import codetoon.argument.ObjectArgument;
import codetoon.system.CodeToon;
import codetoon.system.EnumMemoryStates;
import codetoon.system.Memory;
import codetoon.system.Message;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class States extends MyMethod{
    Memory memory;
    @Override
    public Object newInstance() {
        return new States();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        memory = (Memory) ObjectArgument.getInstance().indentification(map.get(CodeToon.PARCENT_ARGUMENT));
        return null;
    }

    @Override
    public void action(int i) {
        EnumMemoryStates states = memory.getStates();
        Message.addMessage(new String[]{memory.getName(), memory.getStates().name()}, "method.states.mes");
    }
}
