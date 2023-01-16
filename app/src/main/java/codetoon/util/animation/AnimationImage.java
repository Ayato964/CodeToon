package codetoon.util.animation;

import codetoon.main.Main;

import java.awt.*;
import java.io.FileNotFoundException;
import java.security.KeyStore;

public class AnimationImage extends Animation{
    private ImageMaker maker;
    private  int w, h;
    protected AnimationImage(Graphics g) {
        super(g);
    }

    @Override
    public Animation draw() {
        g.drawImage(maker.get(), getX() * Main.DW, getY() * Main.DH, w * Main.DW, h * Main.DH, null);
        return this;
    }
    public Animation draw(String imageName, int x, int y, int w, int h){
        return draw(imageName, x, y, w, h, new Properties());
    }
    public Animation draw(String imageName, int x, int y, int w, int h, Properties p){
        super.setX(x);
        super.setY(y);
        this.w = w;
        this.h = h;
        maker = new ImageMaker(imageName);

        myProp = p;
        p.set(this, g);
        return draw();

    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
