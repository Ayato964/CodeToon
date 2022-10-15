package codetoon.system;

import codetoon.method.*;

import java.util.ArrayList;


public class Admin extends Player{
    public Admin(){
        
    }   
    @Override
    public void setRunMethod(ArrayList<MyMethod> m) {
        super.setRunMethod(m);
    } 
    @Override
    public String getName(){
        return "Player";
    }
}
