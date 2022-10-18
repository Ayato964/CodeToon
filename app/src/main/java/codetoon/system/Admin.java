package codetoon.system;

import codetoon.method.*;
import codetoon.util.TickHelper;

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

    @Override
    protected TickHelper getTick() {
        return Admin::tick;
    }

    public static void tick(){
        System.out.println("This is Admin!!!!!!");
    }


}
