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
    public Animation draw()
    {
        g.drawString(msg, getX() * Main.DW, getY() * Main.DH);
        return this;
    }
    public Animation draw(String str, int x, int y){

        return draw(str, x, y, new Properties());
    }
    public Animation draw(String[] value, String str, int x, int y, Properties properties){
        super.setX(x);
        super.setY(y);

        msg = LangLoader.getInstance().get(value, str);
        msgID = str;
        //  msg = str;
        myProp = properties;
        properties.set(this, g);
        return draw();
    }
    public Animation draw(String str, int x, int y, Properties properties){
        return draw(null, str, x, y, properties);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = LangLoader.getInstance().get(null, msg);
    }
    public void setMsg(String[] s, String mes){
        this.msg = LangLoader.getInstance().get(s, mes);
    }
    public void setVariable(String[] val){
        this.msg = LangLoader.getInstance().get(val, msgID);
    }
}
