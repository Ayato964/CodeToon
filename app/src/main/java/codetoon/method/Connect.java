package codetoon.method;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import codetoon.system.*;
import codetoon.map.*;
import codetoon.main.*;
import codetoon.argument.*;

public class Connect extends MyMethod  {
    Memory memory;
    int pass = 0;
    @Override
    public Object newInstance() {
        return new Connect();
    }

    @Override
    public String set(HashMap<Integer, String> map) {
        memory = (Memory)ObjectArgument.getInstance().indentification(map.get(CodeToon.PARCENT_ARGUMENT));
        if(map.get(0) != null){
            pass = IntegerArgument.getInstance().indentification(map.get(0)).intValue();
        }
        return null;
    }

    @Override
    public void action(int i) {
        memory.connection(pass);
        Message.addMessage(memory.getName() + "にアクセスしました。", Color.BLACK);
    }

    
}
