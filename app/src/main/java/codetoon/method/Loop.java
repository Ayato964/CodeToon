package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.system.CodeToon;
import codetoon.system.Player;
import codetoon.util.Indentification;
import codetoon.util.converter.ConvertSource;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class Loop extends MyMethod{
    Player host;
    ArrayList<MyMethod> methods = new ArrayList<>();
    StringBuilder program = new StringBuilder();
    int count;
    @Override
    public Object newInstance() {
        return new Loop();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        count = IntegerArgument.getInstance().indentification(map.get(0));
        host = (Player) ObjectArgument.getInstance().indentification(map.get(CodeToon.HOST_MAP));
        methods = ConvertSource.convert(map.get(CodeToon.INSIDE_METHODS), host);
        program.append(map.get(CodeToon.INSIDE_METHODS));

        return "for";
    }

    @Override
    public void action(int i) {
        methods = host.removeBlackList(methods);
        for(int l = 0; l < count ; l ++) {
            if(!methods.isEmpty()) {
                for (int c = 0; c < methods.size(); c++) {
                    methods.get(c).action(c);

                }
            }
        }
    }
}
