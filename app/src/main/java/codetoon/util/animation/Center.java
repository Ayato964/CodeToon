package codetoon.util.animation;

import codetoon.main.Main;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Center implements Decorate{
    public Center() {


    }

    @Override
    public void displayAction(Animation.Properties properties, @NotNull Graphics g) {
        int DW = (int) Main.DESCTOP_BOUNDS.getWidth();
        int center = DW / 2;

        if(properties.getAnimation() instanceof AnimationText) {
            int strLength = stringWidth(((AnimationText) properties.getAnimation()).getMsg(), g);
            properties.getAnimation().setX((center - strLength / 2) / Main.DW);
        }
        if(properties.getAnimation() instanceof AnimationImage){
            AnimationImage ai = (AnimationImage) properties.getAnimation();

            properties.getAnimation().setX((center - ai.getW() * Main.DW / 2) / Main.DW);
        }

    }
    private int stringWidth(String str, Graphics g){
        int width = 0;
        for(int i = 0; i < str.length(); i ++){
            width += charWidth(str.charAt(i), g);
        }
        return width;
    }
    private int charWidth(char c, Graphics g){
        return g.getFontMetrics().charWidth(c);
    }
}
