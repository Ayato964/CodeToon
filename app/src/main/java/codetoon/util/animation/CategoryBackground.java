package codetoon.util.animation;

import codetoon.main.Main;

import java.awt.*;

public class CategoryBackground extends DecorateTextLib implements Decorate {
    protected  Color color;
    protected int x, y, w, h;
    public CategoryBackground(Color c){
        this(c, -1, -1);
    }
    public CategoryBackground(Color c, int x, int y){
        this(c, x, y, -1, -1);
    }
    public CategoryBackground(Color c, int x, int y, int w, int h){
        color = c;
        this.x = x * Main.DW;
        this.y = y * Main.DH;
        this.w = w * Main.DW;
        this.h = h * Main.DH;
    }
    @Override
    public void displayAction(Animation.Properties p, Graphics g) {
        if(x == -1 * Main.DW && y == -1 * Main.DH){
            x = p.getAnimation().getX() * Main.DW;
            y = p.getAnimation().getY() * Main.DH;
        }
        if(h == -1 * Main.DH)
            h = g.getFontMetrics().getHeight();
        if(w == -1 * Main.DW)
            w = getTextWidth(((AnimationText)p.getAnimation()).getMsg(), g);

        g.setColor(color);
        g.fillRect(x, y - g.getFontMetrics().getHeight(), w , h );
    }
}
