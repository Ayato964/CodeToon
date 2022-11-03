package codetoon.util.animation;

import java.awt.*;

import codetoon.main.*;
import codetoon.system.CodeToon;
import codetoon.util.IsTick;
import codetoon.util.TickRegistory;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.plaf.ColorUIResource;

public class Animation {
    private Color c = new ColorUIResource(Color.WHITE);
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
    public int getWidth(int[] widths){
        int c = 0;
        for(int i = 0; i < widths.length; i ++){
            c += widths[i];
        }
        return c;
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
        }
        protected void set(Animation a, @NotNull Graphics g){
            percent = a;
            this.g = g;
            g.setFont(new Font(fontName, fontStyle, fontSize));

        }
        public static <T extends IsTick> void tick(T t){
            Properties p = (Properties) t;
            Animation a = p.percent;
            p.g.clearRect(a.x, a.y, a.getWidth(a.graphics.getFontMetrics().getWidths()) * a.msg.length(),
                    a.graphics.getFontMetrics().getHeight());

            p.count ++;
            p.displayAction();

            if(p.percent.TYPE == Animation.STRING_TYPE && !p.isEnd){
                p.percent.draw(a.msg, a.x, a.y, p);
            }else {
                Main.getInstance().repaint();
            }
        }
        private void displayAction(){
            System.out.println(count / 1000);
            if(count / 1000 >= displayTime){
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
            if(animationTickRegistory == null)
              animationTickRegistory = TickRegistory.createTickerAnimation(this, Properties::tick);
            displayTime = time;
            return this;
        }
        public Properties Fade(int in, int out){
            if(animationTickRegistory == null)
                animationTickRegistory = TickRegistory.createTickerAnimation(this, Properties::tick);

            Fadein = in;
            Fadeout = out;
            return this;
        }
        public Properties size(int size){
            fontSize = size;
            return this;
        }

        @Override
        public boolean isCliant() {
            return true;
        }

        @Override
        public void setCliantStates(boolean b) {

        }
    }

}
