package codetoon.util.animation.image;

import codetoon.util.animation.Animation;
import codetoon.util.animation.AnimationImage;
import codetoon.util.animation.Decorate;

import java.awt.*;

public class ImageAnimation extends AbstractImageDecorate{
    int changeTick = 1;
    int tick = 0;
    int piY = 0;
    @Override
    void displayAction(AnimationImage.PropertiesImage p, Graphics g) {
        int piHeight = p.getAnimation().maker.percentImage.getHeight();
        boolean changed = false;
        tick ++;
        if(tick >= changeTick) {
            tick = 0;
            if(piY + p.h < piHeight)
               piY += p.h;
            else
                piY = 0;
            changed = true;
        }
        if(changed)
            p.getAnimation().maker.subImage = p.getAnimation().maker.percentImage.getSubimage(0, piY, p.w, p.h);
    }
}
