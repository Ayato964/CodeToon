package codetoon.method;

import codetoon.system.CodeToon;
import codetoon.system.Memory;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class GetUpMemory extends AbstractGetMemory{
    @Override
    public Object newInstance() {
        return new GetUpMemory();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        return null;
    }

    @Override
    public Memory returnAction(Player host) {
        Memory m = (Memory) host;
        int id = m.getIdI() * CodeToon.RULE.memory_w + m.getIdC();
        return getMyMemories(m).get(id -1);

    }
}
