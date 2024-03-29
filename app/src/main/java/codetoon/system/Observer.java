package codetoon.system;

import codetoon.main.Main;
import codetoon.util.animation.Animation;
import org.jetbrains.annotations.NotNull;
import org.python.compiler.Code;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Observer  implements KeyListener{
    private Console console;
    public static int RUNNING_COUNT = 0;
    public static int METHOD_COUNT = 0;
    private boolean countStart = false;
    private int typingCount = 0;
    private int average = 0;
    private ArrayList<Integer> aveTypingCount = new ArrayList<>();
    int x, y, w, h;
    boolean isFirst = true;
    private int hour = 0, min = 0, sec = 0;
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            sec ++;
            if(sec >= 60){
                sec -= 60;
                min ++;
            }
            if(min >= 60){
                min -= 60;
                hour ++;
            }
        }
    };
    private static Observer instance;
    public Observer(Console c, int x, int y, int w, int h){
        console = c;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        instance = this;
    }
    public void setup(){
        Animation.create(CodeToon.GRAPHICS).draw("observer.category.game", x + 2, y + 4, new Animation.Properties().color(CodeToon.textColor).font("", Font.BOLD, 25));
        Animation.create(CodeToon.GRAPHICS).draw(new String[]{this.getEnemyRemaining()}, "observer.remaining", x + 2, y + 8, new Animation.Properties()
                .changeArgument(() -> new String[]{getEnemyRemaining()})
                .color(CodeToon.textColor).font("", Font.ITALIC, 20));

        Animation.create(CodeToon.GRAPHICS).draw(new String[]{this.getEnemyHeldMemory()}, "observer.life", x + 2, y + 12,
                new Animation.Properties().changeArgument(() ->new String[]{getEnemyHeldMemory()})
                .color(CodeToon.textColor).font("", Font.ITALIC, 20));

        Animation.create(CodeToon.GRAPHICS).draw(new String[]{""}, "observer.typing.speed", x + 2, y + 16,
                new Animation.Properties().changeArgument(() ->new String[]{getTypingAverage()})
                        .color(CodeToon.textColor).font("", Font.ITALIC, 20));

        Animation.create(CodeToon.GRAPHICS).draw(new String[]{""}, "observer.count.run", x + 2, y + 20,
                new Animation.Properties().changeArgument(() ->new String[]{getRunningCount()})
                        .color(CodeToon.textColor).font("", Font.ITALIC, 20));

        Animation.create(CodeToon.GRAPHICS).draw(new String[]{""}, "observer.count.method", x + 20, y + 20,
                new Animation.Properties().changeArgument(() ->new String[]{getMethodCount()})
                        .color(CodeToon.textColor).font("", Font.ITALIC, 20));

        Animation.create(CodeToon.GRAPHICS).draw( "observer.timer", x + 2, y + 24,
                new Animation.Properties().color(CodeToon.textColor).font("", Font.BOLD, 25));

        Animation.create(CodeToon.GRAPHICS).draw(new String[]{""}, "observer.timer.count", x + 10, y + 32, new Animation.Properties()
                .changeArgument(() -> getTimer())
                .color(CodeToon.textColor)
                .size(80));


        console.addKeyListener(this);
        Thread t = new Thread(Observer::typingSpeed);
        t.start();
        Timer timer = new Timer(false);
        timer.schedule(task,0,  1000);
    }
    private String[] getTimer(){
        StringBuilder h = new StringBuilder();
        StringBuilder m = new StringBuilder();
        StringBuilder s = new StringBuilder();
        if(hour < 10)
            h.append("0");
        if(min < 10)
            m.append("0");
        if(sec < 10)
            s.append("0");
        h.append(hour);
        m.append(min);
        s.append(sec);
        return new String[]{h.toString(),m.toString(), s.toString()};
    }
    private String getTypingAverage(){
        return new StringBuilder().append(average).toString();
    }
    private String getRunningCount(){
        return new StringBuilder().append(RUNNING_COUNT).toString();
    }
    private String getMethodCount(){
        return new StringBuilder().append(METHOD_COUNT).toString();
    }
    public void draw(@NotNull Graphics g){
        if(isFirst){
            isFirst = false;
            setup();
        }
        g.setColor(CodeToon.categoryBg);
        g.fillRect(x * Main.DW, y * Main.DH, w * Main.DW, h * Main.DH);
    }

    public static void typingSpeed(){
        while(CodeToon.isGameStart){
            System.out.print(instance.countStart ? "" : "" );
            if(instance.countStart){
                instance.countStart = false;
                instance.aveTypingCount.add(instance.typingCount);
                instance.typingCount = 0;
                if(instance.aveTypingCount.size() != 1)
                    instance.average = sum(instance.aveTypingCount) / instance.aveTypingCount.size();

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static int sum(ArrayList<Integer> aveTypingCount) {
        int c = 0;
        for(int i : aveTypingCount)
            c += i;
        return c;
    }

    public static @NotNull String getEnemyHeldMemory(){
        int c = 0;
        if(Memories.opponentMemory != null){
            for(Memory m : Memories.opponentMemory){
                if(m.getStates() != EnumMemoryStates.HACKED)
                    c ++;
            }
            for(Memory m : Memories.memory){
                if(m.getStates() == EnumMemoryStates.HACKED)
                    c ++;
            }
            return  new StringBuilder().append(c).toString();
        }
        return "NULL";
    }
    public static @NotNull String getEnemyRemaining(){
        int c = 0;
        if(Memories.opponentMemory != null){
            for(Memory m : Memories.opponentMemory){
                if(m.getStates() != EnumMemoryStates.HACKED)
                    c ++;
            }
            return new StringBuilder().append(c).toString();
        }
        return "NULL";
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        if(!countStart)
            countStart = true;
        typingCount ++;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
