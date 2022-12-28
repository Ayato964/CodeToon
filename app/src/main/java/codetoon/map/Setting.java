package codetoon.map;

import codetoon.main.Main;
import codetoon.util.animation.Animation;

import java.awt.*;
import java.net.UnknownHostException;

public class Setting extends Map{
    @Override
    public void setup(Graphics g) throws UnknownHostException {
        Animation.create(g).draw("setting.lang", 40, 50, new Animation.Properties().size(40).button(i -> Main.getInstance().run(new Language())).frame(Color.WHITE));
        Animation.create(g).draw("setting.exit", 20, 100, new Animation.Properties().size(40).button(i -> Main.getInstance().run(new Title())).frame(Color.WHITE));
        Animation.create(g).draw("setting.save", 120, 100, new Animation.Properties().size(40).frame(Color.RED));
    }

    @Override
    public void display(Graphics g) {

    }
}
