package codetoon.method;

import codetoon.server.Server;
import codetoon.system.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class SetHashMode extends Mode{
    String a = "Integer", b = "String";
    @Override
    public Object newInstance() {
        return new SetHashMode();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        memoryStr = map.get(CodeToon.PARCENT_ARGUMENT);
        if(map.get(0) != null &&map.get(1) != null){
            a = map.get(0);
            b = map.get(1);
            if(map.get(2) != null)
                pass = map.get(2);
        }
        return null;
    }

    @Override
    protected void change(Player host, Memory memory) {
            if (a.equals("Integer") && b.equals("String"))
                changeMemory(memory, new HashMemory<Integer, String>(memory.getInfo(), 0, "s"), false);
            if (a.equals("String") && b.equals("Integer"))
                changeMemory(memory, new HashMemory<String, Integer>(memory.getInfo(), "s", 0), false);
            if(a.equals("Memory") && b.equals("String"))
                changeMemory(memory, new HashMemory<Memory, String>(memory.getInfo(), new Memory(Memories.get(0).getInfo()), "s"), false);
            if(a.equals("String") && b.equals("Memory"))
                changeMemory(memory, new HashMemory<String, Memory>(memory.getInfo(), "s", new Memory(Memories.get(0).getInfo())), false);
            if(a.equals("Integer") && b.equals("Memory"))
                changeMemory(memory, new HashMemory<Integer, Memory>(memory.getInfo(), 0,new Memory(Memories.get(0).getInfo())), false);
            if(a.equals("Memory") && b.equals("Integer"))
                changeMemory(memory, new HashMemory<Memory, Integer>(memory.getInfo(), new Memory(Memories.get(0).getInfo()), 0), false);

    }
}
