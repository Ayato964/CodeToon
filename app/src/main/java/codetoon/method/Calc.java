package codetoon.method;

import codetoon.argument.IntegerArgument;
import org.jetbrains.annotations.NotNull;

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
        System.out.println(data1 + "と" + data2 + "を足した数は" + (data1 + data2) + "です。");
    }
}
