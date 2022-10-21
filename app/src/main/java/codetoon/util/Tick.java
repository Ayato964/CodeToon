package codetoon.util;

import java.util.*;

public class Tick {
    private static final Tick INSTANCE = new Tick();
    private final ArrayList<TickRegistory> method = new ArrayList<>();
    private Tick(){

        Timer timer = new Timer(false);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (TickRegistory tickHelper : method) {
                    if(tickHelper != null) {
                        tickHelper.run_tick();
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0,1);

    }
    public void add(TickRegistory run_tick){

        method.add(run_tick);
    }
    public static Tick getInstance(){
        return INSTANCE;
    }
}
