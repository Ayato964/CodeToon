package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.system.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Call extends MyMethod<Object>{
    String percent;
    String pass = "0";
    @Override
    public Object newInstance() {
        return new Call();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        percent = map.get(CodeToon.PARCENT_ARGUMENT);
        if(map.get(0) != null)
          pass = map.get(0);
        return null;
    }

    @Override
    public void action(Player host) {
        Memory m = (Memory) ObjectArgument.getInstance().indentification(percent, host);
        if(m.getSerialID() != Admin.getInstance().getSerialID()){
            Message.addMessage("savememory.call.error");
        }else if(m instanceof SaveMemory){
            if(m.getSource() != null && m.pass == IntegerArgument.getInstance().indentification(pass, host)) {
                StringBuilder s = m.getSource();
                PazzleStage p = (PazzleStage) Main.getInstance().getMap();
                Console c = p.getConsole();
                c.panel.setProgram(s, 0);
            }else{
                Message.addMessage("memory.connection.mes4");
            }
        }
    }
}
