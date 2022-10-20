package codetoon.system;

import java.util.ArrayList;

import codetoon.method.*;
import codetoon.util.IsTick;
import codetoon.util.Tick;
import codetoon.util.TickHelper;
import codetoon.util.TickRegistory;

public abstract class Player implements IsTick {
    ArrayList<MyMethod> method = new ArrayList<>();

    public Player(){
        Tick.getInstance().add(getTick());
    }
    public void setRunMethod(ArrayList<MyMethod> m){
        method = m;
    }
    public void run(){
        for(int i = 0; i < method.size(); i ++){
            method.get(i).action(i);

        }
    }

    public abstract String getName();
    public abstract TickRegistory getTick();
}
