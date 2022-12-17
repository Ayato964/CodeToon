package codetoon.system;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.event.MouseMotionListener;


import codetoon.main.Main;

public class PopUpWindow implements MouseMotionListener{
    public static PopUpWindow popUpWindow= new PopUpWindow();
    int w = 100;
    int h = 50;
    Memory displayMemory;
    Color rectColor = new Color(0, 255, 50, 50);

    public PopUpWindow(){
        super();
        Main.getInstance().addMouseMotionListener(this);
    }

    public void drawPopUpWindow(){
        Graphics g = Main.getMainGraphics();
        if(displayMemory != null){
            g.setColor(rectColor);
            g.fillRect(displayMemory.x, displayMemory.y, w, h);
            if(displayMemory.getSource() != null){
                g.drawString(displayMemory.getSource().toString(), displayMemory.x, displayMemory.y);
            }
        }
    }

    public boolean mouseInBox(Point p, Memory memory){
        return p.getX() > memory.x && p.getX() < memory.x + memory.w && p.getY() > memory.y && p.getY() < memory.y + memory.h;
    }

    public void mouseMoved(MouseEvent e){
        for(int i = 0; i < Memories.memory.size(); i++){
            if(mouseInBox(e.getPoint(), Memories.memory.get(i))){
                displayMemory = Memories.memory.get(i);
            }
        }
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }
}