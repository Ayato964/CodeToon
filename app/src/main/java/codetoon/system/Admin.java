package codetoon.system;

import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.method.*;
import codetoon.util.TickRegistory;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Admin extends Player implements Serializable {
    private static final Admin instance = new Admin();
    private int serialID;
    private Admin(){
        serialID = new Random().nextInt(10000, 999999);
    }

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
        setRunMethod(methods);
        run();
        console.panel.resetAll();
    }

    @Override
    protected void blackList(ArrayList<MyMethod> m) {

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
        if(CodeToon.isGameStart) {

        }
    }


}
