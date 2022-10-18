package codetoon.system;

import codetoon.util.TickHelper;

import java.awt.*;
import java.util.function.Supplier;

/** メモリーを描画、処理するクラス。 **/
public class Memory extends Player{
    int x, y, w, h, idI, idC;

    public Memory(int x, int y, int w, int h, int idI, int idC){
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
      this.idI = idI;
      this.idC = idC;
    }

    public static void tick(){
        System.out.println("This is Memory !!!!!!!");
    }
    public void display(Graphics g){
        g.setColor(Color.WHITE);
      g.fillRect(x, y, w, h);
      g.setColor(Color.BLACK);
      g.drawRect(x, y, w, h);
    }

    @Override
    public String getName(){
      return "Memory_" + idI + "_" + idC;
    }

    @Override
    protected TickHelper getTick() {
        return Memory::tick;
    }


}