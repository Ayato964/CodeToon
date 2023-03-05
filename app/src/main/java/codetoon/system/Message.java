package codetoon.system;

import codetoon.main.Main;
import codetoon.method.Methods;
import codetoon.method.MyMethod;
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
    ArrayList<MyMethod> met = new ArrayList<>();
    ArrayList<MyMethod> blackList = getBlackList();

    private int popCount = 0;

    private boolean isViewMessage = true;
    private Graphics graphics;
    private static Message instance;
    private ArrayList<MyMethod> getBlackList() {
        ArrayList<MyMethod> black = new ArrayList<>();
        black.add(Methods.Attack.get());
        black.add(Methods.SETPASS.get());
        black.add(Methods.RECOVERY.get());
        return black;
    }
    public Message(Graphics g, int x, int y, int w, int h){
        graphics = g;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        animations = new ArrayList<>();
        instance = this;
    }
    public static void popMessage(ArrayList<MyMethod> method){
        instance.met = method;
        instance.popCount --;
        if(instance.popCount == 0)
            instance.isViewMessage = false;
    }
    public static void pushMessage(){

        instance.isViewMessage = true;
        instance.popCount ++;
    }
    private boolean isPop(){
        if(!isViewMessage && !met.isEmpty()){
            for(int i = 0; i < met.size(); i ++){
                for (int c = 0; c < blackList.size(); c ++){
                    if(met.get(i).getClass() == blackList.get(c).getClass()){
                        return false;
                    }
                }
            }
        }
        return true;
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
        if(instance.isPop()) {
            instance.shiftMessage();
            AnimationText a = Animation.create(instance.graphics);
            a.draw(values, str, instance.x + 2, instance.y + instance.h - 1,
                    new Animation.Properties()
                            .size(20)
                            .color(c)
                            .endPosition(instance.x + 2, instance.y, EndPosition.UNDER)
                            .setWidth(instance.w));
            instance.animations.add(a);
        }
    }
    public static void shiftMessage() {
        if(!instance.animations.isEmpty()) {
            for (int i = 0; i < instance.animations.size(); i++) {
                Animation a = instance.animations.get(i);
                a.myProp.setAllPosition(a.getX(), a.getY() - instance.graphics.getFontMetrics().getHeight() / 4);
            }
        }
    }
}
