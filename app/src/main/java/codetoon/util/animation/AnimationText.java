package codetoon.util.animation;

import codetoon.main.Main;
import codetoon.util.lang.LangLoader;

import java.awt.*;

public class AnimationText extends Animation{
    private String msg;
    private String msgID;

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
    public void draw(String[] value, String str, int x, int y, Properties properties){
        super.setX(x);
        super.setY(y);

        msg = LangLoader.getInstance().get(value, str);
        msgID = str;
        //  msg = str;
        myProp = properties;
        properties.set(this, g);
        draw();
    }
    public void draw(String str, int x, int y, Properties properties){
        draw(null, str, x, y, properties);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void setVariable(String[] val){
        this.msg = LangLoader.getInstance().get(val, msgID);
    }
}
