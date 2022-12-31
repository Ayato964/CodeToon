package codetoon.util.animation;

import codetoon.util.StringSup;

import java.awt.*;

public class ChangeArgument implements Decorate{
    StringSup sup;
    public ChangeArgument(StringSup sup){
        this.sup = sup;
    }
    @Override
    public void displayAction(Animation.Properties p, Graphics g) {
        AnimationText text = (AnimationText) p.getAnimation();
        text.setVariable(sup.StringSup());
    }
}
