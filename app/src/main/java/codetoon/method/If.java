package codetoon.method;

import codetoon.argument.BooleanArgument;
import codetoon.system.CodeToon;
import codetoon.system.Player;
import codetoon.util.converter.ConvertSource;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class If extends MyMethod{
    boolean isBool;
    ArrayList<MyMethod> methods;
    String inside;
    String stringBool;

    @Override
    public Object newInstance() {
        return new If();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        stringBool = map.get(0);
        inside = map.get(CodeToon.INSIDE_METHODS);
        return null;
    }

    @Override
    public void action(Player host) {
        methods = ConvertSource.convert(inside, host);
        methods = host.removeBlackList(methods);
        isBool = BooleanArgument.getInstance().indentification(stringBool, host);
        if(isBool){
            for(int c = 0; c < methods.size(); c ++){
                methods.get(c).action(host);
            }
        }
    }
}
