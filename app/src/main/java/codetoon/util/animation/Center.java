package codetoon.util.animation;

import codetoon.main.Main;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Center extends Decorate{
    public Center(Animation.Properties properties) {
        super(properties);
    }

    @Override
    public void displayAction(@NotNull Graphics g) {
        int DW = (int) Main.DESCTOP_BOUNDS.getWidth();
        int strLength = stringWidth(properties.getAnimation().getMsg(), g);
        System.out.println(strLength);
        int center = DW / 2;
        properties.getAnimation().setX((center - strLength / 2) / Main.DW);

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
