package codetoon.regi;

import codetoon.util.*;
import java.util.HashMap;
import java.util.function.Supplier;

public class RegistoryObject<T extends ContainerDataClass<?, HashMap<Integer, String>>> {
    private T t;
    private final String id;
    public RegistoryObject(String regiID, Supplier<T> sup){
        t = sup.get();
        id = regiID;
    }
    public T  newGet(){
        return (T) t.newInstance();
    }
    public T get(){
        return t;
    }
    public String getID(){
        return id;
    }
    public void run(){
        t.action(0);
    }
    
    public  void set(HashMap<Integer, String> map){
        if(t.getCount() != 0){
            t.set(map);
        }
    }
}
