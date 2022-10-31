package codetoon.util;

import java.io.IOError;

public class TickRegistory<T extends IsTick> {
    private final T t;
    private final TickHelper tick_method;
    public  TickRegistory(T t, TickHelper tick){
        this.t = t;
        tick_method = tick;
        Tick.getInstance().add(this);
    }
    public void run_tick(){

        tick_method.tick(t);
    }

    public static <A extends IsTick>  TickRegistory createTicker(A r, TickHelper t){
            return new TickRegistory(r, t);
    }
}
