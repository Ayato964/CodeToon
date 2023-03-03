package codetoon.variable;

import java.util.HashMap;
import codetoon.system.*;
import codetoon.argument.*;
import codetoon.map.*;
import codetoon.main.*;
import org.jetbrains.annotations.NotNull;

public class MemoryVariable extends Variable<Memory> {

    Memory returnMemory;
    String strW;
    String strH;
    String strS;
    public MemoryVariable (){
    }
    @Override
    public Object newInstance() {
        return new MemoryVariable();
    }
    @Override
    public boolean setIsArray() {
        return true;
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> i) {
      //  System.out.println(i.get(0) + "!!!   " + i.get(1) + "!!!");

        strW = i.get(0);
        strH = i.get(1);
        if(i.get(2) != null)
            strS = i.get(2);
        return null;

    }
    @Override
    public Memory returnAction(Player p) {
        int a = IntegerArgument.getInstance().indentification(strW, p);
        int b = IntegerArgument.getInstance().indentification(strH, p);
        if(a >= CodeToon.RULE.memory_w || b >= CodeToon.RULE.memory_h){
            //a = 0;
            //b = 0;
            Message.addMessage("memory.error.mes");
            return null;
        }
        int num =  a + b * CodeToon.RULE.memory_h;
        if(strS != null) {
            if (strS.equals("enemy")) {
                returnMemory = Memories.opponentMemory.get(num);
            }
        }else {
            returnMemory = Memories.memory.get(num);
        }
        return returnMemory;
    }
    
}
