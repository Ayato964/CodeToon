package codetoon.system;

import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.method.*;
import codetoon.util.TickRegistory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
public class Admin extends Player{
    private static final Admin instance = new Admin();
    private Admin(){
        
    }

    public static Admin getInstance() {
        return instance;
    }

    @Override
    public String getName(){
        return "Player";
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
    public void connection(int password) {
        PazzleStage p = (PazzleStage) Main.getInstance().getMap();
        p.getConsole().setHost(this);
    }

    public static <T> void tick(T t){
        if(CodeToon.isGameStart) {

        }
    }


}
