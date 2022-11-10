package codetoon.util.animation;

import codetoon.main.Main;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Center extends Decorate{
    private boolean isFist;
    private int center;
    public Center(Animation.Properties properties) {
        super(properties);
        isFist = true;
    }

    @Override
    public void displayAction(@NotNull Graphics g) {
        Animation animation = properties.getAnimation();
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D rectText = fm.getStringBounds(animation.getMsg(), g).getBounds();

        if (isFist) {
            center = ((int)Main.DESCTOP_BOUNDS.getWidth() / 2 -(int) rectText.getX() / 2) ;
            isFist = false;
        }
       // System.out.println(rectText.getX() * Main.DW + "   "  +rectText.getY() * Main.DH + "   " + rectText.getWidth() * Main.DW + "   " + rectText.getHeight() * Main.DH);
       // g.drawRect(animation.getX() * Main.DW + (int)rectText.getX() * Main.DW , animation.getY() * Main.DH + (int)rectText.getY() + (int)rectText.getY() ,(int)rectText.getWidth() * Main.DW ,(int)rectText.getHeight() * Main.DH);

        animation.setX(center / Main.DW);
    }
}
