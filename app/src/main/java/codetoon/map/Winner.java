package codetoon.map;

import codetoon.main.Main;
import codetoon.server.Server;
import codetoon.system.CodeToon;
import codetoon.system.Memories;
import codetoon.system.Observer;
import codetoon.util.animation.Animation;

import java.awt.*;
import java.net.UnknownHostException;

public class Winner extends Map{
    @Override
    public void setup(Graphics g){
        Animation.create(g).draw("winner.mes1", 20, 30, new Animation.Properties().size(120).center().color(Color.ORANGE));
        Animation.create(g).draw("winner.mes2", 20, 50, new Animation.Properties().size(40).center());
        Animation.create(g).draw("winner.chooser1", 20, 70, new Animation.Properties().size(40).center().button(i -> Main.getInstance().run(new Title())));
        Animation.create(g).draw("winner.chooser2", 20, 80, new Animation.Properties().size(40).center().button(i -> Main.getInstance().run(new CreateSection())));
        CodeToon.RULE = null;
        CodeToon.isGameStart = false;
        Memories.opponentMemory = null;
        Memories.memory = null;
        Server.server.end();

    }

    @Override
    public void display(Graphics g) {

    }
}
