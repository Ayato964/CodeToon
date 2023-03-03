package codetoon.method.getter;

import codetoon.method.MyMethod;
import codetoon.system.CodeToon;
import codetoon.system.Memories;
import codetoon.system.Memory;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbstractGetMemory extends MyMethod<Memory> {

    protected ArrayList<Memory> getMyMemories(Memory m){
        for(Memory memory : Memories.memory){
            if(memory.equals(m)){
                return Memories.memory;
            }
        }
        for(Memory memory : Memories.opponentMemory){
            if(memory.equals(m)){
                return Memories.opponentMemory;
            }
        }
        return null;
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        return null;
    }

    @Override
    public Memory returnAction(Player host) {
        Memory m = (Memory) host;
        int id = m.getIdI() * CodeToon.RULE.memory_w + m.getIdC();
        return returnAction(getMyMemories(m), id);

    }
    public abstract Memory returnAction(ArrayList<Memory> memory, int id);

}
