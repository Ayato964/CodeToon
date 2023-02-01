package codetoon.method;

import codetoon.system.CodeToon;
import codetoon.system.Memory;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;
import org.python.compiler.Code;

import java.util.ArrayList;
import java.util.HashMap;

public class GetDownMemory extends AbstractGetMemory{
    @Override
    public Object newInstance() {
        return new GetDownMemory();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        return null;
    }


    @Override
    public Memory returnAction(ArrayList<Memory> memory, int id) {
        return memory.get(Math.min(id + 1, CodeToon.RULE.memory_w * CodeToon.RULE.memory_h - 1));
    }
}
