package codetoon.variable;

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
    public String set(HashMap<Integer, String> integerStringHashMap) {
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
