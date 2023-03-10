package codetoon.util.animation;

import codetoon.system.CodeToon;
import codetoon.util.InputColorSup;

import java.awt.*;

public class AnimationColorChooser {
    InputColorSup target;
    String title;
    Graphics g;
    int x, y;
    AnimationsPack pack;
    boolean isStart = true;
    private AnimationColorChooser(Graphics g, String str, InputColorSup c, int x, int y){
        this.g = g;
        target = c;
        title = str;
        this.x = x;
        this.y = y;

    }
    private AnimationColorChooser addPack(AnimationsPack p){
        pack = p;
        return this;
    }

    public AnimationColorChooser isStart(boolean b) {
        isStart = b;
        return this;
    }

    public void show(){
        boolean b = pack == null;
                Animation t = Animation.create(g).draw(title, x, y, new Animation.Properties(isStart).font("", 0, 32).color(CodeToon.textColor));
                Animation r = Animation.create(g).draw("detail.text.color.r", x + 35, y, new Animation.Properties(isStart).font("", 0, 32).color(CodeToon.textColor));
                Animation rc = Animation.create(g).draw("" + target.getColor().getRed(), x + 40, y,
                        new Animation.Properties(isStart).font("", 0, 32).color(CodeToon.textColor)
                                .textArea(10, 6, CodeToon.frameColor, mes -> {
                                    Color c = new Color(Math.min(Integer.parseInt(mes), 255), target.getColor().getGreen(), target.getColor().getBlue());
                                    target.setColor(c);
                                })
                );
                Animation green = Animation.create(g).draw("detail.text.color.g", x + 50, y, new Animation.Properties(isStart).font("", 0, 32).color(CodeToon.textColor));
                Animation greenc = Animation.create(g).draw("" + target.getColor().getGreen(), x + 55, y,
                        new Animation.Properties(isStart).font("", 0, 32).color(CodeToon.textColor)
                                .textArea(10, 6, CodeToon.frameColor, mes -> {
                                    Color c = new Color(target.getColor().getRed(), Math.min(Integer.parseInt(mes), 255), target.getColor().getBlue());
                                    target.setColor(c);
                                })
                );
                Animation blue = Animation.create(g).draw("detail.text.color.b", x + 65, y, new Animation.Properties(isStart).font("", 0, 32).color(CodeToon.textColor));
                Animation bluec = Animation.create(g).draw("" + target.getColor().getBlue(), x + 70, y,
                        new Animation.Properties(isStart).font("", 0, 32).color(CodeToon.textColor)
                                .textArea(10, 6, CodeToon.frameColor, mes -> {
                                    Color c = new Color(target.getColor().getRed(), target.getColor().getGreen(), Math.min(Integer.parseInt(mes), 255));
                                    target.setColor(c);
                                })
                );
                if(!b) {
                    pack.add(t);
                    pack.add(r);
                    pack.add(rc);
                    pack.add(green);
                    pack.add(greenc);
                    pack.add(blue);
                    pack.add(bluec);
                }

    }
    public static AnimationColorChooser create(Graphics g, String str,  int x, int y,  InputColorSup c){
        return new AnimationColorChooser(g, str, c, x, y);
    }
    public static AnimationColorChooser create(AnimationsPack p, Graphics g, String str,  int x, int y, InputColorSup c){
        return new AnimationColorChooser(g, str, c, x, y).addPack(p);
    }
}
