package codetoon.util;

public class TickRegistory<T extends  IsTick> {
    private final T t;
    private final TickHelper tick_method;
    public  TickRegistory(T t, TickHelper tick){
        this.t = t;
        tick_method = tick;
    }
    public void run_tick(){
        tick_method.tick(t);
    }

    public static <R extends IsTick> TickRegistory createTicker(R r, TickHelper t){
        return new TickRegistory(r, t);
    }
}
