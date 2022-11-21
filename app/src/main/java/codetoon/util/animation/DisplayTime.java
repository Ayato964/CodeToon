package codetoon.util.animation;

import codetoon.system.CodeToon;

import java.awt.*;

public class DisplayTime implements Decorate{
    private int displayTime;
    public DisplayTime(int time) {
        displayTime = time;
    }

    @Override
    public void displayAction(Animation.Properties properties,Graphics g) {
        if(properties.getCount() / 25 >= displayTime && displayTime != CodeToon.INFINITY && !properties.isEnd()){
            properties.getAnimationTickRegistory().remove();
            properties.setIsEnd(true);
        }
    }
}
