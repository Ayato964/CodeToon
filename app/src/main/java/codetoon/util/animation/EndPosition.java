package codetoon.util.animation;

import codetoon.util.Tick;

import java.awt.*;

public class EndPosition implements Decorate{
    public static final int UNDER = 10000;
    public static final int UPWARDS = 20000;
    private final int type;
    int x, y;
    public EndPosition(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;

    }
    @Override
    public void displayAction(Animation.Properties properties, Graphics g) {
        Animation a = properties.getAnimation();
        if(type == UNDER) {
            if (a.getX() <= x && a.getY() <= y) {
                Tick.getInstance().removeAnimation(properties.animationTickRegistory);
            }
        }
    }
}
