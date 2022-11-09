package codetoon.util.animation;

import org.checkerframework.checker.units.qual.A;

import java.awt.*;

public abstract class Decorate {
    protected final Animation.Properties properties;
    public Decorate(Animation.Properties properties){
        this.properties = properties;
    }
    public abstract void displayAction(Graphics g);
}
