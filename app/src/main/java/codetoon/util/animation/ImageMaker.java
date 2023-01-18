package codetoon.util.animation;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageMaker {
    public Image subImage;
    public Image percentImage;
    public ImageMaker(String str){
        URL filePath =  getClass().getClassLoader().getResource("assets/codetoon/textures/" + str + ".png");
        ImageIcon icon = new ImageIcon(filePath);
        percentImage = icon.getImage();
        subImage = percentImage;

    }
    public Image get(){
        return subImage;
    }
}
