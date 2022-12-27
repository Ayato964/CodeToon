package codetoon.method;

import codetoon.argument.BooleanArgumet;
import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.argument.StringArgument;
import codetoon.system.CodeToon;
import codetoon.system.Message;
import codetoon.system.Player;
import codetoon.util.converter.ConvertSource;
import codetoon.util.converter.ConvertVariable;
import codetoon.variable.Variables;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class For extends MyMethod{
    String bool;
    String integer;
    String lamda;
    Player host;
    String inside;

    @Override
    public Object newInstance() {
        return new For();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        integer = map.get(0);
        bool = map.get(1);
        lamda = map.get(2);
        host = (Player) ObjectArgument.getInstance().indentification(map.get(CodeToon.HOST_MAP));
        inside = map.get(CodeToon.INSIDE_METHODS);
        return null;
    }

    @Override
    public void action(int i) {
        PrivateNewVariable p = (PrivateNewVariable) ConvertVariable.convert(integer, host);
        p.action(-1);
        System.out.println(Variables.VARIABLE.getThis(p.id).action());
        ArrayList<MyMethod> methods = ConvertSource.convert(inside, host);
        //Message.popMessage(methods);
        while (BooleanArgumet.getInstance().indentification(bool)){

            methods = ConvertSource.convert(inside, host);
            if(!methods.isEmpty()){
                for(int l = 0; l < methods.size(); l ++){
                    methods.get(l).action(i);
                }
            }
            ConvertVariable.convert(lamda, host).action(-1);
        }
       // Message.pushMessage();
    }
}
