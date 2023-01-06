package codetoon.method;

import codetoon.system.CodeToon;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class GetH extends MyMethod<Integer>{
    @Override
    public Object newInstance() {
        return new GetH();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        return null;
    }

    @Override
    public Integer returnAction(Player host) {
        return CodeToon.MEMORY_SIZE;
    }
}
