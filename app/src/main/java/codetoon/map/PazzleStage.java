package codetoon.map;

import codetoon.main.*;
import codetoon.system.*;
import codetoon.util.Action;
import codetoon.util.animation.Animation;

import java.awt.*;
public class PazzleStage extends Map{
    public int MEMORY_SIZE;
    public int MEMORY_W;
    public int MEMORY_H;
    private final Field field;
    private Field enemyField;
    private Rule rule;
    private final Message messageBox;
    public final Observer observer;
    private final Console c;

    public PazzleStage(int size){
      MEMORY_SIZE = size;
      field = new Field(5, 20, 130, 83);
      field.setMemoryCapability(MEMORY_SIZE, MEMORY_SIZE);
      c = Console.getInstance();
      c.setVisible(true);
        messageBox = new Message(Main.getMainGraphics(), 140, 65, 60, 40);
        observer = new Observer(c, 140, 15, 60, 40);
        CodeToon.gameStart();
    }
    public PazzleStage(Rule r){
        rule = r;
        MEMORY_W = r.memory_w;
        MEMORY_H = r.memory_h;
        MEMORY_SIZE = MEMORY_H;
        if(r.dif != Difficulty.EASY) {
            field = new Field(5, 15, 130, 83);
            field.setMemoryCapability(MEMORY_W, MEMORY_H);
        }else{
            field = new Field(10, 70, 75, 40);
            field.setMemoryCapability(MEMORY_W, MEMORY_H);
            enemyField = new Field(10, 20, 75, 40);
            enemyField.setEnemyMode(MEMORY_W, MEMORY_H);
        }
        c = Console.getInstance();
        c.setVisible(true);
        messageBox = new Message(Main.getMainGraphics(), 140, 65, 60, 40);
        observer = new Observer(c, 140, 15, 60, 40);
        CodeToon.gameStart();
    }
    public Console getConsole() {
        return c;
    }

    @Override
    public void setup(Graphics h) {
        if(rule.dif != Difficulty.EASY) {
            Animation.create(h).draw("stage.memory.list", 10, 10,
                    new Animation.Properties()
                            .size(40));
        }else{
            Animation.create(h).draw("stage.enemy.memory.list", 10, 10, new Animation.Properties().size(40));
            Animation.create(h).draw("stage.memory.list", 10, 65, new Animation.Properties().size(40));
        }
        if(CodeToon.DEBUG){
            Animation.create(h).draw("stage.memory.debug.end", 0, 10, new Animation.Properties().size(40).center().frame(Color.WHITE).button(i ->{
                Console.getInstance().setVisible(false);
                Memories.stopAll();
                Main.getInstance().run(new Title());
            }));
        }
    }

    @Override
    /** フィールドを描画 **/
    public void display(Graphics g){
      //background(#505050);
      field.display(g);
      if(rule.dif == Difficulty.EASY)
          enemyField.display(g);
      messageBox.draw();
      observer.draw(g);
      PopUpWindow.popUpWindow.drawPopUpWindow();
    }

    private class Field{
      private int x, y, w, h;
      int mw, mh;
      boolean enemyMode = false;
      public Field(int x, int y, int w, int h){
        this.x = x * Main.DW;
        this.y = y * Main.DH;
        this.w = w * Main.DW;
        this.h = h * Main.DH;
      }
      public void setMemoryCapability(int mw, int mh){
          this.mw = mw;
          this.mh = mh;
          System.out.println(mw + "    " + mh);
        Memories.setInstance(mw, mh, x, y, w, h);
      }
      public void display(Graphics g){
          if(!enemyMode) {
              for (int i = 0; i < mw * mh; i++) {
                  Memories.get(i).display(g);
              }
          }else{
              if(Memories.opponentMemory != null)
                  if(!Memories.opponentMemory.isEmpty()) {
                      int z = 0;
                      for (int i = 0; i < mh; i++) {
                          for (int c = 0; c < mw; c++) {
                              Memories.opponentMemory.get(z).display(g, x + i * (w / mw), y + c * (h / mh), w / mw, h / mh);
                              z ++;
                          }
                      }
                  }
          }
      }

        public void setEnemyMode(int memory_w, int memory_h) {
          this.mw = memory_w;
          mh = memory_h;
          enemyMode = true;
        }
    }
  }