package codetoon.system;

import java.awt.*;
import java.io.Serializable;

public abstract class AbstractLockerPlayer extends Player implements Serializable {
    public int serialID;
    //SUCSESS
    protected int pass = 0;
    public void setPassWord(int old_pass, int pass) {
        if(Admin.getInstance().getSerialID() == serialID) {
            if (this.pass == old_pass) {
                this.pass = pass;

                Message.addMessage(new String[]{getName(), "" +pass},"memory.pass.mes1", Color.RED);
            } else {
                Message.addMessage(new String[]{getName()},"memory.pass.mes2", Color.RED);
            }
        }else{
            Message.addMessage(new String[]{Admin.getInstance().getName()},"memory.pass.mes3");
        }
    }

    @Override
    public int getSerialID() {
        return serialID;
    }

    public boolean getPass(int p) {
        return pass == p;
    }
}
