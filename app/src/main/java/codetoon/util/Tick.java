package codetoon.util;

import codetoon.main.Main;
import codetoon.system.CodeToon;

import java.util.*;

public class Tick {
    private static final Tick INSTANCE = new Tick();
    private ArrayList<TickRegistory> method = new ArrayList<>();
    private ArrayList<TickRegistory> animation = new ArrayList<>();
    private Tick(){

        Timer timer = new Timer(false);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {

                    if (!method.isEmpty() && CodeToon.isGameStart) {
                        for (TickRegistory tickHelper : method) {
                            tickHelper.run_tick();
                        }
                    }

                }catch (Exception e){
                    
                }
                if(!animation.isEmpty()){
                    for(TickRegistory registory : animation){

                        registory.run_tick();
                    }
                }

            }
        };
        timer.scheduleAtFixedRate(task, 0,1);

    }
    public void removeAnimation(TickRegistory regi){
        ArrayList<TickRegistory> temp = new ArrayList<>();

        for(int i = 0; i <animation.size(); i ++){
            if(!animation.get(i).equals(regi)){
                temp.add(animation.get(i));
            }
        }
        animation = temp;
    }
    public void addMethod(TickRegistory run_tick){

        method.add(run_tick);
    }
    public void addAnimation(TickRegistory run_tick){
        animation.add(run_tick);
    }
    public static Tick getInstance(){
        return INSTANCE;
    }
}
