package codetoon.map;

import codetoon.server.Server;
import codetoon.system.CodeToon;
import codetoon.util.*;
import codetoon.main.*;
import codetoon.util.animation.Animation;
import codetoon.util.box.Box;
import codetoon.util.box.DrawingTextBox;
import codetoon.util.box.ContainerBox;

import java.awt.*;
public class Title extends Map{
    private ContainerBox box;
    public Title(){

  }

    @Override
    public void setup(Graphics h) {
        Animation.createImage(h).draw("title/title", 50, 0, 80, 80, new Animation.Properties().center().fade(2, -1));
        Animation.create(h).draw("Version." + CodeToon.GAME_VERSION, 160, 110,
                new Animation.Properties().font("", Font.ITALIC, 32));
        Animation.createImage(h).draw("other/setting", 190, 90, 13, 13, new Animation.Properties().button(i -> Main.getInstance().run(new Setting())));

        Animation.create(h).draw("title.chooser1", 75, 70, new Animation.Properties().size(40)
                .frame(Color.WHITE, 80, 8, ()->true)
                .button(i->
                {
                   // Server.isHost = true;
                    Main.getInstance().run(new CreateSection());
                   // Server.server.startServer(null);
                }));
        Animation.create(h).draw("title.chooser2", 75, 78, new Animation.Properties().size(40)
                .frame(Color.WHITE, 80, 8, ()->true)
                .button(i-> {
                    CodeToon.DEBUG = true;
                    Main.getInstance().run(new PythonSetup(5));
                }));
        Animation.create(h).draw("title.chooser3", 75, 86, new Animation.Properties().size(40)
                .frame(Color.WHITE, 80, 8, ()->true).button(i->System.exit(-1)));

        Animation.create(h).draw("title.chooser4", 75, 94, new Animation.Properties().size(40)
                .frame(Color.WHITE, 80, 8, ()->true).button(i->{
                    CodeToon.DEBUG = true;Main.getInstance().run(new PazzleStage(5));
    }));


    }


    public void display(Graphics g){
 //       box.draw();
  }

}
