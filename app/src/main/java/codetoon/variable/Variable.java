package codetoon.variable;

import java.util.HashMap;
import codetoon.util.*;

public abstract class Variable<T> implements Actions<Integer, T>, Setter {
    public boolean isArray = setIsArray();


    public abstract boolean setIsArray();    
    @Override
    public void action(Integer i) {
       action(); 
    }
    public abstract T action();

    @Override
    public T returnAction(Integer integer) {
        return null;
    }
}
