package codetoon.system;

import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.method.Methods;
import codetoon.method.MyMethod;
import codetoon.util.Indentification;
import codetoon.util.IsTick;
import codetoon.util.TickRegistory;
import codetoon.server.Server;
import codetoon.variable.Variables;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.io.Serializable;

public class Memory extends AbstractLockerPlayer implements Serializable{
   // public int serialID;
    private StringBuilder source = null;
    EnumMemoryStates states;
    int x, y, w, h, idI, idC;
    public  Color color = Color.WHITE;
    public int counter = 0;
    private final String name = "Memory";
    private boolean isHostMemory;

    public Memory(int x, int y, int w, int h, int idC, int idI){
        serialID = Admin.getInstance().getSerialID();
        isHostMemory = Server.isHost;
        states = EnumMemoryStates.NONE;
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

    public static <T extends IsTick> void tick(T t){
        if(CodeToon.isGameStart){
            Memory memory = (Memory)t;
            memory.counter ++;
            if(memory.counter / 100 >= 5 && memory.source != null){
                System.out.println("Hello");
                memory.counter = 0;
                ArrayList<MyMethod> methods = Indentification.indentification(memory.source.toString(), memory);
                memory.setRunMethod(methods);
                memory.runMethod();
                Variables.VARIABLE.deleteAll("variable_" + memory.getID());
            }
        }

    }

    public void hacking(int pass){
        System.out.println(serialID +"   " + Admin.getInstance().getSerialID());
        if(serialID != Admin.getInstance().getSerialID() && states != EnumMemoryStates.HACKED) {
            if (pass == this.pass) {
                states = EnumMemoryStates.HACKED;
                serialID = Admin.getInstance().getSerialID();
                //   System.out.println("Hacked");
            } else {
                Message.addMessage("パスワードが設定されているか、パスワードが違うため、攻撃できません。", Color.BLACK);
            }
        }else{
            Message.addMessage("自分の陣地をハッキングするのは愚かなことです。");
        }
    }

    public void display(Graphics g){
        g.setColor(states.getColor());
        g.fillRect(x, y, w, h);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, w, h);
    }

    @Override
    public String getName(){
      return name + "_" + idI + "_" + idC;
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
        if(source.isEmpty()){
            states = EnumMemoryStates.NONE;
        }else{
            if(states != EnumMemoryStates.HACKED){
                states = EnumMemoryStates.USED;
            }
        }

        console.panel.resetAll();
        Server.server.sendOpponentCopy();
        Server.server.sendMyCopy();
    }
    public int showPass(){
        return pass;
    }
    @Override
    protected void blackList(ArrayList<MyMethod> m) {
        m.add(Methods.CONNECT.get());
    }

    @Override
    public String getID() {
        return "memory" +"[" + idI + "][" + idC + "]";
    }

    @Override
    public void connection(int password) {
        if(states == EnumMemoryStates.HACKED && serialID == Admin.getInstance().getSerialID()){
            connect(password);
        }else if(states == EnumMemoryStates.HACKED) {
            Message.addMessage("このメモリーはハッキングされています！！", Color.RED);
            Message.addMessage("attack()で取り返してください", Color.RED);

        }else {
            if(serialID != Admin.getInstance().getSerialID()){
                Message.addMessage("自分のメモリーではないため、接続できません。");
            }else {
                connect(password);
            }
        }
    }
    private void connect(int password){
        PazzleStage p = (PazzleStage) Main.getInstance().getMap();
        if (pass == 0 || pass == password) {
            Message.addMessage(p.getConsole().getHost().getName() + "にアクセスされました", Color.BLACK);
            p.getConsole().setHost(this);
            p.getConsole().panel.setProgram(getSource() != null ? getSource() : new StringBuilder());
        } else {
            p.getConsole().panel.setProgram(new StringBuilder());
            Message.addMessage("パスワードが再設定されているか、パスワードが間違っている為アクセスできません", Color.BLACK);

        }
    }


    @Override
    public boolean isClient() {
        if(isHostMemory == Server.isHost){
            return true;
        }else{
            return false;
        }
    }


    public void recovering(int pass) {
        if(serialID != Admin.getInstance().getSerialID() && states == EnumMemoryStates.HACKED){
            if(pass == this.pass){
                serialID = Admin.getInstance().getSerialID();
                states = EnumMemoryStates.NONE;
                method = new ArrayList<>();
                source = new StringBuilder();
            }else{
                Message.addMessage("パスワードが間違っています", Color.BLACK);
            }
        }else {
            Message.addMessage("リカバリーできません。", Color.BLACK);
        }
    }
}