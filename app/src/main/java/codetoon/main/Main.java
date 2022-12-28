package codetoon.main;

import javax.swing.*;
import codetoon.map.*;
import codetoon.map.Map;
import codetoon.util.Tick;
import codetoon.util.animation.Animation;
import codetoon.util.lang.LangLoader;

import java.awt.event.MouseListener;
import java.awt.image.*;
import java.awt.*;
import java.net.UnknownHostException;

public class Main extends JFrame{
    protected static Main main;
    public Map displayMap;
    public static String NAME;
    private final MainPanel panel;
    public static int DW;
    public static int DH;
    public static Rectangle DESCTOP_BOUNDS;
    private static  Graphics g;
    public Main(String title){
        //JOptionPane.showMessageDialog(new JFrame(), getClass().getClassLoader().getResource("assets/").toString());
        setTitle(title);
        this.setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        NAME = title;
        main = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DESCTOP_BOUNDS = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        DW = (int) DESCTOP_BOUNDS.getWidth() / 198;
        DH =(int) DESCTOP_BOUNDS.getHeight() / 108;
        panel = new MainPanel();
        add("Center", panel);
        pack();
        g = panel.getGraphics();



        repaint();
    }
    public static Main getInstance(){
        return Main.main; 
    }
    public static  Graphics getMainGraphics(){
        return g;
    }
    /** Main Method !!! **/
    public static void main(String[] args) {
       // JOptionPane.showMessageDialog(new JFrame(), "Say");
        LangLoader.create("ja_jp");
        System.out.println();
        Main m = new Main("CodeToon");
        m.setVisible(true);
        m.run(new Title());
        //Tick.getInstance();
       // System.out.println("Tick Loaded");
    }

    public void run(Map map){
        g.clearRect(0, 0, getWidth(), getHeight());
        Animation.removeMouseListener();

        Tick.getInstance().removeAllAnimation();
        displayMap = map;
        try {
            displayMap.setup(g);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        displayMap.display(g);
        repaint();
    }
    public Map getMap(){
        return displayMap;
    }
    private class MainPanel extends JLabel{
        BufferedImage image;
        final Graphics hackingPazzle;
        public MainPanel(){
            image = new BufferedImage((int) DESCTOP_BOUNDS.getWidth(),(int) DESCTOP_BOUNDS.getHeight(), BufferedImage.TYPE_INT_RGB);
            this.setIcon(new ImageIcon(image));
            hackingPazzle = image.createGraphics();
            repaint();
        }
        public Graphics getGraphics(){
            return hackingPazzle;
        }

    }
}