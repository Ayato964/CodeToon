package codetoon.util.animation;

import java.awt.*;

public class Fade extends Decorate{
    private final double fadeIn;
    private  final  double fadeOut;
    private  double percent;
    private double inGage;
    private double outGage;
    private boolean isIn;
    double count = 0;
    public Fade(Animation.Properties properties, double fadeIn, double fadeOut) {
        super(properties);
        this.fadeIn = fadeIn * 100;
        this.fadeOut = fadeOut * 100;
        inGage  = 255 / (15 / fadeIn);
        outGage = 255 / (15 /fadeOut);
        isIn = true;
    }

    @Override
    public void displayAction(Graphics g) {
        count += 1;
        if(!isIn && 255 -count * outGage <= 0){
            count = 1;
            outGage = 255;
        }
        Color c = properties.getColor();
        properties.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), (int) (
                isIn == true ? count * inGage : 255 - count * outGage)));
        if(count * inGage >= 250){
            isIn = false;
            count = 0;
        }

    }
}
