package codetoon.util.animation;

import codetoon.main.Main;

import java.awt.*;

public class AnimationText extends Animation{
    private String msg;

    protected AnimationText(Graphics g) {
        super(g);
    }

    @Override
    public void draw() {
        g.drawString(msg, getX() * Main.DW, getY() * Main.DH);
    }
    public void draw(String str, int x, int y){
        draw(str, x, y, new Properties());
    }
    public void draw(String str, int x, int y, Properties properties){
        super.setX(x);
        super.setY(y);
        msg = str;
        myProp = properties;
        properties.set(this, g);
        draw();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
