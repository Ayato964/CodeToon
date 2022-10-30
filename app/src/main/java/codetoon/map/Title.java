package codetoon.map;

import codetoon.util.*;
import codetoon.main.*;
import codetoon.system.*;

import java.awt.*;
public class Title extends Map{
    private ContainerBox box;
    public Title(){
    box = new ContainerBox(60, 40, 70, 40, new ContainerData<Box, Integer>(){
      @Override
      public void action(int i){
        switch(i){
          case 0: System.out.println("新規ゲームを始める。"); Main.getInstance().run(new PazzleStage(5)); HostServer.server.setUpServer(); break;
          case 1: System.out.println("途中からゲームを始める。");Main.getInstance().run(new PazzleStage(5)); GuestServer.server.connect(null); break;
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
            case 0-> new Box("新規でセッションを作成する");
            case 1-> new Box("既存セッションに参加する");
            case 2-> new Box("終わる"); 
            default-> null;
        };
      }
    });
  }
  
  public void display(Graphics g){
    MyText.setText("名前未定", 10 * Main.DW,   10 * Main.DH);
    box.draw();
  }
}
