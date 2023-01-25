package codetoon.util.animation;

import java.awt.*;
import java.util.function.BooleanSupplier;

public class Remove implements Decorate{
    BooleanSupplier bool;
    public Remove(BooleanSupplier sup){
        bool = sup;
    }
    @Override
    public void displayAction(Animation.Properties p, Graphics g) {
        if(bool.getAsBoolean()){
            p.animationTickRegistory.remove();
        }
    }
}
