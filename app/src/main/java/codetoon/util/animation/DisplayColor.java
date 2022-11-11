package codetoon.util.animation;

import java.awt.*;

public class DisplayColor extends Decorate{
    private Color color;
    public DisplayColor(Animation.Properties properties, Color c) {
        super(properties);
        this.color = c;
    }

    @Override
    public void displayAction(Graphics g) {
        properties.setColor(color);

    }
}
