package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.server.Server;
import codetoon.system.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public abstract class Mode extends MyMethod<Object>{
    String memoryStr;
    String pass = "0";


    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        memoryStr = map.get(CodeToon.PARCENT_ARGUMENT);
        if(map.get(0) != null)
            pass = map.get(0);
        return null;
    }

    @Override
    public void action(Player host) {
        Memory memory = (Memory) ObjectArgument.getInstance().indentification(memoryStr, host);
        if (memory.pass == IntegerArgument.getInstance().indentification(pass, host))
            if (memory.getStates() != EnumMemoryStates.HACKED)
                if (memory.getSerialID() == Admin.getInstance().getSerialID())
                    change(host, memory);
                else
                    Message.addMessage("memory.mode.error.notmyself");
            else
                Message.addMessage(new String[]{memory.getName()}, "memory.mode.error.haveenemy");
        else
            Message.addMessage(new String[]{memory.getName()}, "memory.connection.mes4");
    }
    protected void changeMemory(Memory memory, Memory newMemory, boolean isRunning){
        PazzleStage p = (PazzleStage) Main.getInstance().getMap();
        memory.running = isRunning;
        memory.removeAnimation();
        Memories.memory.remove(p.MEMORY_H * memory.getIdC() + memory.getIdI());
        Memories.memory.add(p.MEMORY_H * memory.getIdC() + memory.getIdI(), newMemory);
        if(isRunning)
            Memories.runThread(Memories.memory.get(p.MEMORY_H * memory.getIdC() + memory.getIdI()));

        Server.server.sendMyCopy();
        Server.server.sendOpponentCopy();
    }
    protected abstract void change(Player host, Memory memory);
}
