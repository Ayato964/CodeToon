package codetoon.system;

import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.method.MyMethod;
import codetoon.util.Indentification;
import codetoon.util.TickRegistory;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;

/** メモリーを描画、処理するクラス。 **/
public class Memory extends Player{
    private StringBuilder source = null;
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

    public StringBuilder getSource() {
        return source;
    }

    public static void tick(Object t){
        if(CodeToon.isGameStart){
            Memory memory = (Memory)t;
            memory.counter ++;
            if(memory.counter / 1000 >= 5){
                memory.counter = 0;
                //System.out.println(memory.source != null ? memory.source.toString() : "ソースが入力されていません");

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
    public void endMethod(@NotNull Console console, ArrayList<MyMethod> methods, StringBuilder source) {
        setRunMethod(methods);
        console.setHost(Admin.getInstance());
        source = Indentification.removeEnd(source);
        this.source = source;
        console.panel.resetAll();

    }

    @Override
    ArrayList<MyMethod> removeBlackList(ArrayList<MyMethod> m) {
        return m;
    }


}