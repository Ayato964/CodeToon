package codetoon.variable;

import codetoon.argument.IntegerArgument;
import codetoon.argument.StringArgument;

import java.util.HashMap;

public class CustomVariable<T> extends Variable<T>{
    T obj;
    public CustomVariable(T t){
        obj = t;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public String set(HashMap<Integer, String> map) {
        if(obj instanceof Integer){
            obj = (T) IntegerArgument.getInstance().indentification(map.get(0));
        }
        if(obj instanceof String){
            obj = (T) StringArgument.getInstance().indentification(map.get(0));
        }
        return null;
    }

    @Override
    public Object newInstance() {
        return new CustomVariable<T>(obj);
    }

    @Override
    public boolean setIsArray() {
        return false;
    }

    @Override
    public T action() {
        return obj;
    }
}
