package codetoon.system;

import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.util.TickRegistory;

import java.awt.*;

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

    public static void tick(Object t){
        if(CodeToon.isGameStart){
            Memory memory = (Memory)t;
            memory.counter ++;
            if(memory.counter / 1000 >= 5){
                memory.counter = 0;
                memory.run();
            }


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

    @Override
    public void endMethod() {
        Console c =((PazzleStage) Main.getInstance().getMap()).getConsole();
        setRunMethod(c.getMethods());
        c.panel.resetAll();
    }


}