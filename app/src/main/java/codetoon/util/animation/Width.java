package codetoon.util.animation;

import codetoon.main.Main;
import codetoon.system.Message;

import javax.net.ssl.SNIHostName;
import java.awt.*;

public class Width implements Decorate{
    int w;
    boolean isFirst = true;
    public Width(Animation.Properties properties, int w) {
        this.w = w;
    }

    @Override
    public void displayAction(Animation.Properties properties, Graphics g) {
        AnimationText text =(AnimationText) properties.getAnimation();
        StringBuilder builder = new StringBuilder().append(text.getMsg());
        Animation a = properties.getAnimation();
        int c = 0;
        int row = 0;
        for(int i = 0; i < builder.length(); i ++){
            c += g.getFontMetrics().charWidth(builder.charAt(i));
            if(c >= w * Main.DW){
                Message.shiftMessage();
                builder.insert(i, '\n');
                row ++;
                c = 0;
            }
        }
        if(isFirst) {
            boolean isFirstMsg = true;
            for (String str : builder.toString().split("\n")) {
                if (isFirstMsg) {
                    properties.getAnimation().myProp.setAllPosition(properties.getAnimation().getX(),
                            properties.getAnimation().getY() - g.getFontMetrics().getHeight() / 4 * row);
                    text.setMsg(str);
                    isFirstMsg = false;
                } else {
                    Animation.Properties p = new Animation.Properties().copy(properties);
                    Animation.create(g).draw(str, a.getX(), a.getY() - g.getFontMetrics().getHeight() / 4 * row, p);
                    properties.setChild(p);
                    row--;
                }
            }
        }
        //properties.getAnimation().setMsg(builder.toString());
    }
}
