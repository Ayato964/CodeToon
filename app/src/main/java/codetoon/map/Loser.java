package codetoon.map;

import codetoon.main.Main;
import codetoon.util.animation.Animation;

import java.awt.*;
import java.net.UnknownHostException;

public class Loser extends Map{
    @Override
    public void setup(Graphics g) throws UnknownHostException {
        Animation.create(g).draw("loser.mes1", 20, 30, new Animation.Properties().size(120).center().color(Color.CYAN));
        Animation.create(g).draw("loser.mes2", 20, 50, new Animation.Properties().size(40).center());
        Animation.create(g).draw("loser.chooser1", 20, 70, new Animation.Properties().size(40).center().button(i -> Main.getInstance().run(new JoinServer())));
        Animation.create(g).draw("loser.chooser2", 20, 80, new Animation.Properties().size(40).center().button(i -> Main.getInstance().run(new Title())));
        Animation.create(g).draw("loser.chooser3", 20, 90, new Animation.Properties().size(40).center().button(i -> System.exit(-1)));
    }

    @Override
    public void display(Graphics g) {

    }
}
