package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.system.Message;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;

public class Calc extends  MyMethod{
    int data1, data2;
    @Override
    public Object newInstance() {
        return new Calc();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        data1 = IntegerArgument.getInstance().indentification(map.get(0));
        data2 = IntegerArgument.getInstance().indentification(map.get(1));
        return null;
    }

    @Override
    public void action(int i) {
        String s1 = new StringBuilder().append(data1).toString();
        String s2 = new StringBuilder().append(data2).toString();
        String s3 = new StringBuilder().append(data1 + data2).toString();
        Message.addMessage(new String[]{s1, s2, s3}, "method.calc.mes");
    }
}
