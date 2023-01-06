package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Random extends MyMethod<Integer>{
    private String v1;
    private String v2;
    @Override
    public Integer returnAction(Player host) {
        if(v1 != null)
            if(v2 != null)
                return new java.util.Random().nextInt(IntegerArgument.getInstance().indentification(v1, host), IntegerArgument.getInstance().indentification(v2, host));
            else
                return new java.util.Random().nextInt(IntegerArgument.getInstance().indentification(v1, host));
            else
                return 0;
    }

    @Override
    public Object newInstance() {
        return new Random();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        v1 = map.get(0);
        v2 = map.get(1);
        return null;
    }
}
