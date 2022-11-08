package codetoon.util;

import java.awt.event.MouseEvent;
import java.util.*;
import codetoon.main.*;

import javax.swing.event.MouseInputListener;

public class ContainerBox implements Container<Box>, MouseInputListener {
    int x, y, width, height;
    ArrayList<Box> box;
    ContainerData<Box, Integer> data;
    public ContainerBox(int x, int y, int width, int height, ContainerData<Box, Integer> data){
      box = new ArrayList<Box>();
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.data = data;
      for(int i = 0; i < data.getCount(); i ++){
        add(data.set(i));
      }

        Main.getInstance().addMouseListener(this);

    }  
    @Override
    public void add(Box b){
      box.add(b);
    }
    @Override 
    public void set(Box b, int i){
      box.set(i, b);
    }
    @Override
    public void draw(){
      for(int i = 0; i < box.size(); i ++){
        box.get(i).setSize(x, y + (height / box.size()) * i , width, height / box.size());
        box.get(i).draw();
      }
    }
    @Override
    public void remove(int i){
      
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i = 0; i < box.size(); i ++){
            if(box.get(i).mouseInBox(e.getPoint())){
                data.action(i);
                Main.getInstance().removeMouseListener(this);
            }
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
    @Override
    public void mouseDragged(MouseEvent e) {
        
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
}
