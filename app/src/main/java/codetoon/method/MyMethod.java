package codetoon.method;

import java.util.HashMap;

import codetoon.system.Player;
import codetoon.util.*;
import org.jetbrains.annotations.NotNull;

public abstract class MyMethod implements ContainerDataClass<String, HashMap<Integer, String>>{
    public MyMethod(){
    
    }
    /**
     * Create new Instance.
     * So return Object is "THIS".
     * **/
    @Override
    public abstract Object newInstance();
    /**
     * This Method is not using.
     * **/
    @Override
    public int getCount() {
        return 0;
    }
    /**
     * This Method set Arguments.
     * HashMap is coming ArgumentsCode(But String).
     * ID of Percent, Host and INSIDE is on CodeToon.java
     * **/
    @Override
    public abstract  String set(@NotNull HashMap<Integer, String> map);

    @Override
    public void action(int i){
        action(null);
    }
    public abstract void action(Player host);
}