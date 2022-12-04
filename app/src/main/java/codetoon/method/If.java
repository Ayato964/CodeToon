package codetoon.method;

import codetoon.argument.BooleanArgumet;
import codetoon.argument.ObjectArgument;
import codetoon.system.CodeToon;
import codetoon.system.Player;
import codetoon.util.Indentification;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class If extends MyMethod{
    boolean isBool;
    Player host;
    ArrayList<MyMethod> methods;
    @Override
    public Object newInstance() {
        return new If();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        isBool = BooleanArgumet.getInstance().indentification(map.get(0));
        host = (Player) ObjectArgument.getInstance().indentification(map.get(CodeToon.HOST_MAP));
        methods = Indentification.indentification(map.get(CodeToon.INSIDE_METHODS), host);

        return null;
    }

    @Override
    public void action(int i) {
        methods = host.removeBlackList(methods);
        if(isBool){
            for(int c = 0; c < methods.size(); c ++){
                methods.get(c).action(c);
            }
        }
    }
}
