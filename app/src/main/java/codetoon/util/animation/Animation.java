package codetoon.util.animation;

import java.awt.*;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import codetoon.main.*;
import codetoon.util.*;
import codetoon.util.function.IsBoolInterface;
import codetoon.util.function.StringSup;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

//import javax.swing.plaf.ColorUIResource;

public abstract class Animation implements Serializable {
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
            if(ll instanceof Button || ll instanceof CheckButton){
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
    public abstract Animation draw();

    public static class Properties implements IsTick {
        private Animation percent;
        private Properties child;
        protected ArrayList<Decorate> prop;
        private Graphics g;
        private Font font;
        public TickRegistory<Properties> animationTickRegistory;
        private int count = 0;
        private boolean isEnd = false;
        private boolean isStart = true;
        private BooleanSupplier bool;
        public Properties(){
            this(true);
        }
        public Properties(boolean isStart){
            this.isStart = isStart;
            prop = new ArrayList<>();
            font = new Font("Serif", 0, 12);
            animationTickRegistory = TickRegistory.createTickerAnimation(this, Properties::tick);
            child = null;

        }
        public void start(){
            isStart = true;
        }
        public void stop(){
            isStart = false;
        }

        public boolean isStart() {
            return isStart;
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
            if(p.bool != null)
                if(p.bool.getAsBoolean())
                    p.start();
                else
                    p.stop();
            if(p.isStart) {
                Animation a = p == null ? null : p.percent;
                p.count++;
                if (a != null) {
                    p.set(a, p.g);
                    p.percent.draw();
                }
            }

        }
        public void removeChild(){
            if(child != null)
                child.removeAll();
        }
        public void removeAll(){
            animationTickRegistory.remove();
            if(child != null)
                child.removeAll();
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
        public Properties checkBox(Action t, Action f){
            prop.add(new CheckButton(t, f));
            return this;
        }

        public Properties displayTime(int time){
            prop.add(new DisplayTime(time));
            return this;
        }
        public Properties drawIf(BooleanSupplier b){
            bool = b;
            return this;
        }
        public Properties textArea(int w, int h, Color frameColor, ActionString complete){
            prop.add(new TextArea(w, h, frameColor, complete));
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
        public Properties frame(Color c,int x, int y, int w, int h, IsBoolInterface b){
            prop.add(new Frame(c, x, y, w, h, b));
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
            prop.add(new Width(Width.UPPER, w));
            return this;
        }
        /*
        public Properties setWidth(int layout, int w){
            prop.add(new Width(layout, w));
            return  this;
        }

         */
        public Properties setChangeText(String text, BooleanSupplier sup){
            prop.add(new ChangeText(text, sup));
            return this;
        }
        public Properties setChangeText(String[] str, String text, BooleanSupplier sup){
            prop.add(new ChangeText(str, text, sup));
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

        public Properties remove(BooleanSupplier sup) {
            prop.add(new Remove(sup));
            return this;
        }
    }

}
