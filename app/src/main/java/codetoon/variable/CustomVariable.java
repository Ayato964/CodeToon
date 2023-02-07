package codetoon.variable;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.argument.StringArgument;
import codetoon.system.CodeToon;
import codetoon.system.Player;

import java.util.HashMap;

public class CustomVariable<T> extends Variable<T>{
    T obj;
    String objString;
    String host;
    public CustomVariable(T t){
        obj = t;
    }
    public String getName(){
        return objString;
    }

    @Override
    public String set(HashMap<Integer, String> map) {
        host = map.get(CodeToon.HOST_MAP);
        objString = map.get(0);
        return null;
    }

    @Override
    public void action(Player host) {
        String o = objString;
        objString = null;
        if (obj instanceof Integer) {
            obj = (T) IntegerArgument.getInstance().indentification(o, host);
            //  System.out.println(obj);
        }
        if (obj instanceof String) {
            obj = (T) StringArgument.getInstance().indentification(o, host);
        }
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
    public T returnAction(Player host) {
        if(objString != null)
            action(host);
        return obj;
    }
}
