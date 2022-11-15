package codetoon.system;

import codetoon.main.Main;
import codetoon.util.Tick;
import codetoon.util.TickRegistory;
import codetoon.util.animation.Animation;
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
    }
    public void draw(){
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x * Main.DW, y * Main.DH, w * Main.DW, h * Main.DH);
    }
    public static void addMessage(String str){
        instance.shiftMessage();

        Animation a = Animation.create(instance.graphics);
        instance.animations.add(a);
        a.draw(str, instance.x, instance.y + instance.h,
                new Animation.Properties()
                        .size(30)
                        .endPosition(instance.x, instance.y, EndPosition.UNDER)
                        .setWidth(instance.w));
    }



    public static void addMessage(String str, Color c){
        instance.shiftMessage();

        Animation a = Animation.create(instance.graphics);
        a.draw(str, instance.x, instance.y + instance.h,
                new Animation.Properties()
                        .size(30)
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
