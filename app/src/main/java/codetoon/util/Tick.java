package codetoon.util;

import codetoon.system.CodeToon;

import java.util.*;

public class Tick {
    private static final Tick INSTANCE = new Tick();
    private final ArrayList<TickRegistory> method = new ArrayList<>();
    private Tick(){

        Timer timer = new Timer(false);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(!method.isEmpty() && CodeToon.isGameStart) {
                    for (TickRegistory tickHelper : method) {
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
