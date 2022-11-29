package codetoon.method;

import codetoon.argument.BooleanArgumet;
import codetoon.argument.IntegerArgument;
import codetoon.system.Message;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class PrintBool extends MyMethod{
    boolean b;
    @Override
    public Object newInstance() {
        return new PrintBool();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        b = BooleanArgumet.getInstance().indentification(map.get(0));
        return null;
    }

    @Override
    public void action(int i) {
        System.out.println(b);
        Message.addMessage(String.valueOf(b));
    }
}
