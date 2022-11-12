package codetoon.map;

import codetoon.main.Main;
import codetoon.util.animation.Animation;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.UnknownHostException;

public class JoinServer extends Map implements KeyListener {
    StringBuilder adress;
    Animation animationAdress;
    Graphics g;
    @Override
    public void setup(Graphics g){
        adress = new StringBuilder();
        this.g = g;
        Animation.create(g).draw("セッションID（相手のIPアドレス）を入力してください。", 0, 20,
                new Animation.Properties()
                        .center()
                        .size(40));
        animationAdress = Animation.create(g);
        animationAdress.draw(">", 0, 40, new Animation.Properties().size(40).center());
        Main.getInstance().addKeyListener(this);
    }

    @Override
    public void display(Graphics g) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        addAdress(keyEvent);
    }

    private void addAdress(KeyEvent keyChar) {
        if(keyChar.getKeyChar() == KeyEvent.VK_BACK_SPACE){
            adress.deleteCharAt(adress.length() - 1);
        }else {
            adress.append(keyChar.getKeyChar());
        }
        animationAdress.setMsg(adress.toString());
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {


    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
