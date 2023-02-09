package codetoon.map;

import codetoon.server.Server;
import codetoon.system.CodeToon;
import codetoon.system.Rule;
import codetoon.util.*;
import codetoon.main.*;
import codetoon.util.animation.Animation;
import codetoon.util.animation.AnimationImage;
import codetoon.util.box.Box;
import codetoon.util.box.DrawingTextBox;
import codetoon.util.box.ContainerBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Title extends Map{
    private ContainerBox box;
    public Title(){

  }

    @Override
    public void setup(Graphics h) {
        Animation.createImage(h).draw("title/title", 45, 0, 80, 80, new AnimationImage.PropertiesImage().of(468, 468).center().fade(2, -1));
        Animation.create(h).draw("Version." + CodeToon.GAME_VERSION, 160, 110,
                new Animation.Properties().font("", Font.ITALIC, 32));
        Animation.createImage(h).draw("other/setting", 190, 90, 13, 13,  new AnimationImage.PropertiesImage().of(120, 120).button(i -> Main.getInstance().run(new Setting())));
        //Animation.createImage(h).draw("other/loading2", 20, 20, 13, 13, new AnimationImage.PropertiesImage().animation());

        AnimationImage.createImage(h).draw("title/icon", 170, 90, 13, 13, new AnimationImage.PropertiesImage().of(1280, 1280)
                .button(i ->{
                    try {
                        Desktop.getDesktop().browse(new URI("https://code-toon.netlify.app/"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                }));
        Animation.create(h).draw("title.chooser1", 70, 70, new Animation.Properties().size(40)
                .frame(Color.WHITE, 80, 8, ()->true)
                .button(i->
                {
                   // Server.isHost = true;
                    Main.getInstance().run(new CreateSection());
                   // Server.server.startServer(null);
                }));
        Animation.create(h).draw("title.chooser2", 70, 78, new Animation.Properties().size(40)
                .frame(Color.WHITE, 80, 8, ()->true)
                .button(i-> {
                    CodeToon.DEBUG = true;
                    Main.getInstance().run(new PythonSetup(5));
                }));
        Animation.create(h).draw("title.chooser3", 70, 86, new Animation.Properties().size(40)
                .frame(Color.WHITE, 80, 8, ()->true).button(i->System.exit(-1)));
/*
        Animation.create(h).draw("title.chooser4", 70, 94, new Animation.Properties().size(40)
                .frame(Color.WHITE, 80, 8, ()->true).button(i->{
                    Rule r = Rule.create();
                    r.memory_w = 5;
                    r.memory_h = 5;
                    r.isSettingMemoryPassword = false;
                    r.isShowingOpponentMemory = false;
                    CodeToon.RULE = r;
                    CodeToon.DEBUG = true;Main.getInstance().run(new PazzleStage(r));
    }));
 */

    }


    public void display(Graphics g){
 //       box.draw();
  }

}
