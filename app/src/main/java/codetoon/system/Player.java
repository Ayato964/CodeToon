package codetoon.system;

import java.util.ArrayList;
import codetoon.method.*;
import codetoon.util.IsTick;
import codetoon.util.TickRegistory;
import codetoon.util.animation.Animation;
import org.jetbrains.annotations.NotNull;


public abstract class Player implements IsTick, Runnable{
    protected Animation lock;
    protected Animation connect;
    boolean isLoading = false;

    public boolean running = true;
    ArrayList<MyMethod> method = new ArrayList<>();
    ArrayList<MyMethod> blackList = new ArrayList<>();
    protected TickRegistory<Player> ticker;

    public Player(){
        blackList(blackList);
        ticker = getTick();


    }
    public void setRunMethod(ArrayList<MyMethod> m){
        m = removeBlackList(m);
        method = m;
    }
    public void runMethod(){
        if(!method.isEmpty()) {
            for (int i = 0; i < method.size(); i++) {
                method.get(i).action(this);

            }
        }
    }

    @Override
    public void run() {
        while (running) {
            ticker.run_tick();

            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public @NotNull ArrayList<MyMethod> removeBlackList(@NotNull ArrayList<MyMethod> m) {
        if(m != null) {
            ArrayList<MyMethod> tmp = new ArrayList<>();
            for (int i = 0; i < m.size(); i++) {
                boolean isNotBlackList = true;
                if (!blackList.isEmpty()) {
                    for (int c = 0; c < blackList.size(); c++) {
                        if (m.get(i).getClass() == blackList.get(c).getClass()) {
                            System.out.println(m.get(i).getClass() + "is BlackList");
                            isNotBlackList = false;
                        }
                    }
                }
                if (isNotBlackList) {
                    tmp.add(m.get(i));
                }
            }
            return tmp;
        }
        return m;
    }


    public abstract String getName();
    public abstract TickRegistory getTick();
    public abstract void endMethod(Console console, ArrayList<MyMethod> methods, StringBuilder source);
    protected abstract void blackList(ArrayList<MyMethod> m);
    public abstract String getID();

    @Override
    public boolean isClient() {
        return true;//特に意味はない
    }

    public abstract void connection(int password);
    public abstract int getSerialID();
}
