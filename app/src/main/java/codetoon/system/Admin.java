package codetoon.system;

import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.method.*;
import codetoon.server.Server;
import codetoon.util.TickRegistory;
import codetoon.util.animation.Animation;
import codetoon.util.animation.AnimationImage;
import codetoon.util.lang.LangLoader;
import codetoon.variable.Variables;
import org.jetbrains.annotations.NotNull;
import org.python.compiler.Code;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Admin extends Player implements Serializable {
    private static final Admin instance = new Admin();
    private int serialID;
    private Console c;
    boolean isFirst = true;
    private Admin(){
        super();
        serialID = new Random().nextInt(10000, 999999);
        running =true;
        Thread t = new Thread(this::run);
        t.start();
    }
    @Override
    public int getSerialID() {
        return serialID;
    }

    public static Admin getInstance() {
        return instance;
    }

    @Override
    public String getName(){
        return "Admin";
    }

    @Override
    public TickRegistory getTick() {
        return TickRegistory.createTicker( this, Admin::tick);
    }

    @Override
    public void endMethod(@NotNull Console console, ArrayList<MyMethod> methods, StringBuilder source) {
        c = console;
        setRunMethod(methods);
    }

    @Override
    public void run() {
        while (running) {
            ticker.run_tick();
        }
    }

    @Override
    protected void blackList(ArrayList<MyMethod> m) {
        m.add(Methods.GET_UP_MEMORY.get());
        m.add(Methods.GET_DOWN_MEMORY.get());
        m.add(Methods.GET_LEFT_MEMORY.get());
        m.add(Methods.GET_RIGHT_MEMORY.get());
    }

    @Override
    public String getID() {
        return "Admin";
    }

    @Override
    public void connection(int password) {
        PazzleStage p = (PazzleStage) Main.getInstance().getMap();
        p.getConsole().setHost(this);
    }

    public static <T> void tick(T t){
        Admin a = (Admin) t;
        if(CodeToon.isGameStart) {
            if(!a.method.isEmpty()) {
                a.isLoading = true;
                a.c.isLoading = true;
                a.c.panel.resetAll();
                if(a.c.panel.getProgram().isEmpty())
                    a.c.panel.setProgram(new StringBuilder()
                                    .append(LangLoader.getInstance().get(null, "console.execute.process"))
                                    .append("\n")
                                    .append(LangLoader.getInstance().get(null, "console.execute.loading"))


                            , 0);
                a.runMethod();
                a.isLoading = false;
                a.c.isLoading = false;
                a.method = new ArrayList<>();
                a.c.panel.resetAll();
                Variables.VARIABLE.deleteAll(a.getID() + "_" + a.getSerialID());
                //Server.server.sendMyCopy();
                //Server.server.sendOpponentCopy();
                System.out.println("SSSSS");
            }

        }
    }


}
