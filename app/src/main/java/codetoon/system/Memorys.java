package codetoon.system;

import java.util.ArrayList;
import codetoon.variable.*;

public class Memorys {

    public static ArrayList<Memory> memory;
    public static ArrayList<Memory> opponentMemory;

    public static void setInstance(int size, int x, int y, int w, int h){
        memory = new ArrayList<>();
        for(int i = 0; i < size; i ++){
          for(int c = 0; c < size; c ++){
            Memory t = new Memory(x + c * (w / size), y + i * (h / size), w / size, h / size, c, i);
            
            memory.add(t);
          }
        }
        Variables.createVariable("memory", () -> new MemoryVariable());

    }
    public static Memory get(int i){
        return memory.get(i);
    }
    
}
