package codetoon.util.animation;

import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.system.CodeToon;
import codetoon.system.Message;

import javax.net.ssl.SNIHostName;
import java.awt.*;

public class Width implements Decorate{
    public static final int UPPER = 0;
    public static final int UNDER = 1;
    int w;
    private final int layout;
    boolean isFirst = true;
    public Width(int layout, int w) {
        this.layout = layout;
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
                if(Main.getInstance().getMap() instanceof PazzleStage)
                   Message.shiftMessage();
                builder.insert(i, '\n');
                row ++;
                c = 0;
            }
        }
        if(layout == UPPER) {
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
        }else{
            boolean isFirstMsg = true;
            int tmpRow = row;
            for( String str : builder.toString().split("\n")){
                if (isFirstMsg) {
                    properties.getAnimation().myProp.setAllPosition(properties.getAnimation().getX(),
                            properties.getAnimation().getY() + g.getFontMetrics().getHeight() / 4 * (tmpRow - row));
                    text.setMsg(str);
                    isFirstMsg = false;
                    row --;
                } else {
                    Animation.Properties p = new Animation.Properties().copy(properties);
                    Animation.create(g).draw(str, a.getX(), a.getY() + g.getFontMetrics().getHeight() / 4 * (tmpRow - row), p);
                    properties.setChild(p);
                    row--;
                }
            }
        }
        //properties.getAnimation().setMsg(builder.toString());
    }
}
