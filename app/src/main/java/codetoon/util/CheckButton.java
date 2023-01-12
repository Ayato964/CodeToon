package codetoon.util;

import codetoon.main.Main;

import java.awt.*;

public class CheckButton implements IsTick{
    private TickRegistory<CheckButton> tick;

    private Graphics g;
    private boolean isChecked = false;
    int x, y, w, h;
    public CheckButton(Graphics g, int x, int y, int size, String mes){
        this.g = g;
        this.x = x;
        this.y = y;
        w = size;
        h = size;
        tick = TickRegistory.createTicker(this, CheckButton::display);
        Tick.getInstance().addAnimation(tick);
    }
    public static <T extends IsTick> void display(T t){
        CheckButton b = (CheckButton) t;
        b.g.setColor(Color.WHITE);
        if(b.isChecked)
            b.g.fillRect(b.x * Main.DW, b.y * Main.DH, b.w * Main.DW, b.h * Main.DH);
        else
            b.g.drawRect(b.x * Main.DW, b.y * Main.DH, b.w * Main.DW, b.h * Main.DH);

    }
    public boolean isChecked(){
        return isChecked;
    }

    @Override
    public boolean isClient() {
        return true;
    }
}
