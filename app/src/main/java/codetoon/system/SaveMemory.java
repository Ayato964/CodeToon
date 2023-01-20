package codetoon.system;

import codetoon.method.MyMethod;
import codetoon.server.Server;
import codetoon.util.Indentification;
import codetoon.util.TickRegistory;
import codetoon.util.converter.ConvertSource;
import codetoon.variable.Variables;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SaveMemory extends Memory{
    public SaveMemory(Information info) {
        super(info.x, info.y, info.w, info.h, info.idI, info.idC);
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
        if(source != null) {
            if(!source.isEmpty()) {
                ArrayList<MyMethod> methods = ConvertSource.convert(source.toString(), this);
                setRunMethod(methods);
                runMethod();
                Variables.VARIABLE.deleteAll(getID() + "_" + getSerialID());
                Server.server.sendOpponentCopy();
                Server.server.sendMyCopy();
            }
        }
    }
}
