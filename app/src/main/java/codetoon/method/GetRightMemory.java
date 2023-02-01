package codetoon.method;

import codetoon.system.CodeToon;
import codetoon.system.Memory;
import org.python.compiler.Code;

import java.util.ArrayList;

public class GetRightMemory extends AbstractGetMemory{
    @Override
    public Memory returnAction(ArrayList<Memory> memory, int id) {
        return memory.get(Math.min(id + CodeToon.RULE.memory_h, CodeToon.RULE.memory_w * CodeToon.RULE.memory_h - 1));
    }

    @Override
    public Object newInstance() {
        return new GetRightMemory();
    }
}
