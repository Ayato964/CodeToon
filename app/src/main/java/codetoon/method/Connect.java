package codetoon.method;

import java.util.HashMap;
import codetoon.system.*;
import codetoon.map.*;
import codetoon.main.*;
import codetoon.argument.*;

public class Connect extends MyMethod {
    Memory memory;
    @Override
    public Object newInstance() {
        return new Connect();
    }

    @Override
    public String set(HashMap<Integer, String> map) {
        memory = (Memory)ObjectArgument.getInstance().indentification(map.get(0));
        return null;
    }

    @Override
    public void action(int i) {
        PazzleStage p = (PazzleStage) Main.getInstance().getMap();      
        p.getConsole().setHost(memory);
        p.getConsole().panel.setProgram(memory.getSource() != null ? memory.getSource() : new StringBuilder());
    }

    
}
