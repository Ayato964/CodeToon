package codetoon.util.animation;

import codetoon.main.Main;
import codetoon.util.IsBoolInterface;

import java.awt.*;

public class Frame implements Decorate{
    int w = 0, h = 0;
    Color color;
    IsBoolInterface bool;
    public Frame(Color c){
        this(c, ()->true);
    }
    public Frame(Color c, IsBoolInterface inter){
        color = c;
        bool = inter;
    }
    public Frame(Color c, int w, int h, IsBoolInterface inter){
        this(c, inter);
        this.w = w * Main.DW;
        this.h = h;
    }
    @Override
    public void displayAction(Animation.Properties p, Graphics g) {
        if(bool.bool()) {
            g.setColor(color);
            if (p.getAnimation() instanceof AnimationText) {
                AnimationText text = (AnimationText) p.getAnimation();
                w = w == 0 ? getTextWidth(text.getMsg(), g) : w;
                h = h == 0 ? g.getFontMetrics().getHeight() : h;
                g.drawRect(p.getAnimation().getX() * Main.DW, p.getAnimation().getY() * Main.DH - h, w, h);
            }
            if (p.getAnimation() instanceof AnimationImage) {
                AnimationImage ani = (AnimationImage) p.getAnimation();
                w = w == 0 ? ani.getW() * Main.DW: w;
                h = h == 0 ? ani.getH() * Main.DH : h;
                g.drawRect(p.getAnimation().getX() * Main.DW, p.getAnimation().getY() * Main.DH, w, h);
            }
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
