package codetoon.util;

import codetoon.main.*;
import codetoon.util.animation.Animation;
import org.jetbrains.annotations.NotNull;

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
        Animation.create(g).draw(mes, x / Main.DW, y / Main.DH + (h / Main.DH) / 2,
                new Animation.Properties()
                        .size(30)
        );
     
    }
    public boolean mouseInBox(@NotNull Point p){
        return p.getX() > x && p.getX() < x + w && p.getY() > y && p.getY() < y + h; 
    }
  }