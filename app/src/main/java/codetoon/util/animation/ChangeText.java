package codetoon.util.animation;

import codetoon.util.lang.LangLoader;
import java.awt.*;
import java.util.function.BooleanSupplier;

public class ChangeText implements Decorate{
    String text;
    String[] argument;
    BooleanSupplier bool;
    public ChangeText(String text, BooleanSupplier isBool){
        this(null, text, isBool);
    }
    public ChangeText(String[] a, String text, BooleanSupplier isBool){
        this.text = LangLoader.getInstance().get(a, text);
        argument = a;
        bool = isBool;
    }
    @Override
    public void displayAction(Animation.Properties p, Graphics g) {
        if(bool.getAsBoolean()){
            AnimationText text =(AnimationText) p.getAnimation();
            text.setMsg(this.text);
        }
    }
}
