package codetoon.variable;

import codetoon.argument.IntegerArgument;
import codetoon.argument.StringArgument;

import java.util.HashMap;

public class CustomVariable<T> extends Variable<T>{
    T obj;
    String objString;
    public CustomVariable(T t){
        obj = t;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public String set(HashMap<Integer, String> map) {

        objString = map.get(0);
        if (obj instanceof Integer) {
           // System.out.println("Integer:" + objString);
            obj = (T) IntegerArgument.getInstance().indentification(objString);
          //  System.out.println(obj);
        }
        if (obj instanceof String) {
            obj = (T) StringArgument.getInstance().indentification(objString);
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
    public void action(int i) {
        if(objString != null) {
            if (obj instanceof Integer) {
            //    System.out.println("Integer:" + objString);
                obj = (T) IntegerArgument.getInstance().indentification(objString);
                System.out.println(obj);
            }
            if (obj instanceof String) {
                obj = (T) StringArgument.getInstance().indentification(objString);
            }
        }
    }

    @Override
    public T action() {
        return obj;
    }
}
