package codetoon.util.box;

import codetoon.main.Main;
import codetoon.util.ContainerData;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
@Deprecated(since = "1.7.0")
public abstract class Box {
    private int x, y, w, h;

    public Box(int _x, int _y, int _w, int _h){
        x = _x;
        y = _y;
        w = _w;
        h = _h;

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
    public boolean mouseInBox(@NotNull Point p){
        return p.getX() > x && p.getX() < x + w && p.getY() > y && p.getY() < y + h;
    }
    public void draw(){
        draw(x, y, w, h);
    }
    public abstract void draw(int x, int y, int w, int h);
    public abstract void pressedMouse(ContainerBox box, ContainerData data, int i);
}
