package codetoon.map;

import codetoon.main.*;
import codetoon.system.*;
import codetoon.util.animation.Animation;

import java.awt.*;
/** 
���ۂ̐擪��ʂ�`�悷��A�`��X�N���[���N���X�B
���̃N���X�ɂ́A���N���X�Ƃ��āAField�N���X���܂�ł���B
Field�N���X��Memory��Container�Ƃ��Ď��e������̂ł���B
**/
public class PazzleStage extends Map{
    public final int MEMORY_SIZE; 
    private final Field field;
    private final Console c;
    
    public PazzleStage(int size){
      MEMORY_SIZE = size;
      field = new Field(5, 20, 130, 83);
      field.setMemoryCapability(MEMORY_SIZE);
      c = new Console(140, 50, 120, 60);
      c.setVisible(true);
      CodeToon.isGameStart = true;
    }

    public Console getConsole() {
        return c;


    }

    @Override
    public void setup(Graphics h) {

    }

    @Override
    /** �t�B�[���h��`�� **/
    public void display(Graphics g){
      //background(#505050);
      field.display(g);
      Animation.create(g).draw("My Memory List", 10, 10);
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
        Memorys.setInstance(size, x, y, w, h);
      }
      public void display(Graphics g){
        
        for(int i = 0; i < size * size; i ++){
            Memorys.get(i).display(g);
        }
      
      }
    }
  }