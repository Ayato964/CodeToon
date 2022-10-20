package codetoon.system;

import codetoon.method.*;
import codetoon.util.IsTick;
import codetoon.util.TickHelper;
import codetoon.util.TickRegistory;

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
    public TickRegistory getTick() {
        return TickRegistory.createTicker( this, Admin::tick);
    }

    public static <T> void tick(T t){
        if(GameMaster.isGameStart) {
            System.out.println("This is Admin!!!!!!");
        }
    }


}
