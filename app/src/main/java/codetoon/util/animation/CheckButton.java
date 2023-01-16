package codetoon.util.animation;
import codetoon.main.Main;
import codetoon.util.Action;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CheckButton implements Decorate, MouseListener {
    Action t, f;
    int pixW, pixH, x, y;
    boolean isChecked;
    public CheckButton(Action thisTrue, Action thisFalse){
        t = thisTrue;
        f = thisFalse;
        isChecked = false;
        Main.getInstance().addMouseListener(this);
    }
    @Override
    public void displayAction(Animation.Properties p, Graphics g) {
        AnimationText text = (AnimationText) p.getAnimation();
        pixW = g.getFontMetrics().charWidth(text.getMsg().charAt(0));
        pixH = g.getFontMetrics().getHeight();
        x = text.getX() * Main.DW - pixW;
        y = text.getY() * Main.DH- pixH;
        if (!isChecked) {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, pixW, pixH);
            g.setColor(Color.WHITE);
            g.drawRect(x, y, pixW, pixH);

        }else {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, pixW, pixH);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, pixW, pixH);
            g.setColor(Color.WHITE);
        }


    }

    @Override
    public void mouseClicked(MouseEvent m) {
        if(x < m.getX() && x + pixW > m.getX() && y < m.getY() && y + pixH > m.getY()){
            isChecked = !isChecked;
            if(isChecked)
                t.action(-1);
            else
                f.action(-1);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
