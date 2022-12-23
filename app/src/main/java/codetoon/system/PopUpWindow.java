package codetoon.system;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.event.MouseMotionListener;


import codetoon.main.Main;

public class PopUpWindow implements MouseMotionListener{
    public static PopUpWindow popUpWindow= new PopUpWindow();
    int w = 250;
    int h = 70;
    Memory displayMemory;
    Color rectColor = new Color(0, 255, 50, 70);
    Color fontColor = new Color(0, 0, 0);
    Font font = new Font(Font.SERIF, Font.PLAIN, 12);
    public PopUpWindow(){
        super();
        Main.getInstance().addMouseMotionListener(this);
    }

    public void drawPopUpWindow(){
        Graphics g = Main.getMainGraphics();
        if(displayMemory != null && displayMemory.states != EnumMemoryStates.NONE){
            g.setFont(font);
            if(displayMemory.states == EnumMemoryStates.HACKED){
                g.setColor(rectColor);
                g.fillRect(displayMemory.x-30, displayMemory.y-h-10, w, h);
                g.setColor(fontColor);
                g.drawString("This memory was hacked", displayMemory.x-25, displayMemory.y-h+10);
            }else if(displayMemory.getSource() != null){
                g.setColor(rectColor);
                g.fillRect(displayMemory.x-30, displayMemory.y-h-10, w, h);
                g.fillPolygon(new int [] {displayMemory.x + 30,displayMemory.x+60, displayMemory.x+45  }, new int [] {displayMemory.y-10,displayMemory.y-10, displayMemory.y}, 3);
                if(displayMemory != null && displayMemory.getSource() != null ){
                    g.setColor(fontColor);
                    g.drawString(displayMemory.getSource().toString(), displayMemory.x-25, displayMemory.y-h+10);
                }
            }
        }
    }

    public boolean mouseInBox(Point p, Memory memory){
        return p.getX() > memory.x && p.getX() < memory.x + memory.w && p.getY() > memory.y && p.getY() < memory.y + memory.h;
    }

    public void mouseMoved(MouseEvent e){
        boolean isMemory = false;
        for(int i = 0; i < Memories.memory.size(); i++){
            if(mouseInBox(e.getPoint(), Memories.memory.get(i))){
                displayMemory = Memories.memory.get(i);
                isMemory = true;
            }
        }
        if(!isMemory){
            displayMemory = null;
        }
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }
}