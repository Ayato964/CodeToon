package codetoon.method;

import codetoon.argument.BooleanArgumet;
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
    @Override
    public Object newInstance() {
        return new If();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        isBool = BooleanArgumet.getInstance().indentification(map.get(0));
        inside = map.get(CodeToon.INSIDE_METHODS);
        return null;
    }

    @Override
    public void action(Player host) {
        methods = ConvertSource.convert(inside, host);
        methods = host.removeBlackList(methods);
        if(isBool){
            for(int c = 0; c < methods.size(); c ++){
                methods.get(c).action(host);
            }
        }
    }
}
