package codetoon.map;

import codetoon.system.Server;
import codetoon.util.*;
import codetoon.main.*;
import codetoon.util.animation.Animation;

import java.awt.*;
public class Title extends Map{
    private ContainerBox box;
    public Title(){
    box = new ContainerBox(60, 40, 70, 40, new ContainerData<Box, Integer>(){
      @Override
      public void action(int i){
        switch(i){
          case 0:
             /* Main.getInstance().run(new CreateSection());*/
              Main.getInstance().run(new PazzleStage(5)); Server.server.setUpServer(); break;
          case 1: System.out.println("�r������Q�[�����n�߂�B");/*Main.getInstance().run(new PazzleStage(5)); Server.server.connect("192.168.11.13"); */break;
          case 2: System.exit(0); break;
        }
      }
      @Override
      public int getCount(){
        return 3;
      }
      @Override
      public Box set(Integer i){
        return switch (i.intValue()) {
            case 0-> new Box("�V�K�ŃZ�b�V�������쐬����");
            case 1-> new Box("�����Z�b�V�����ɎQ������");
            case 2-> new Box("�I���"); 
            default-> null;
        };
      }
    });
  }

    @Override
    public void setup(Graphics h) {
        box.draw();
        Animation.create(h).draw("CODETOON", 10,   10,
                new Animation.Properties()
                        .size(60)
                        .font("", Font.ITALIC)
                        .displayTime(3)
        );
         Animation.create(h).draw("�e�X�g", 30, 30,
                 new Animation.Properties()
                         .size(60)
                         //.displayTime(5)
                 );
    }
    public void display(Graphics g){
        box.draw();

  }
}
