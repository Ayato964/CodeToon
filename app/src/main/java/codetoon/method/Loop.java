package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.system.CodeToon;
import codetoon.system.Memory;
import codetoon.system.Message;
import codetoon.system.Player;
import codetoon.util.Indentification;
import codetoon.util.converter.ConvertSource;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class Loop extends MyMethod{
    String methods;
    StringBuilder program = new StringBuilder();
    String count;
    @Override
    public Object newInstance() {
        return new Loop();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        count = map.get(0);
        methods = map.get(CodeToon.INSIDE_METHODS);
        program.append(map.get(CodeToon.INSIDE_METHODS));


        return "for";
    }

    @Override
    public void action(Player host) {
        int count = IntegerArgument.getInstance().indentification(this.count);
        ArrayList<MyMethod> methods = ConvertSource.convert(this.methods, host);

        Message.popMessage(methods);
        for(int l = 0; l < count ; l ++) {
            methods = ConvertSource.convert(this.methods, host);
            methods = host.removeBlackList(methods);
            if(!methods.isEmpty()) {
                for (int c = 0; c < methods.size(); c++) {
                    methods.get(c).action(host);
                }
            }
            if(count - 2 == l)
                Message.pushMessage();
        }
    }
}
