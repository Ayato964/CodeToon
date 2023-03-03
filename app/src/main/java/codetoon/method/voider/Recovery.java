package codetoon.method.voider;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.method.MyMethod;
import codetoon.system.Memory;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Recovery extends MyMethod {
    private String Stringmemory;
    private String stringPass;
    @Override
    public Object newInstance() {
        return new Recovery();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        Stringmemory = map.get(0);
        stringPass = map.get(1);
        return null;
    }

    @Override
    public void action(Player host) {
        Memory memory = (Memory) ObjectArgument.getInstance().indentification(Stringmemory, host);
        int pass =  stringPass != null ?  IntegerArgument.getInstance().indentification(stringPass, host) : 0;
        if(memory != null)
            memory.recovering(pass);
    }
}
