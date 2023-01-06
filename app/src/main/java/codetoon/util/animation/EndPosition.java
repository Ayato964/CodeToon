package codetoon.util.animation;

import codetoon.main.Main;
import codetoon.util.Tick;

import java.awt.*;

public class EndPosition implements Decorate{
    public static final int UNDER = 10000;
    public static final int UPWARDS = 20000;
    private final int type;
    int x, y;
    public EndPosition(int x, int y, int type) {
        this.x = x * Main.DW;
        this.y = y * Main.DH;
        this.type = type;

    }
    @Override
    public void displayAction(Animation.Properties properties, Graphics g) {
        Animation a = properties.getAnimation();
        if(type == UNDER) {
            if (a.getX() * Main.DW  <= x && a.getY() * Main.DH <= y) {
                System.out.println("Over Console");
                Tick.getInstance().removeAnimation(properties.animationTickRegistory);
            }
        }
    }
}
