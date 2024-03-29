package codetoon.method.voider;

import java.util.HashMap;

import codetoon.method.MyMethod;
import codetoon.system.*;
import codetoon.argument.*;

public class Connect extends MyMethod {
    String memoryString;
    String strPass = "0";
    int pass = 0;
    @Override
    public Object newInstance() {
        return new Connect();
    }

    @Override
    public String set(HashMap<Integer, String> map) {
        System.out.println(map.get(CodeToon.PARCENT_ARGUMENT));
        memoryString = map.get(CodeToon.PARCENT_ARGUMENT);
        if(map.get(0) != null){
            strPass = map.get(0);
        }
        return null;
    }

    @Override
    public void action(Player host) {
        Memory memory = (Memory)ObjectArgument.getInstance().indentification(memoryString, host);
        pass = IntegerArgument.getInstance().indentification(strPass, host);
        if(memory != null) {
            memory.connection(pass);
        }
    }

    
}
