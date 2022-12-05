package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.system.Message;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;

public class PrintInt extends MyMethod{
    int num;
    @Override
    public Object newInstance() {
        return new PrintInt();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        num = IntegerArgument.getInstance().indentification(map.get(0));
        return null;
    }

    @Override
    public void action(int i) {
        Message.addMessage("" + num, Color.BLACK);
    }
}
