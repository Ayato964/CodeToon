package codetoon.system;

import java.util.ArrayList;
import codetoon.variable.*;

public class Memories {

    public static ArrayList<Memory> memory;
    public static ArrayList<Memory> opponentMemory;

    public static void setInstance(int size, int x, int y, int w, int h){
        memory = new ArrayList<>();
        for(int i = 0; i < size; i ++){
          for(int c = 0; c < size; c ++){
            Memory t = new Memory(x + i * (w / size), y + c * (h / size), w / size, h / size, i, c);
            Thread th = new Thread(t);
            th.start();
            memory.add(t);

          }
        }
        Variables.createVariable("memory", () -> new MemoryVariable());

    }
    public static void updateMemory(ArrayList<Memory> m){
        for(Memory memory1 : memory){
            memory1.running = false;
        }
        memory = m;
        runThread(memory);
    }
    private static void runThread(ArrayList<Memory> m){
        for(Memory memory1 : m){
            memory1.running = true;
            Thread t = new Thread(memory1);
            t.run();
        }
    }
    public static Memory get(int i){
        return memory.get(i);
    }
    
}
