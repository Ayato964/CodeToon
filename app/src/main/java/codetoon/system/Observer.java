package codetoon.system;

import codetoon.main.Main;
import codetoon.util.animation.Animation;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Observer {
    int x, y, w, h;
    boolean isFirst = true;
    public Observer(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        setup();

    }
    public void setup(){
        Animation.create(CodeToon.GRAPHICS).draw("observer.category.game", x + 2, y + 4, new Animation.Properties().color(Color.BLACK).font("", Font.BOLD, 20));
        Animation.create(CodeToon.GRAPHICS).draw(new String[]{this.getEnemyRemaining()}, "observer.remaining", x + 2, y + 8, new Animation.Properties()
                .changeArgument(() -> new String[]{getEnemyRemaining()})
                .color(Color.BLACK).font("", Font.ITALIC, 20));
        Animation.create(CodeToon.GRAPHICS).draw(new String[]{this.getEnemyHeldMemory()}, "observer.life", x + 2, y + 12,
                new Animation.Properties().changeArgument(() ->new String[]{getEnemyHeldMemory()})
                .color(Color.BLACK).font("", Font.ITALIC, 20));
    }
    public void draw(@NotNull Graphics g){
        if(isFirst){
            isFirst = false;
            setup();
        }
        g.setColor(Color.WHITE);
        g.fillRect(x * Main.DW, y * Main.DH, w * Main.DW, h * Main.DH);
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
}
