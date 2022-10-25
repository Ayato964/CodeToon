package codetoon.system;

import java.util.ArrayList;

import codetoon.method.*;
import codetoon.util.IsTick;
import codetoon.util.Tick;
import codetoon.util.TickHelper;
import codetoon.util.TickRegistory;


public abstract class Player implements IsTick {
    ArrayList<MyMethod> method = new ArrayList<>();
    protected TickRegistory<Player> ticker = getTick();

    public Player(){
    }
    public void setRunMethod(ArrayList<MyMethod> m){
        m = removeBlackList(m);
        method = m;
    }
    public void run(){
        if(!method.isEmpty()) {
            for (int i = 0; i < method.size(); i++) {
                method.get(i).action(i);

            }
        }
    }

    public abstract String getName();
    public abstract TickRegistory getTick();
    public abstract void endMethod(Console console, ArrayList<MyMethod> methods, StringBuilder source);
    abstract ArrayList<MyMethod> removeBlackList(ArrayList<MyMethod> m);
}
