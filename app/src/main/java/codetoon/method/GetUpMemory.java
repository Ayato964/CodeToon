package codetoon.method;

import codetoon.system.CodeToon;
import codetoon.system.Memory;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
    public Memory returnAction(ArrayList<Memory> memory, int id) {
        return memory.get(Math.max(id - 1, 0));
    }
}