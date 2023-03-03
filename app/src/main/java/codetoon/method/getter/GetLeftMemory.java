package codetoon.method.getter;

import codetoon.system.CodeToon;
import codetoon.system.Memory;

import java.util.ArrayList;

public class GetLeftMemory extends AbstractGetMemory {
    @Override
    public Memory returnAction(ArrayList<Memory> memory, int id) {
        return memory.get(Math.max(id - CodeToon.RULE.memory_h, 0));
    }

    @Override
    public Object newInstance() {
        return new GetLeftMemory();
    }
}
