package codetoon.util.animation;

import codetoon.main.Main;
import codetoon.util.function.IsBoolInterface;

import java.awt.*;

public class FrameCenter extends DecorateTextLib implements Decorate{
    Color color;
    int w, h;
    IsBoolInterface bool;
    public FrameCenter(Color c, int w, int h){
        this(c, w, h, () -> true);
    }
    public FrameCenter(Color c, int w, int h, IsBoolInterface b){
        color = c;
        this.w = w * Main.DW;
        this.h = h * Main.DH;
        bool = b;
    }
    @Override
    public void displayAction(Animation.Properties p, Graphics g) {
        if(bool.bool()) {
            int DW = (int) Main.DESCTOP_BOUNDS.getWidth();
            int center = DW / 2;

            int y = p.getAnimation().getY() * Main.DH;
            int frameX = center - w / 2;
            g.setColor(color);
            g.drawRect(frameX, y - h, w, h);
        }
    }
}
