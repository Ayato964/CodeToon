package codetoon.util.animation;

import codetoon.main.Main;
import codetoon.util.Action;
import codetoon.util.ActionString;
import org.checkerframework.checker.units.qual.A;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TextArea implements KeyListener, MouseListener, Decorate {

    private int x, y, w, h;
    Color fc;
    private  boolean change;
    private  boolean beforeChange;
    private StringBuilder mes;
    private ActionString comp;
    public TextArea(int w, int h, Color frameColor, ActionString complete){
        comp = complete;
        this.w = w * Main.DW;
        this.h = h * Main.DH;
        fc = frameColor;
        change = false;
        beforeChange = false;
        mes = new StringBuilder();
        Main.getInstance().addMouseListener(this);
    }
    @Override
    public void displayAction(Animation.Properties p, Graphics g) {
        AnimationText animation = (AnimationText) p.getAnimation();
        isChanged();
        int textH = g.getFontMetrics().getHeight() - 10;
        x = animation.getX() * Main.DW;
        y = animation.getY() * Main.DH - textH;
        g.setColor(fc);
        g.drawRect(x, y, w , h);
        if(change){
            animation.setMsg(mes.toString());
        }
    }
    private void isChanged(){
        if(change != beforeChange){
            beforeChange = change;
            if(change)
                Main.getInstance().addKeyListener(this);
            else {
                Main.getInstance().removeKeyListener(this);
                comp.action(mes.toString());
            }
        }
    }
    boolean temp = true;
    @Override
    public void keyTyped(KeyEvent e) {
        if(temp) {
            mes.append(e.getKeyChar());
        }else {
            temp = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            change = false;
            temp = false;
    }
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            mes.delete(mes.length() - 1, mes.length());
            temp = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(x < e.getX() && x + w > e.getX() &&  y < e.getY() && y + h > e.getY()){
            change = true;
        }else{
            change = false;
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
