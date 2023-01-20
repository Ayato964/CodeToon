package codetoon.system;

import java.util.ArrayList;

import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.variable.*;
import org.checkerframework.checker.units.qual.A;

public class Memories {

    public static ArrayList<Memory> memory;
    public static ArrayList<Memory> opponentMemory;
    private static final Memories instance = new Memories();

    public static void setInstance(int mw, int mh, int x, int y, int w, int h, int pass){
        memory = new ArrayList<>();
        for(int i = 0; i < mh; i ++){
          for(int c = 0; c < mw; c ++){
            Memory t = new Memory(x + i * (w / mw), y + c * (h / mh), w / mw, h / mh, i, c);
            t.pass = pass;
            Thread th = new Thread(t);
            th.start();
            memory.add(t);

          }
        }
        Variables.VARIABLE.createRegistory("memory", () -> new MemoryVariable());

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
    public static void updateMemory(Memory m, int i){
            memory.get(i).running = false;
            memory.remove(i);
            memory.add(i, m);
            runThread(memory.get(i));
            updateConsoleHost(memory.get(i));
    }
    public static void updateOpponentMemory(Memory m, int i){
        m.running = false;
        opponentMemory.remove(i);
        opponentMemory.add(i, m);
        updateConsoleHost(opponentMemory.get(i));
    }
   public static void runThread(Memory m){
            m.running = true;
            Thread t = new Thread(m);
            t.start();
    }
    public static Memory get(int i){
        return memory.get(i);
    }

    public static void equalsMemory(ArrayList<Memory> upMemory) {
        if(Memories.memory != null) {
            for (int i = 0; i < CodeToon.RULE.memory_w * CodeToon.RULE.memory_h; i++) {
                if (!Memories.memory.get(i).equals(upMemory.get(i))) {
                    System.out.println(Memories.memory.get(i).getSource());
                    System.out.println(upMemory.get(i).getSource());
                    updateMemory(upMemory.get(i), i);
                }
            }
        }else {
            Memories.memory = upMemory;
        }
    }
    public static void equalsOpponentMemory(ArrayList<Memory> upMemory) {
        if(Memories.opponentMemory != null) {
            for (int i = 0; i <CodeToon.RULE.memory_w * CodeToon.RULE.memory_h; i++) {
                if (!Memories.opponentMemory.get(i).equals(upMemory.get(i))) {
                    updateOpponentMemory(upMemory.get(i), i);
                }
            }
        }else {
            Memories.opponentMemory = upMemory;
        }
    }
    public static void updateConsoleHost(Memory m){
        Console c = ((PazzleStage)Main.getInstance().getMap()).getConsole();
        Memory consoleMemory =(Memory) (c.getHost() instanceof Admin ? null : c.getHost());
        if(consoleMemory != null)
            if(consoleMemory.getMemorySirialID() == m.getMemorySirialID())
                c.setHost(m);

    }
}
