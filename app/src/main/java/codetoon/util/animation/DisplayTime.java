package codetoon.util.animation;

import codetoon.system.CodeToon;

import java.awt.*;

public class DisplayTime extends Decorate{
    private int displayTime;
    public DisplayTime(Animation.Properties properties, int time) {
        super(properties);
        displayTime = time;
    }

    @Override
    public void displayAction(Graphics g) {
        if(properties.getCount() / 25 >= displayTime && displayTime != CodeToon.INFINITY && !properties.isEnd()){
            properties.getAnimationTickRegistory().remove();
            properties.setIsEnd(true);
        }
    }
}
