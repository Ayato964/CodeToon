package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.system.AbstractLockerPlayer;
import codetoon.system.Memory;
import codetoon.system.Message;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Recovery extends MyMethod{
    private Memory memory;
    private int pass;
    @Override
    public Object newInstance() {
        return new Recovery();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        memory = (Memory) ObjectArgument.getInstance().indentification(map.get(0));

        pass =map.get(1) != null ?  IntegerArgument.getInstance().indentification(map.get(1)) : 0;
        return null;
    }

    @Override
    public void action(int i) {
        memory.recovering(pass);
        Message.addMessage("制圧データを取り戻しました。");
    }
}
