package codetoon.util.animation;

import codetoon.main.Main;

import java.awt.*;

public class Frame implements Decorate{
    Color color;
    public Frame(Color c){
        color = c;
    }
    @Override
    public void displayAction(Animation.Properties p, Graphics g) {
        int w = 0, h = 0;
        g.setColor(color);
        if(p.getAnimation() instanceof AnimationText){
            AnimationText text = (AnimationText) p.getAnimation();
            w = getTextWidth(text.getMsg(), g);
            h = g.getFontMetrics().getHeight();
            g.drawRect(p.getAnimation().getX() * Main.DW, p.getAnimation().getY() * Main.DH - h, w, h);
        }
        if(p.getAnimation() instanceof AnimationImage){
            AnimationImage ani = (AnimationImage) p.getAnimation();
            w = ani.getW() * Main.DW;
            h = ani.getH() * Main.DH;
            g.drawRect(p.getAnimation().getX() * Main.DW, p.getAnimation().getY() * Main.DH, w, h);
        }
    }

    private int getTextWidth(String mes, Graphics g) {
        int len = 0;
        for(int i = 0; i < mes.length(); i ++){
           len += g.getFontMetrics().charWidth(mes.charAt(i));
        }
        return len;
    }

}
