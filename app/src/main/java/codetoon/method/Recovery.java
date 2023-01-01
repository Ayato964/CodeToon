package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.system.AbstractLockerPlayer;
import codetoon.system.Memory;
import codetoon.system.Message;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Recovery extends MyMethod{
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
    public void action(int i) {
        Memory memory = (Memory) ObjectArgument.getInstance().indentification(Stringmemory);
        int pass =  stringPass != null ?  IntegerArgument.getInstance().indentification(stringPass) : 0;

        memory.recovering(pass);
    }
}
