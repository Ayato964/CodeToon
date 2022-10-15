package codetoon.util;

import codetoon.main.*;
import java.awt.*;
public class Box{
    private String mes;
    private Font font;
    private Color bg;
    private int x, y, w, h;
    private Graphics g;
    public Box(String s){
        g = Main.getMainGraphics();
      mes = s;
      font = new Font("", Font.PLAIN, 32);
      bg = Color.WHITE;
    }
    public Box(String s,int x, int y, int width, int height){
      mes = s;  
      this.x = x * Main.DW;
      this.y = y * Main.DH;
      w = width * Main.DW;
      h = height * Main.DH;
    
    }
  
    public void setPosition(int x, int y){
      this.x = x * Main.DW;
      this.y = y * Main.DH;
      
    }
    public void setSize(int x, int y, int width, int height){
      this.x = x * Main.DW;
      this.y = y * Main.DH;
      w = width * Main.DW;
      h = height * Main.DH;
    }
    public void draw(){
        g.setColor(bg);
        g.drawRect(x, y, w, h);
        MyText.setText(mes, x, y + h / 2, Color.WHITE, font);
     
    }
    public boolean mouseInBox(Point p){
        return p.getX() > x && p.getX() < x + w && p.getY() > y && p.getY() < y + h; 
    }
  }