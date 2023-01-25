package codetoon.system;

import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.method.MyMethod;
import codetoon.server.Server;
import codetoon.util.Indentification;
import codetoon.util.TickRegistory;
import codetoon.util.converter.ConvertSource;
import codetoon.variable.Variables;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class SaveMemory extends Memory implements Serializable {
    public boolean isClickedRun = false;
    public SaveMemory(Information info) {
        super(info.x, info.y, info.w, info.h, info.idC, info.idI);
        memorySirialID = info.memorySerial;
        states = EnumMemoryStates.SAVEMODE;
        serialID = info.serial;
        pass = info.pass;
    }

    @Override
    public TickRegistory getTick() {
        return null;
    }

    @Override
    public void endMethod(@NotNull Console console, ArrayList<MyMethod> methods, StringBuilder source) {
        setRunMethod(methods);
        console.setHost(Admin.getInstance());
        Indentification.removeEnd(source);
        this.source = source;
        console.panel.resetAll();
        Server.server.sendOpponentCopy();
        Server.server.sendMyCopy();
    }

    @Override
    public void run() {
        if(serialID == Admin.getInstance().getSerialID()) {
            if (source != null) {
                if (!source.isEmpty() && isClickedRun) {
                    ArrayList<MyMethod> methods = ConvertSource.convert(source.toString(), this);
                    setRunMethod(methods);
                    runMethod();
                    Variables.VARIABLE.deleteAll(getID() + "_" + getSerialID());
                    isClickedRun = false;
                    Server.server.sendOpponentCopy();
                    Server.server.sendMyCopy();
                }
            }
        }else{
            Message.addMessage("savememory.run.error");
        }
    }

    @Override
    public void hacking(int pass, int hostSerialID) {
        System.out.println(serialID +"   " + hostSerialID);
        if(serialID != hostSerialID && states != EnumMemoryStates.HACKED) {
            if (pass == this.pass) {
                states = EnumMemoryStates.HACKED;
                this.pass = 0;
                serialID = hostSerialID;
                removeAnimation();

                PazzleStage p = (PazzleStage) Main.getInstance().getMap();
                Memories.memory.remove(p.MEMORY_H * getIdC() + getIdI());
                Memories.memory.add(p.MEMORY_H * getIdC() + getIdI(), new Memory(getInfo()));
                Memories.runThread(Memories.memory.get(p.MEMORY_H * getIdC() + getIdI()));
            } else {
                Message.addMessage("memory.attack.mes1", Color.BLACK);
            }
        }else{
            Message.addMessage("memory.attack.mes2");
        }
    }
}
