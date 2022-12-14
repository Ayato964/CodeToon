package codetoon.map;

import codetoon.main.*;
import codetoon.system.*;
import codetoon.util.animation.Animation;

import java.awt.*;
/** 
実際の先頭画面を描画する、描画スクリーンクラス。
このクラスには、小クラスとして、Fieldクラスを含んでいる。
FieldクラスはMemoryをContainerとして収容するものである。
**/
public class PazzleStage extends Map{
    public final int MEMORY_SIZE; 
    private final Field field;
    private final Message messageBox;
    private final Console c;
    
    public PazzleStage(int size){
      MEMORY_SIZE = size;
      field = new Field(5, 20, 130, 83);
      field.setMemoryCapability(MEMORY_SIZE);
      c = new Console(140, 50, 120, 60);
      c.setVisible(true);
      messageBox = new Message(Main.getMainGraphics(), 140, 20, 60, 80);
      CodeToon.isGameStart = true;
    }

    public Console getConsole() {
        return c;


    }

    @Override
    public void setup(Graphics h) {

        Animation.create(h).draw("stage.memory.list", 10, 10,
                new Animation.Properties()
                    .size(40));
    }

    @Override
    /** フィールドを描画 **/
    public void display(Graphics g){
      //background(#505050);
      field.display(g);
      messageBox.draw();
    }
  
    private class Field{
      private int x, y, w, h;
      int size;
      public Field(int x, int y, int w, int h){
        this.x = x * Main.DW;
        this.y = y * Main.DH;
        this.w = w * Main.DW;
        this.h = h * Main.DH;
      }
      public void setMemoryCapability(int size){
        this.size = size;
        Memories.setInstance(size, x, y, w, h);
      }
      public void display(Graphics g){
        
        for(int i = 0; i < size * size; i ++){
            Memories.get(i).display(g);
        }
      
      }
    }
  }