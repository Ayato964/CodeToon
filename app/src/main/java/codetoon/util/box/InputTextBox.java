package codetoon.util.box;

import codetoon.main.Main;
import codetoon.util.ContainerData;
import codetoon.util.animation.Animation;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputTextBox extends Box implements KeyListener {
    private StringBuilder texts;
    private  int textCount;
    private Animation animation;
    private Graphics graphics;
    private ContainerBox box;
    private int myDataId;
    private ContainerData data;
    public InputTextBox(Graphics g, int x, int y, int wid, int height, Animation.Properties properties){
        super(x, y, wid, height);
        texts = new StringBuilder();
        graphics = g;
        animation = Animation.create(g);
        animation.draw(">",30, 30, properties);

    }
    public InputTextBox(Graphics g, Animation.Properties properties){
        super(0, 0, 0, 0);
        texts = new StringBuilder();
        graphics = g;
        animation = Animation.create(g);
        animation.draw(">",30, 30, properties);
    }

    @Override
    public void setSize(int x, int y, int width, int height) {
        animation.setY(y);
        super.setSize(animation.getX(), animation.getY() - height, width, height);
   //     System.out.println(x + "  " + y + "  " + width + "  " + height + "   ");
    }

    protected void add(@NotNull KeyEvent e){
        if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
            texts.deleteCharAt(textCount - 1);
            textCount --;
        }else if(e.getKeyChar() == KeyEvent.VK_ENTER){
            data.action(myDataId);
            Main.getInstance().removeKeyListener(this);
        }else{
            texts.append(e.getKeyChar());
            textCount ++;
        }
        animation.setMsg(texts.toString());
    }
    public String getString(){
        return texts.toString();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        add(e);

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void draw(int x, int y, int w, int h) {
        graphics.drawRect(animation.getX() * Main.DW, animation.getY() * Main.DH - h, w, h);
    }
    private void removeListenerAll(){
        KeyListener[] t = Main.getInstance().getKeyListeners();
        for(int i = 0; i < t.length; i ++){
            Main.getInstance().removeKeyListener(t[i]);
        }
    }

    @Override
    public void pressedMouse(ContainerBox box, ContainerData data, int i) {
        removeListenerAll();
        Main.getInstance().addKeyListener(this);

        this.data = data;
        this.box = box;
        myDataId = i;

    }
}
