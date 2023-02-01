package codetoon.method;

import codetoon.system.Memories;
import codetoon.system.Memory;

import java.util.ArrayList;

public abstract class AbstractGetMemory extends MyMethod<Memory>{

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

}
