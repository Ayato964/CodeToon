package codetoon.system;

import java.util.ArrayList;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

import codetoon.method.*;
import codetoon.util.Tick;
import codetoon.util.TickHelper;

public abstract class Player {
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
    protected abstract TickHelper getTick();
}
