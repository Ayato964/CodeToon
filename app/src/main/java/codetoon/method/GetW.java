package codetoon.method;

import codetoon.system.CodeToon;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class GetW extends MyMethod<Integer>{
    @Override
    public Integer returnAction(Player o) {
        return CodeToon.RULE.memory_w;
    }

    @Override
    public Object newInstance() {
        return new GetW();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        return null;
    }
}
