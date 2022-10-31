package codetoon.system;

import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.method.Methods;
import codetoon.method.MyMethod;
import codetoon.util.Indentification;
import codetoon.util.TickRegistory;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.io.Serializable;

import static codetoon.system.CodeToon.isCliant;

/** メモリーを描画、処理するクラス。 **/
public class Memory extends Player implements Serializable{
    private StringBuilder source = null;
    int x, y, w, h, idI, idC;
    public  Color color = Color.WHITE;
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
        if(CodeToon.isGameStart && isCliant ){
            Memory memory = (Memory)t;
            memory.counter ++;
            if(memory.counter / 1000 >= 5){
                memory.counter = 0;
                //System.out.println(memory.source != null ? memory.source.toString() : "ソースが入力されていません");

                memory.run();
            }


        }

    }

    public void changeColor(){
        color = Color.RED;
    }

    public void display(Graphics g){
        g.setColor(color);
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
    protected void blackList(ArrayList<MyMethod> m) {
        m.add(Methods.CONNECT.get());
    }

    @Override
    public void connection(int password) {
        PazzleStage p = (PazzleStage) Main.getInstance().getMap();
        if(pass == 0 || pass == password) {
            p.getConsole().setHost(this);
            p.getConsole().panel.setProgram(getSource() != null ? getSource() : new StringBuilder());
        }else{
            p.getConsole().panel.setProgram(new StringBuilder());
            System.out.println(getName() + "にはパスワードが設定されているか、パスワードが違うためコネクトを確立できません。");

        }
    }


}