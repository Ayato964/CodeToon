package codetoon.method;

import java.util.HashMap;

import codetoon.system.Player;
import codetoon.util.*;
import org.jetbrains.annotations.NotNull;

public abstract class MyMethod<T> implements Actions<Player, T>, Setter{
    public MyMethod(){
    
    }
    /**
     * Create new Instance.
     * So return Object is "THIS".
     * **/
    public abstract Object newInstance();
    /**
     * This Method is not using.
     * **/
    public int getCount() {
        return 0;
    }


    /**
     * This Method set Arguments.
     * HashMap is coming ArgumentsCode(But String).
     * ID of Percent, Host and INSIDE is on CodeToon.java
     * **/

    @Override
    public void action(Player host){};

    @Override
    public T returnAction(Player host){
        return null;
    }
}