package codetoon.variable;

import java.util.ArrayList;
import java.util.HashMap;
import codetoon.system.*;
import codetoon.argument.*;
import codetoon.map.*;
import codetoon.main.*;

public class MemoryVariable extends Variable<Memory> {

    ArrayList<Memory> memory;
    Memory returnMemory;
    public MemoryVariable (ArrayList<Memory> m){
        memory = new ArrayList<>();
        memory = m;
    }
    @Override
    public Object newInstance() {
        return new MemoryVariable(memory);
    }
    @Override
    public boolean setIsArray() {
        return true;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 2;
    }
    
    @Override
    public String set(HashMap<Integer, String> i) {
      //  System.out.println(i.get(0) + "!!!   " + i.get(1) + "!!!");
        int size = ((PazzleStage) Main.getInstance().getMap()).MEMORY_SIZE;
        int num = new IntegerArgument().indentification(i.get(0)) + 
                    new IntegerArgument().indentification(i.get(1)) * size;
        returnMemory = memory.get(num); 
        return null;

    }
    @Override
    public Memory action() {

        return returnMemory;
    }
    
}
