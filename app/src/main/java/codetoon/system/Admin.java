package codetoon.system;

import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.method.*;
import codetoon.util.TickRegistory;

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
    public void endMethod() {
        Console c = ((PazzleStage)Main.getInstance().getMap()).getConsole();
        setRunMethod(c.getMethods());
        run();
        c.panel.resetAll();
    }

    public static <T> void tick(T t){
        if(CodeToon.isGameStart) {
        }
    }


}
