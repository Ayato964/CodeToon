package codetoon.util.animation.image;

import codetoon.util.animation.Animation;
import codetoon.util.animation.AnimationImage;
import codetoon.util.animation.Decorate;

import java.awt.*;

public abstract class AbstractImageDecorate implements Decorate {
    @Override
    public void displayAction(Animation.Properties p, Graphics g) {
        displayAction((AnimationImage.PropertiesImage) p, g);
    }
    abstract void displayAction(AnimationImage.PropertiesImage p, Graphics g);
}
