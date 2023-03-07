package codetoon.util.animation;

import java.awt.*;

public class DecorateFont implements Decorate{
    private String font;
    private int fontStyle;
    private int size;
    public   Font f;
    public DecorateFont(String str, int font_style, int size){
        font = str;
        fontStyle = font_style;
        this.size = size;
        f = new Font(font, fontStyle, size);
    }
    public DecorateFont(int size){
        f = new Font("Serif", Font.PLAIN, size);
    }
    @Override
    public void displayAction(Animation.Properties p, Graphics g) {
        g.setFont(f);
    }
}
