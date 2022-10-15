package codetoon.util;

import java.awt.*;
import codetoon.main.*;

import javax.swing.plaf.ColorUIResource;
public class MyText {
    private static Font font = new Font("", Font.PLAIN, 32);
    private static Color c = new ColorUIResource(Color.WHITE); 
    public static void setText(String str, int x, int y, Color c, int size){
      Main.getMainGraphics().setColor(c);
      font = new Font("", Font.PLAIN, size);
      MyText.c = c;
      Main.getMainGraphics().setFont(font);
      Main.getMainGraphics().drawString(str, x, y);
    }
    public static void setText(String str, int x, int y, Color c, Font font){
        Main.getMainGraphics().setColor(c);
        MyText.font = font;
        MyText.c = c;
        Main.getMainGraphics().setFont(font);
        Main.getMainGraphics().drawString(str, x, y);
    }
    public static void setText(String str, int x, int y){
        Main.getMainGraphics().setColor(c);
        Main.getMainGraphics().setFont(font);
        Main.getMainGraphics().drawString(str, x, y);
    }
}
