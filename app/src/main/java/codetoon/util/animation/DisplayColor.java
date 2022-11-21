package codetoon.util.animation;

import java.awt.*;

public class DisplayColor implements Decorate{
    private Color color;
    public DisplayColor(Color c) {
        this.color = c;
    }

    @Override
    public void displayAction(Animation.Properties properties, Graphics g) {
        g.setColor(color);

    }
}
