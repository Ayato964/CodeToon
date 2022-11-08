package codetoon.util;

import codetoon.main.Main;
import codetoon.system.CodeToon;

import java.util.*;

public class Tick {
    private static final Tick INSTANCE = new Tick();
    private ArrayList<TickRegistory> method = new ArrayList<>();
    private ArrayList<TickRegistory> animation = new ArrayList<>();
    private int count = 0;
    private float animationCount = 0;
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

                animationCount += 1;
                if(animationCount / 1000 >= 0.05 ) {
                    animationCount = 0;
                    Main.getMainGraphics().clearRect(0, 0, 2000, 1500);

                    Main.getInstance().displayMap.display(Main.getMainGraphics());
                    if (!animation.isEmpty()) {
                        while (count < animation.size()) {
                            animation.get(count).run_tick();
                            count++;
                        }
                        count = 0;
                    }

                    Main.getInstance().repaint();
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
        count = 0;
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
