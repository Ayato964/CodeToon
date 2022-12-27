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
    box = new ContainerBox(60, 70, 70, 40, new ContainerData<Box, Integer>(){
      @Override
      public void action(int i){
        switch(i){
          case 0:
             Server.isHost = true;
             Main.getInstance().run(new CreateSection());
             Server.server.startServer(null);
              break;
          case 1:
              Server.isHost = false;
              Main.getInstance().run(new JoinServer());

              break;
          case 2: System.exit(0); break;
            case 3:CodeToon.DEBUG = true;Main.getInstance().run(new PazzleStage(5));
        }
      }
      @Override
      public int getCount(){
        return 4;
      }
      @Override
      public Box set(Integer i){
        return switch (i.intValue()) {
            case 0-> new DrawingTextBox("title.chooser1");
            case 1-> new DrawingTextBox("title.chooser2");
            case 2-> new DrawingTextBox("title.chooser3");
            case 3-> new DrawingTextBox("title.chooser4");
            default-> null;
        };
      }
    });
  }

    @Override
    public void setup(Graphics h) {
        Animation.createImage(h).draw("title/title", 30, 0, 80, 80, new Animation.Properties().center().fade(2, -1));
        Animation.create(h).draw("Version." + CodeToon.GAME_VERSION, 160, 110,
                new Animation.Properties().font("", Font.ITALIC, 32));
        Animation.createImage(h).draw("other/setting", 155, 90, 13, 13, new Animation.Properties());
         box.draw();
    }
    public void display(Graphics g){
        box.draw();
  }

}
