package codetoon.map;

import codetoon.system.Background;
import codetoon.system.CodeToon;
import codetoon.main.*;
import codetoon.system.Rule;
import codetoon.util.animation.Animation;
import codetoon.util.animation.AnimationImage;
import codetoon.util.box.ContainerBox;
import org.python.compiler.Code;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Title extends Map{
    public Title(){

  }

    @Override
    public void setup(Graphics h) {
        if(Background.getInstance().mode== Background.BackgroundMode.COLOR_ANIMATION) {
            Animation.createImage(h).draw("title/logo", 55, 10, 160, 30,
                    new AnimationImage.PropertiesImage().of(4090, 676).center());
        }else {
            Animation.createImage(h).draw("title/title",55,0,80,80,new AnimationImage.PropertiesImage().of(468,468).center());
        }
        Animation.create(h).draw("Version." + CodeToon.GAME_VERSION, 160, 110,
                new Animation.Properties().font("", Font.ITALIC, 32));
        Animation.createImage(h).draw("other/setting", 190, 90, 13, 13,  new AnimationImage.PropertiesImage().of(120, 120).button(i -> Main.getInstance().run(new Setting())));
        //Animation.createImage(h).draw("other/loading2", 20, 20, 13, 13, new AnimationImage.PropertiesImage().animation());

        AnimationImage.createImage(h).draw("title/icon", 170, 90, 13, 13, new AnimationImage.PropertiesImage().of(1280, 1280)
                .button(i ->{
                    try {
                        Desktop.getDesktop().browse(new URI("https://github.com/Ayato964/CodeToon/wiki"));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }));
        AnimationImage.createImage(h).draw("other/help",170,70,13,13,new AnimationImage.PropertiesImage().of(120,120).button(e -> Main.getInstance().run(new Setting())));
        Animation.create(h).draw("title.chooser1", 70, 70, new Animation.Properties().size(40)
                .backgroundCenter(CodeToon.categoryBg, 80, 8)
                .frameCenter(CodeToon.frameColor, 80, 8)
                .center()
                .color(CodeToon.textColor)
                .button(i->
                {
                   // Server.isHost = true;
                    Main.getInstance().run(new CreateSection());
                   // Server.server.startServer(null);
                }));
        Animation.create(h).draw("title.chooser2", 70, 78, new Animation.Properties().size(40)
                .backgroundCenter(CodeToon.categoryBg,  80, 8)
                .frameCenter(CodeToon.frameColor, 80, 8).color(CodeToon.textColor)
                .button(i-> {
                    CodeToon.DEBUG = true;
                    Main.getInstance().run(new PythonSetup(5));
                }));
        Animation.create(h).draw("title.chooser3", 70, 86, new Animation.Properties().size(40)
                .backgroundCenter(CodeToon.categoryBg, 80, 8)
                .frameCenter(CodeToon.frameColor, 80, 8).color(CodeToon.textColor).button(i->System.exit(-1)));

        Animation.create(h).draw("title.chooser4", 70, 94, new Animation.Properties().size(40)
                .backgroundCenter(CodeToon.categoryBg, 80, 8)
                .frameCenter(CodeToon.frameColor, 80, 8).color(CodeToon.textColor).button(i->{
                    Rule r = Rule.create();
                    r.memory_w = 5;
                    r.memory_h = 5;
                    r.isSettingMemoryPassword = false;
                    r.isShowingOpponentMemory = false;
                    CodeToon.RULE = r;
                    CodeToon.DEBUG = true;Main.getInstance().run(new PazzleStage(r));
    }));


    }


    public void display(Graphics g){
 //       box.draw();
  }

}
