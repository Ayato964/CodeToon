package codetoon.system;

import java.util.ArrayList;
import codetoon.method.*;
public abstract class Player {
    ArrayList<MyMethod> method = new ArrayList<>();
    public void setRunMethod(ArrayList<MyMethod> m){
        method = m;
    }
    public void run(){
        for(int i = 0; i < method.size(); i ++){
            method.get(i).action(i);
        }
    }
    public abstract String getName();    
    
}
