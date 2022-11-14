package codetoon.method;

import codetoon.argument.Argument;
import codetoon.argument.IntegerArgument;
import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.system.CodeToon;
import codetoon.system.Console;
import codetoon.util.Indentification;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class For extends MyMethod{
    ArrayList<MyMethod> methods = new ArrayList<>();
    StringBuilder program = new StringBuilder();
    int count;
    @Override
    public Object newInstance() {
        return new For();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        count = IntegerArgument.getInstance().indentification(map.get(0));
        methods = Indentification.indentification(map.get(CodeToon.INSIDE_METHODS));
        program.append(map.get(CodeToon.INSIDE_METHODS));
        System.out.println("set::" +count);
        return "for";
    }

    @Override
    public void action(int i) {
        System.out.println("action::" + count);
        PazzleStage stage = (PazzleStage) Main.getInstance().getMap();
        Console c = stage.getConsole();
        c.getHost().endMethod(c, methods, program);
        for(int l = 0; l < count - 1; l ++) {
            System.out.println(l);
            c.getHost().run();
        }
    }
}
