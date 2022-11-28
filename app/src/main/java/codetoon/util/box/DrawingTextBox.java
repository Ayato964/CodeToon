package codetoon.util.box;

import codetoon.main.*;
import codetoon.util.ContainerData;
import codetoon.util.animation.Animation;

import java.awt.*;
public class DrawingTextBox extends Box {
    private String mes;
    private Font font;
    private Color bg;
    private Graphics g;
    private Animation animation;
    public DrawingTextBox(String s){
        super(0, 0, 0, 0);
        g = Main.getMainGraphics();
        mes = s;
        font = new Font("", Font.PLAIN, 32);
        bg = Color.WHITE;
    }
    public DrawingTextBox(String s, int x, int y, int width, int height){
        super(x, y, width, height);
        mes = s;

    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        animation.setY(y);
        animation.setX(x);
    }

    public void draw(int x, int y, int w, int h){
        g.setColor(bg);
        g.drawRect(x, y, w, h);

        Animation.create(g).draw(mes, x / Main.DW, y / Main.DH + (h / Main.DH) / 2,
                new Animation.Properties()
                        .size(30)
                        .color(Color.WHITE)
        );
     
    }

    @Override
    public void pressedMouse(ContainerBox box, ContainerData data, int i) {
        data.action(i);
        Main.getInstance().removeMouseListener(box);
    }

}