package codetoon.method;

import codetoon.argument.ObjectArgument;
import codetoon.system.*;
import org.jetbrains.annotations.NotNull;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.HashMap;

public class States extends MyMethod{
    @Override
    public Object newInstance() {
        return new States();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        return null;
    }

    @Override
    public void action(Player host) {
       Memory memory = (Memory) host;
        Message.addMessage(new String[]{memory.getName(), memory.getStates().name()}, "method.states.mes");
    }
}
