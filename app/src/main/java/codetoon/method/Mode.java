package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.system.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Mode extends MyMethod<Object>{
    String memoryStr;
    String enumStr;
    String pass = "0";

    @Override
    public Object newInstance() {
        return new Mode();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        memoryStr = map.get(CodeToon.PARCENT_ARGUMENT);
        enumStr = map.get(0);
        if(map.get(1) != null)
            pass = map.get(1);

        return null;
    }

    @Override
    public void action(Player host) {
        Memory memory = (Memory) ObjectArgument.getInstance().indentification(memoryStr, host);
        if(memory.pass == IntegerArgument.getInstance().indentification(pass, host))
        {
            if (enumStr.equals("SAVE")) {
                memory.running = false;
                memory.removeAnimation();
                Memories.memory.remove(memory.getIdC() * memory.getIdI());
                Memories.memory.add(memory.getIdC() * memory.getIdI(), new SaveMemory(memory.getInfo()));
            }
            if (enumStr.equals("NORMAL")) {
                memory.running = true;
                memory.removeAnimation();
                Memories.memory.remove(memory.getIdC() * memory.getIdI());
                Memories.memory.add(memory.getIdC() * memory.getIdI(), new Memory(memory.getInfo()));
                Memories.runThread(Memories.memory.get(memory.getIdC() * memory.getIdI()));
            }
        }else{
            Message.addMessage(new String[]{memory.getName()}, "memory.pass.mes2");
        }
    }
}
