package codetoon.system;

import java.awt.*;
import java.io.Serializable;

public abstract class AbstractLockerPlayer extends Player implements Serializable {
    protected int pass = 0;
    public void setPassWord(int pass, int old_pass) {
        if(this.pass == old_pass){
            this.pass = pass;
        }else{
            Message.addMessage(getName() + "のパスワードが違います。", Color.RED);
        }
    }

    public boolean getPass(int p) {
        return pass == p;
    }
}
