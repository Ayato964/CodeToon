package codetoon.util.animation;

import codetoon.util.MessageSup;
import codetoon.util.function.StringSup;
import codetoon.util.lang.LangLoader;
import java.awt.*;
import java.util.function.BooleanSupplier;

public class ChangeText implements Decorate{
    MessageSup text;
    String[] argument;

    BooleanSupplier bool;
    public ChangeText(MessageSup text, BooleanSupplier isBool){
        this(null, text, isBool);
    }
    public ChangeText(String[] a, MessageSup text, BooleanSupplier isBool){
        this.text = text;
        argument = a;
        bool = isBool;
    }
    @Override
    public void displayAction(Animation.Properties p, Graphics g) {
        if(bool.getAsBoolean()){
            AnimationText text =(AnimationText) p.getAnimation();
            text.setMsg(argument, this.text.get());
        }
    }
}
