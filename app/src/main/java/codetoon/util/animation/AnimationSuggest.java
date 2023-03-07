package codetoon.util.animation;

import codetoon.main.Main;
import codetoon.system.CodeToon;
import codetoon.util.lang.MouseAction;
import org.python.compiler.Code;

import java.awt.*;
import java.util.ArrayList;

public class AnimationSuggest {
    private ArrayList<Animation> list;
    private ArrayList<MouseAction> action;

    int x, y, width, count;
    private Graphics g;
    private Font f;


    private AnimationSuggest(Graphics g, int x, int y, int w, int count, int s){
        this.x = x;
        this.y = y;
        width = w;
        this.count = count;
        list = new ArrayList<>();
        action = new ArrayList<>();
        this.g = g;
        f = new Font("", Font.PLAIN, s);
    }
    public static AnimationSuggest create(Graphics g,int x, int y, int w, int count, int size){
        return new AnimationSuggest(g, x, y, w, count, size);
    }
    public AnimationSuggest add(String str, Animation.Properties properties, MouseAction a){
        return add(null, str, properties, a);
    }
    public AnimationSuggest add(String[] s, String str, Animation.Properties properties, MouseAction a){
        properties.stop();
        list.add(Animation.create(g).draw(s, str, 0, 0, properties));
        action.add(a);
        return this;
    }
    public void replaceProperties(Animation.Properties p, Decorate d){
        int c = p.searchProp(d);
        if(c != -1)
            p.prop.remove(c);
        p.prop.add(0, d);
    }
    public void show(){
        int s = display(0);

    }
    private int display(int start){
        int s = 0;
        for(int i = start; i < count && list.get(i) != null; i ++){
            list.get(i).setX(x);
            list.get(i).setY(y + s);
            list.get(i).myProp.start();
            final int click = i;
            list.get(i).myProp.prop.add(new Button(e -> {
                action.get(click).action(e);
                end();
            }));
            g.setFont(f);
            s += g.getFontMetrics().getHeight() / Main.DH;
        }
        for(int i = start; i < count && list.get(i) != null; i ++){
            replaceProperties(list.get(i).myProp, new CategoryBackground(CodeToon.categoryBg, list.get(i).getX(), list.get(i).getY(), width, -1));
            replaceProperties(list.get(i).myProp, new DecorateFont(f.getFontName(), 0, f.getSize()));
        }
        return s;
    }
    public void end(){
        for(Animation a : list){
            a.myProp.stop();
        }
    }

}
