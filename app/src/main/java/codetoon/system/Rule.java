package codetoon.system;

import java.io.Serializable;

public class Rule implements Serializable {
    private static Rule instance;
    public int memory_w;
    public int memory_h;
    public Difficulty dif;
    public boolean isShowingOpponentMemory = false;
    public boolean isSettingMemoryPassword = false;

    private Rule(){}
    public static Rule getInstance(){
        return instance;
    }
    public static Rule create(){
        instance = new Rule();
        return instance;
    }
}