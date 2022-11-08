package codetoon.util.animation;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import codetoon.main.*;
import codetoon.system.CodeToon;
import codetoon.util.IsTick;
import codetoon.util.TickRegistory;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

//import javax.swing.plaf.ColorUIResource;

public class Animation {
    //private Color c = new ColorUIResource(Color.WHITE);
    private int x, y;
    private String msg;
    private Graphics graphics;

    private static final int STRING_TYPE = 120120120;
    private static final int IMAGE_TYPE = 1211211210;
    protected int TYPE;
    private Animation(){
    }
    private Animation(Graphics g){
        graphics = g;
    }
    public  void draw(String str, int x, int y){
        draw(str, x, y, new Properties());
    }
    public  void draw(String str, int x, int y, @NotNull Properties properties){
        this.x = x;
        this.y = y;
        msg = str;
        TYPE = STRING_TYPE;

        properties.set(this, graphics);
        graphics.drawString(str, x * Main.DW, y * Main.DH);
    }
    public void draw(Image image, int x, int y){

    }
    @Contract("_ -> new")
    public static @NotNull Animation create(Graphics g) {
        return new Animation(g);
    }

    public static class Properties implements IsTick {
        private Animation percent;
        private Graphics g;
        private Font font;
        private int displayTime;
        private int Fadein, Fadeout;
        private String fontName;
        private int fontStyle;
        public TickRegistory<Properties> animationTickRegistory;
        public int fontSize;
        private int count = 0;
        private boolean isEnd = false;
        public Properties(){
            displayTime = CodeToon.INFINITY;
            Fadein = CodeToon.INFINITY;
            Fadeout = CodeToon.INFINITY;
            animationTickRegistory = null;
            fontSize = 15;
            fontName = "";
            fontStyle = Font.PLAIN;
            font = new Font(fontName, fontStyle, fontSize);
            animationTickRegistory = TickRegistory.createTickerAnimation(this, Properties::tick);
        }
        protected void set(Animation a, @NotNull Graphics g){
            percent = a;
            this.g = g;
            font = new Font(fontName, fontStyle, fontSize);
            g.setFont(font);
        }

        public static <T extends IsTick> void tick(T t){
            Properties p = (Properties) t;
            Animation a = p.percent;
            p.count ++;
            p.displayAction();
            if(p.percent.TYPE == Animation.STRING_TYPE && !p.isEnd){
                p.percent.draw(a.msg, a.x, a.y, p);

            }
        }

        private void displayAction(){
            if(count / 50 >= displayTime && displayTime != CodeToon.INFINITY && !isEnd){
                animationTickRegistory.remove();
                isEnd = true;
            }
        }

        public Properties font(String font, int font_type){
            fontName = font;
            fontStyle = font_type;
            return this;
        }

        public Properties displayTime(int time){
            displayTime = time;
            return this;
        }

        public Properties Fade(int in, int out){

            Fadein = in;
            Fadeout = out;
            return this;
        }

        public Properties size(int size){
            fontSize = size;
            return this;
        }

        @Override
        public boolean isClient() {
            return true;
        }

    }

}
