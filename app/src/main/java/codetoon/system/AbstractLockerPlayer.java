package codetoon.system;

import codetoon.server.Server;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class AbstractLockerPlayer extends Player implements Serializable {
    private ArrayList<Integer> history = new ArrayList<>();
    public int serialID;
    //SUCSESS
    public int pass = 0;
    public void setPassWord(int old_pass, int pass) {
        if(Admin.getInstance().getSerialID() == serialID) {
            if (this.pass == old_pass) {
                history.add(this.pass);
                this.pass = pass;
                Server.server.sendOpponentCopy();
                Server.server.sendMyCopy();
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

    public Integer getBeforePass() {
        return history.get(history.size() - 1);
    }
}
