package codetoon.util;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Tick {
    private static final Tick INSTANCE = new Tick();
    private final ArrayList<TickHelper> method = new ArrayList<>();
    private Tick(){

        Timer timer = new Timer(false);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (TickHelper tickHelper : method) {
                    tickHelper.tick();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0,1);

    }
    public void add(TickHelper t){
        method.add(t);
    }
    public static Tick getInstance(){
        return INSTANCE;
    }
}
