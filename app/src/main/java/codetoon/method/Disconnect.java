package codetoon.method;

import java.util.HashMap;

import codetoon.system.Player;
import codetoon.util.*;
import codetoon.server.Server;
import org.jetbrains.annotations.NotNull;

public class Disconnect extends MyMethod{
    public Disconnect(){
    
    }
    @Override
    public Object newInstance(){
        return new Disconnect();
    }
    @Override
    public int getCount() {
        return 0;
    }
    
    @Override
    public String set(@NotNull HashMap<Integer, String> map){
        return null;
    };
    
    @Override
    public void action(Player host){
        System.out.println("disconnect");
        Server.server.end();
    };
}