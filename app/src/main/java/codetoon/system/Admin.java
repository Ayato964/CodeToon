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
    public void setRunMethod(ArrayList<MyMethod> m) {
        super.setRunMethod(m);
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
    ArrayList<MyMethod> removeBlackList(ArrayList<MyMethod> m) {
        return m;
    }

    public static <T> void tick(T t){
        if(CodeToon.isGameStart) {
        }
    }


}
