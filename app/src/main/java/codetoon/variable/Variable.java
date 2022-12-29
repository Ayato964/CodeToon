package codetoon.variable;

import java.util.HashMap;
import codetoon.util.*;

public abstract class Variable<T> implements ContainerDataClass<String, HashMap<Integer, String>> {
    public boolean isArray = setIsArray();


    public abstract boolean setIsArray();    
    @Override
    public void action(int i) {
       action(); 
    }
    public abstract T action();
}
