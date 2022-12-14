package codetoon.system;

import codetoon.main.Main;
import codetoon.util.Tick;
import codetoon.util.TickRegistory;
import codetoon.util.animation.Animation;
import codetoon.util.animation.AnimationText;
import codetoon.util.animation.EndPosition;

import java.awt.*;
import java.util.ArrayList;

public class Message {

    private int x, y, w, h;
    ArrayList<Animation> animations;
    private Graphics graphics;
    private static Message instance;
    public Message(Graphics g, int x, int y, int w, int h){
        graphics = g;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        animations = new ArrayList<>();
        instance = this;
        addMessage("ここに、プログラムの結果が抽出されます。", Color.BLACK);
    }
    public void draw(){
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x * Main.DW, y * Main.DH, w * Main.DW, h * Main.DH);
    }
    public static void addMessage(String str){
        addMessage(null, str);
    }
    public static void addMessage(String[] values, String str){
        addMessage(values, str, Color.BLACK);
    }


    public static void addMessage(String str, Color c){
        addMessage(null, str, c);
    }
    public static void addMessage(String[] values, String str, Color c){
        instance.shiftMessage();

        AnimationText a = Animation.create(instance.graphics);
        a.draw(values, str, instance.x, instance.y + instance.h,
                new Animation.Properties()
                        .size(20)
                        .color(c)
                        .endPosition(instance.x, instance.y, EndPosition.UNDER)
                        .setWidth(instance.w));
        instance.animations.add(a);
    }
    private void shiftMessage() {
        if(!animations.isEmpty()) {

            for (int i = 0; i < animations.size(); i++) {
                Animation a = animations.get(i);
                a.myProp.setAllPosition(a.getX(), a.getY() - graphics.getFontMetrics().getHeight() / 4);

            }
        }
    }
}
