package codetoon.method.voider;

import codetoon.argument.BooleanArgument;
import codetoon.method.MyMethod;
import codetoon.method.PrivateNewVariable;
import codetoon.system.CodeToon;
import codetoon.system.Message;
import codetoon.system.Player;
import codetoon.util.converter.ConvertSource;
import codetoon.util.converter.ConvertVariable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class For extends MyMethod {
    String bool;
    String integer;
    String lamda;
    public String inside;

    @Override
    public Object newInstance() {
        return new For();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        integer = map.get(0);
        bool = map.get(1);
        lamda = map.get(2);
        inside = map.get(CodeToon.INSIDE_METHODS);
        return null;
    }

    @Override
    public void action(Player host) {
        PrivateNewVariable p = (PrivateNewVariable) ConvertVariable.convert(integer, host);
        p.action(host);
        ArrayList<MyMethod> methods = ConvertSource.convert(inside, host);
        Message.popMessage(methods);
        l1: while (BooleanArgument.getInstance().indentification(bool, host)){

            methods = ConvertSource.convert(inside, host);
            if(!methods.isEmpty()){
                l2: for(int l = 0; l < methods.size(); l ++){
                    //System.out.println("For + " + l + ":" + host.getName());
                    methods.get(l).action(host);
                    if(!host.running || host.DEAD_CHORD)
                        break l2;
                }
            }
            if(!host.running || host.DEAD_CHORD)
                break l1;
            ConvertSource.convert(new StringBuilder().append(lamda).append(";").toString(), host).get(0).action(host);


            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Message.pushMessage();
    }
}
