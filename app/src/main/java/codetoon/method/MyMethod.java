package codetoon.method;

import java.util.HashMap;

import codetoon.argument.BooleanArgument;
import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.argument.StringArgument;
import codetoon.system.Player;
import codetoon.util.*;
import org.jetbrains.annotations.NotNull;

public abstract class MyMethod<T> implements Actions<Player, T>, Setter{
    T t;
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
    protected Object changeToArgument(Player host, Object t, String a) {
        if(t.getClass() == "string".getClass())
            return new StringBuilder().append("\"").append(StringArgument.getInstance().indentification(a, host)).append("\"").toString();
        if(t.getClass() == Integer.class) {
            return IntegerArgument.getInstance().indentification(a, host);
        }
        if(t.getClass() == Boolean.class)
            return BooleanArgument.getInstance().indentification(a, host);
        return ObjectArgument.getInstance().indentification(a, host);
    }
}