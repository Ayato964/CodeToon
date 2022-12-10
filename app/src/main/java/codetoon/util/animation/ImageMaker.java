package codetoon.util.animation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;

public class ImageMaker {
    public Image subImage;
    public Image percentImage;
    public ImageMaker(String str){
        try {
            File f2 = new File(getClass().getResource("/assets/codetoon/textures/" + str + ".png").toURI());
            System.out.println(f2.canRead());
            percentImage = ImageIO.read(f2);
            subImage = percentImage;
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Image get(){
        return subImage;
    }
}
