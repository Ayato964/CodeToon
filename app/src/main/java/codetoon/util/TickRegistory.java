package codetoon.util;

import java.io.IOError;
import java.io.Serializable;

public class TickRegistory<T extends IsTick> {
    private final T t;
    private final TickHelper tick_method;
    public  TickRegistory(T t, TickHelper tick){
        this.t = t;
        tick_method = tick;
    }

    public void run_tick(){
        if(t.isClient()) {
            tick_method.tick(t);
        }
    }
    public void remove(){
        Tick.getInstance().removeAnimation(this);
    }
    public static <A extends IsTick>  TickRegistory createTicker(A r, TickHelper t){
        TickRegistory tr = new TickRegistory(r, t);
        Tick.getInstance().addMethod(tr);
            return tr;
    }
    public static <A extends  IsTick> TickRegistory createTickerAnimation(A r, TickHelper t){
        TickRegistory tr = new TickRegistory(r, t);
        Tick.getInstance().addAnimation(tr);
        return tr;
    }
}
