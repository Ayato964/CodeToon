package codetoon.util.animation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;

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
