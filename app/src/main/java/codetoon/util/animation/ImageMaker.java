package codetoon.util.animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageMaker {
    public Image subImage;
    public BufferedImage percentImage;
    public ImageMaker(String str){
        URL filePath =  getClass().getClassLoader().getResource("assets/codetoon/textures/" + str + ".png");
        try {
            percentImage = ImageIO.read(filePath);
            subImage = percentImage.getSubimage(0, 0, 120, 120);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public ImageMaker(String str, int w, int h){
        URL filePath =  getClass().getClassLoader().getResource("assets/codetoon/textures/" + str + ".png");
        try {
            percentImage = ImageIO.read(filePath);
            subImage = percentImage.getSubimage(0, 0, w, h);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Image get(){
        return subImage;
    }
}
