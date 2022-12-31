package codetoon.util.animation;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Properties;

import codetoon.main.*;
import codetoon.system.CodeToon;
import codetoon.util.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

//import javax.swing.plaf.ColorUIResource;

public abstract class Animation {
    private int x, y;
    public Properties myProp;
    protected Graphics g;
    protected Animation(Graphics g){
        this.g = g;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public static void removeMouseListener(){
        MouseListener[] l = Main.getInstance().getMouseListeners();
        for(MouseListener ll : l){
            if(ll instanceof Button){
                Main.getInstance().removeMouseListener(ll);
            }
        }
    }

    @Contract("_ -> new")
    public static @NotNull AnimationText create(Graphics g) {
        return new AnimationText(g);
    }
    public static @NotNull AnimationImage createImage(Graphics g){
        return new AnimationImage(g);
    }
    public abstract void draw();

    public static class Properties implements IsTick {
        private Animation percent;
        private Properties child;
        private ArrayList<Decorate> prop;
        private Graphics g;
        private Font font;
        public TickRegistory<Properties> animationTickRegistory;
        private int count = 0;
        private boolean isEnd = false;
        public Properties(){
            prop = new ArrayList<>();
            font = new Font("Serif", 0, 12);
            animationTickRegistory = TickRegistory.createTickerAnimation(this, Properties::tick);
            child = null;
        }

        protected void set(Animation a, @NotNull Graphics g){
            percent = a;
            this.g = g;
            g.setFont(font);
            g.setColor(Color.white);
            for (int i = 0; i < prop.size(); i++) {
                prop.get(i).displayAction(this, g);
            }
        }

        public static <T extends IsTick> void tick(T t){

            Properties p = (Properties) t;


            Animation a = p == null ? null : p.percent;
            p.count ++;
            if(a != null) {
                p.set(a, p.g);
                p.percent.draw();
            }

        }
        public int setAllPosition(int x, int y){
            percent.x = x;
            percent.y = y;
            if(child != null){
                return y + child.setAllPosition(x, y + g.getFontMetrics().getHeight() / 4);
            }else{
                return y;
            }
        }
        public void setChild(Properties p){
            if(child == null) {
                child = p;
            }else{
                child.setChild(p);
            }
        }
        public Properties font(String font, int font_type, int size){
            prop.add(new DecorateFont(font, font_type, size));
            return this;
        }
        public Properties copy(Properties p){
            prop = p.prop;
            return this;
        }

        public Properties displayTime(int time){
            prop.add(new DisplayTime(time));
            return this;
        }
        public Properties frame(Color c){
            prop.add(new Frame(c));
            return this;
        }
        public Properties frame(Color c, IsBoolInterface b){
            prop.add(new Frame(c, b));
            return this;
        }
        public Properties frame(Color c, int w, int h, IsBoolInterface b){
            prop.add(new Frame(c, w, h, b));
            return this;
        }
        public Properties button(Action data){
            prop.add(new Button(data));
            return this;
        }

        public Properties fade(double in, double out){
            prop.add(new Fade(in, out));
            return this;
        }

        public Properties size(int size){
            prop.add(new DecorateFont(size));
            return this;
        }
        public Properties center(){
            prop.add(new Center());
            return this;
        }
        public Properties changeArgument(StringSup sup){
            prop.add(new ChangeArgument(sup));
            return this;
        }
        public Properties color(Color c){
            prop.add(new DisplayColor(c));
            return this;
        }
        public Properties endPosition(int x, int y, int type){
            prop.add(new EndPosition(x, y, type));
            return this;
        }
        public Properties setWidth(int w){
            prop.add(new Width(this, w));
            return this;
        }
        public int getCount() {
            return count;
        }

        public boolean isEnd() {
            return isEnd;
        }
        public TickRegistory<Properties> getAnimationTickRegistory() {
            return animationTickRegistory;
        }
        public void setIsEnd(boolean b){
            isEnd = b;
        }
        public Animation getAnimation(){
            return percent;
        }

        public Font getFont() {
            return font;
        }

        @Override
        public boolean isClient() {
            return true;
        }

    }

}
