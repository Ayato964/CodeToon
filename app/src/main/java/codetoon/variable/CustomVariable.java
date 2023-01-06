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
    Player host;
    public CustomVariable(T t){
        obj = t;
    }
    public String getName(){
        return objString;
    }

    @Override
    public String set(HashMap<Integer, String> map) {
        host =(Player) ObjectArgument.getInstance().indentification(map.get(CodeToon.HOST_MAP));
        objString = map.get(0);
        if (obj instanceof Integer) {
           // System.out.println("Integer:" + objString);
            obj = (T) IntegerArgument.getInstance().indentification(objString, host);
          //  System.out.println(obj);
        }
        if (obj instanceof String) {
            obj = (T) StringArgument.getInstance().indentification(objString, host);
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
    public void action(Integer i) {
        if(objString != null) {
            if (obj instanceof Integer) {
               // System.out.println("Integer:" + objString + "    " + host.getID());
                obj = (T) IntegerArgument.getInstance().indentification(objString, host);
                System.out.println(obj);
            }
            if (obj instanceof String) {
                obj = (T) StringArgument.getInstance().indentification(objString, host);
            }
        }
    }

    @Override
    public T action() {
        return obj;
    }
}
