package codetoon.system;

import codetoon.argument.BooleanArgument;
import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.argument.StringArgument;
import codetoon.main.Main;
import codetoon.map.PazzleStage;

import java.awt.*;
import java.util.HashMap;

public class HashMemory<T, V> extends Memory{
    HashMap<T, V> hash;
    public T t;
    public V v;
    public HashMemory(Information info, T exT, V exV) {
        super(info);
        memorySirialID = info.memorySerial;
        states = EnumMemoryStates.HASHMODE;
        serialID = info.serial;
        pass = info.pass;
        hash = new HashMap<>();
        t = exT;
        v = exV;
    }
    public void set(T a, V b, int pass){
        if(this.pass == pass) {
            hash.put(a, b);
            System.out.println(hash.get(a));
            Message.addMessage(new String[]{getName()}, "hash.set.success");
        }else
            Message.addMessage(new String[]{getName()},"hash.set.error");
    }
    public V get(String a, int pass, Player host){
        if(this.pass == pass)
            return hash.get(changeToArgument(host, t, a));
        else
            return null;
    }

    @Override
    public void connection(int password) {
        Message.addMessage(new String[]{getName()}, "hash.error.mes1");
    }

    @Override
    public void run() {

    }
    @Override
    public void hacking(int pass, int hostSerialID) {
        System.out.println(serialID +"   " + hostSerialID);
        if(serialID != hostSerialID && states != EnumMemoryStates.HACKED) {
            if (pass == this.pass) {
                states = EnumMemoryStates.HACKED;
                this.pass = 0;
                serialID = hostSerialID;
                removeAnimation();

                PazzleStage p = (PazzleStage) Main.getInstance().getMap();
                Memories.memory.remove(p.MEMORY_H * getIdC() + getIdI());
                Memories.memory.add(p.MEMORY_H * getIdC() + getIdI(), new Memory(getInfo()));
                Memories.runThread(Memories.memory.get(p.MEMORY_H * getIdC() + getIdI()));
            } else {
                Message.addMessage("memory.attack.mes1", Color.BLACK);
            }
        }else{
            Message.addMessage("memory.attack.mes2");
        }
    }
    protected Object changeToArgument(Player host, T t, String a) {
        if(t.getClass() == "string".getClass())
            return "\"" + StringArgument.getInstance().indentification(a, host) + "\"";
        if(t.getClass() == Integer.class) {
            return IntegerArgument.getInstance().indentification(a, host);
        }
        if(t.getClass() == Class.class)
            return BooleanArgument.getInstance().indentification(a, host);

        return ObjectArgument.getInstance().indentification(a, host);
    }
}
