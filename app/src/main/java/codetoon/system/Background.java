package codetoon.system;

import codetoon.main.Main;
import codetoon.util.Display;
import codetoon.util.animation.ImageMaker;

import java.awt.*;

public class Background implements Display {
    private static final Background instance = new Background();
    private ImageMaker bgImage;

    private Background(){
        bgImage = new ImageMaker("title/background", 256, 144);
    }

    public static Background getInstance() {
        return instance;
    }

    @Override
    public void display(Graphics g) {
        Rectangle r = Main.DESCTOP_BOUNDS;
        g.drawImage(bgImage.get(), 0, 0, Main.getInstance().getWidth(), Main.getInstance().getHeight(), null);
    }
}
