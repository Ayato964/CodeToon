package codetoon.method;

import codetoon.argument.ObjectArgument;
import codetoon.system.*;
import jdk.jfr.Description;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;

@Deprecated(since = "1.6.2")
public class States extends MyMethod{
    String percent;
    @Override
    public Object newInstance() {
        return new States();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        percent = map.get(CodeToon.PARCENT_ARGUMENT);
        return null;
    }

    @Override
    public void action(Player host) {
       Memory memory = (Memory) ObjectArgument.getInstance().indentification(percent, host);
        Message.addMessage(new String[]{memory.getName(), memory.getStates().name()}, "method.states.mes");
    }
}
