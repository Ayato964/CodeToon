package codetoon.method.getter;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.method.MyMethod;
import codetoon.system.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class GetBeforePass extends MyMethod<Integer> {
    String pMemory;
    String pass;

    @Override
    public Integer returnAction(Player host) {
        Memory m = (Memory) ObjectArgument.getInstance().indentification(pMemory, host);
        int p = IntegerArgument.getInstance().indentification(pass, host);
        if(m.pass == p && m.getSerialID() == Admin.getInstance().getSerialID())
            return m.getBeforePass();
        else {
            Message.addMessage("memory.connection.mes4");
            return -1;
        }
    }

    @Override
    public Object newInstance() {
        return new GetBeforePass();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        pass = map.get(0);
        pMemory = map.get(CodeToon.PARCENT_ARGUMENT);
        return null;
    }
}
