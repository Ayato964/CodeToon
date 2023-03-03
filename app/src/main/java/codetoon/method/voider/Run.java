package codetoon.method.voider;

import codetoon.argument.ObjectArgument;
import codetoon.method.MyMethod;
import codetoon.system.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Run extends MyMethod<Object> {
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
            SaveMemory mm = (SaveMemory) m;
            mm.isClickedRun = true;
            m.run();
        }
    }
}
