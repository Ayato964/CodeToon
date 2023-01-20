package codetoon.system;

import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.method.Methods;
import codetoon.method.MyMethod;
import codetoon.util.Indentification;
import codetoon.util.IsTick;
import codetoon.util.TickRegistory;
import codetoon.server.Server;
import codetoon.util.animation.Animation;
import codetoon.util.converter.ConvertSource;
import codetoon.variable.Variables;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Random;

public class Memory extends AbstractLockerPlayer implements Serializable{
   // public int serialID;
    protected StringBuilder source = null;
    boolean isFirst;
    EnumMemoryStates states;
    protected int memorySirialID;
    int x, y, w, h, idI, idC;
    public  Color color = Color.WHITE;
    private final String name = "Memory";
    private final boolean isHostMemory;

    public Memory(int x, int y, int w, int h, int idC, int idI){
        serialID = Admin.getInstance().getSerialID();
        memorySirialID = new Random().nextInt(0, 100000000);
        isHostMemory = Server.isHost;
        states = EnumMemoryStates.NONE;
        isFirst = true;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.idI = idI;
        this.idC = idC;

    }
    public Memory(Information m){
        this(m.x, m.y, m.w, m.h, m.idC, m.idI);
        memorySirialID = m.memorySerial;
        isFirst = false;
        serialID = m.serial;

    }

    public int getIdC() {
        return idC;
    }

    public int getIdI() {
        return idI;
    }

    public void setup(Graphics g){
        if(isFirst) {
            Animation.createImage(g).draw("other/lock", x / Main.DW, y / Main.DH, w / Main.DW, h / Main.DH,
                    new Animation.Properties()
                                     .drawIf(()->pass != 0)
            );
            Animation.createImage(g).draw("other/target", x / Main.DW, y / Main.DH, w / Main.DW, h / Main.DH, new Animation.Properties()
                    .drawIf(() ->{
                        PazzleStage p = (PazzleStage) Main.getInstance().getMap();
                        Console c = p.getConsole();
                        if(c.getHost() instanceof  Memory){
                            return ((Memory) c.getHost()).memorySirialID == memorySirialID;
                        }
                        return false;
                    })
            );
            isFirst = false;
        }
    }

    public StringBuilder getSource() {
        return source;
    }

    public static <T extends IsTick> void tick(T t){
        if(CodeToon.isGameStart){
            Memory memory = (Memory)t;
            if(memory.source != null) {
                //ArrayList<MyMethod> methods = Indentification.indentification(memory.source.toString(), memory);
                if(!memory.source.isEmpty()) {
                    ArrayList<MyMethod> methods = ConvertSource.convert(memory.source.toString(), memory);
                    memory.setRunMethod(methods);
                    memory.runMethod();
                    Variables.VARIABLE.deleteAll(memory.getID() + "_" + memory.getSerialID());
                    Server.server.sendOpponentCopy();
                    Server.server.sendMyCopy();
                }
            }
        }

    }

    public EnumMemoryStates getStates() {
        return states;
    }

    public int getMemorySirialID() {
        return memorySirialID;
    }

    public void hacking(int pass, int hostSerialID){
        System.out.println(serialID +"   " + hostSerialID);
        if(serialID != hostSerialID && states != EnumMemoryStates.HACKED) {
            if (pass == this.pass) {
                states = EnumMemoryStates.HACKED;
                this.pass = 0;
                serialID = hostSerialID;
                //   System.out.println("Hacked");
            } else {
                Message.addMessage("memory.attack.mes1", Color.BLACK);
            }
        }else{
            Message.addMessage("memory.attack.mes2");
        }
    }

    public void display(@NotNull Graphics g){
        setup(g);
        g.setColor(states.getColor());
        g.fillRect(x, y, w, h);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, w, h);
    }
    public void display(@NotNull Graphics g, int x, int y, int w, int h){
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
        Indentification.removeEnd(source);
        this.source = source;
        if(source.isEmpty() && states != EnumMemoryStates.HACKED){
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
            Message.addMessage("memory.connection.mes1", Color.RED);
            Message.addMessage("memory.connection.mes2", Color.RED);

        }else {
            if(serialID != Admin.getInstance().getSerialID()){
                Message.addMessage("memory.connection.mes3");
            }else {
                connect(password);
            }
        }
    }
    private void connect(int password){
        PazzleStage p = (PazzleStage) Main.getInstance().getMap();
        if (pass == 0 || pass == password) {
            Message.addMessage(new String[]{p.getConsole().getHost().getName()},"memory.connection.mes5", Color.BLACK);
            p.getConsole().setHost(this);
            p.getConsole().panel.setProgram(getSource() != null ? getSource() : new StringBuilder(), 0);
        } else {
            p.getConsole().panel.setProgram(new StringBuilder(), 0);
            Message.addMessage("memory.connection.mes4", Color.BLACK);

        }
    }


    @Override
    public boolean isClient() {
        return isHostMemory == Server.isHost;
    }


    public void recovering(int pass) {
        if(serialID != Admin.getInstance().getSerialID() && states == EnumMemoryStates.HACKED){
            if(pass == this.pass){
                serialID = Admin.getInstance().getSerialID();
                states = EnumMemoryStates.NONE;
                method = new ArrayList<>();
                source = new StringBuilder();
                Message.addMessage("memory.recovery.mes3", Color.BLACK);
            }else{
                Message.addMessage("memory.recovery.mes1", Color.BLACK);
            }
        }else {
            Message.addMessage("memory.recovery.mes2", Color.BLACK);
        }
    }
    public boolean equals(Memory obj) {
        return  this.eqSource(obj)&&
                this.states == obj.states &&
                this.getID().equals(obj.getID()) &&
                this.pass == obj.pass;
    }

    private boolean eqSource(Memory m) {
        if(m.source == null || source == null){
            if(m.source == null && source != null){
                return false;
            }else return m.source == null;
        }else{
            return this.source.toString().equals(m.source.toString());
        }
    }

    public Information getInfo() {
        return new Information();
    }

    protected class Information{
        int x, y, w, h, idI, idC;
        int memorySerial, serial;
        public Information(){
            this.x = Memory.this.x;
            this.y = Memory.this.y;
            this.w = Memory.this.w;
            this.h = Memory.this.h;
            this.idI = Memory.this.idI;
            this.idC = Memory.this.idC;
            this.memorySerial = Memory.this.memorySirialID;
            serial = Memory.this.serialID;
        }
    }
}