package codetoon.method;

import codetoon.argument.ObjectArgument;
import codetoon.system.CodeToon;
import codetoon.system.Memory;
import codetoon.system.Player;
import codetoon.system.SaveMemory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Run extends MyMethod<Object>{
    String memory;
    @Override
    public Object newInstance() {
        return new Run();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        memory = map.get(CodeToon.PARCENT_ARGUMENT);
        return null;
    }

    @Override
    public void action(Player host) {
        Memory m = (Memory) ObjectArgument.getInstance().indentification(memory, host);
        if(m instanceof SaveMemory){
            System.out.println("SSSSSSSSS");
            m.run();
        }
    }
}
