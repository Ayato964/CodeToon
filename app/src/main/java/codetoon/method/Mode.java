package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.main.Main;
import codetoon.map.PazzleStage;
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
        PazzleStage p = (PazzleStage) Main.getInstance().getMap();
        if(memory.pass == IntegerArgument.getInstance().indentification(pass, host))
        {
            if (enumStr.equals("SAVE")) {
                if(memory.getStates() != EnumMemoryStates.HACKED) {
                    memory.running = false;
                    memory.removeAnimation();
                    Memories.memory.remove(p.MEMORY_W * memory.getIdI() + memory.getIdC());
                    Memories.memory.add(p.MEMORY_W * memory.getIdI() + memory.getIdC(), new SaveMemory(memory.getInfo()));
                }else{
                    Message.addMessage("mode.change.error.opponent");
                }
            }
            if (enumStr.equals("NORMAL")) {
                memory.running = true;
                memory.removeAnimation();
                Memories.memory.remove(p.MEMORY_W * memory.getIdI() + memory.getIdC());
                Memories.memory.add(p.MEMORY_W * memory.getIdI() + memory.getIdC(), new Memory(memory.getInfo()));
                Memories.runThread(Memories.memory.get(p.MEMORY_W * memory.getIdI() + memory.getIdC()));
            }
        }else{
            Message.addMessage(new String[]{memory.getName()}, "memory.pass.mes2");
        }
    }
}
