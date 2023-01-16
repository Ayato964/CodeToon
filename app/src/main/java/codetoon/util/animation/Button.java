package codetoon.util.animation;

import codetoon.main.Main;
import codetoon.util.Action;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button implements Decorate, MouseListener {
    Action data;
    Graphics g;
    int x, y, w, h;
    public Button(Action data){
        this.data = data;
        Main.getInstance().addMouseListener(this);
    }
    @Override
    public void displayAction(Animation.Properties p, Graphics g) {
        this.g = g;

        if(p.getAnimation() instanceof AnimationText){
            AnimationText text = (AnimationText) p.getAnimation();
            w = getTextWidth(text.getMsg(), g) ;
            h = g.getFontMetrics().getHeight() + 20 ;
            x = (p.getAnimation().getX()) * Main.DW;
            y = ( p.getAnimation().getY()) * Main.DH - h + 20;
        }
        if(p.getAnimation() instanceof AnimationImage){
            AnimationImage ani = (AnimationImage) p.getAnimation();
            w = ani.getW()* Main.DW;
            h = ani.getH()* Main.DH;
            x = (p.getAnimation().getX()) * Main.DW;
            y = (p.getAnimation().getY()) * Main.DH;
        }
    }
    private int getTextWidth(String mes, Graphics g) {
        int len = 0;
        for(int i = 0; i < mes.length(); i ++){
            len += g.getFontMetrics().charWidth(mes.charAt(i));
        }
        return len + 10;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(x < e.getX() && e.getX() < x + w && y < e.getY() && e.getY() < y + h){
            data.action(-1);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
