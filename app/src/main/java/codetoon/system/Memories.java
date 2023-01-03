package codetoon.system;

import java.util.ArrayList;
import codetoon.variable.*;
import org.checkerframework.checker.units.qual.A;

public class Memories {

    public static ArrayList<Memory> memory;
    public static ArrayList<Memory> opponentMemory;
    private static final Memories instance = new Memories();

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

    public static Memories getInstance() {
        return instance;
    }

    public static void stopAll(){
        for(Memory memory1 : memory){
            memory1.running = false;
        }
        if(opponentMemory != null) {
            for (Memory memory1 : opponentMemory) {
                memory1.running = false;
            }
        }
        memory = new ArrayList<>();
        opponentMemory = new ArrayList<>();
    }
    public static void updateMemory(ArrayList<Memory> beMemory, Memory m, int i){
            m.running = false;
            beMemory.remove(i);
            beMemory.add(i, m);
            runThread(beMemory.get(i));
    }
    private static void runThread(Memory m){
            m.running = true;
            Thread t = new Thread(m);
            t.start();
    }
    public static Memory get(int i){
        return memory.get(i);
    }

    public static void equalsMemory(ArrayList<Memory> beMemory, ArrayList<Memory> upMemory) {
        for (int i = 0; i < CodeToon.MEMORY_SIZE * CodeToon.MEMORY_SIZE; i++) {
            if (beMemory != null) {
                if (!beMemory.get(i).equals(upMemory.get(i))) {
                    updateMemory(beMemory, upMemory.get(i), i);
                }
            } else {
                beMemory = upMemory;
            }
        }
    }
}
