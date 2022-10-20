package codetoon.system;

import codetoon.util.IsTick;
import codetoon.util.TickHelper;
import codetoon.util.TickRegistory;

import java.awt.*;
import java.util.function.Supplier;

/** メモリーを描画、処理するクラス。 **/
public class Memory extends Player{
    int x, y, w, h, idI, idC;
    private int counter = 0;

    public Memory(int x, int y, int w, int h, int idI, int idC){
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
      this.idI = idI;
      this.idC = idC;
    }

    public static <R> void tick(R t){
        if(GameMaster.isGameStart){

        }
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
    public TickRegistory getTick() {
        return TickRegistory.createTicker(this, Memory::tick);
    }


}