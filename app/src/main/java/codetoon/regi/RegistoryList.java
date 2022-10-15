package codetoon.regi;

import codetoon.util.*;

import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.*;
public class RegistoryList<T extends ContainerDataClass<?,HashMap<Integer, String>>>{
    private String ID;
    private ArrayList<RegistoryObject<T>> registory;
    public RegistoryList(String ID){
        this.ID = ID;
        registory = new ArrayList<RegistoryObject<T>>();
        
    }
    public  RegistoryObject<T> createRegistory(String ID, Supplier<T> sup){
        RegistoryObject<T> obj = new RegistoryObject<T>(ID, sup);
        registory.add(obj);
        return obj;
    }
    public T get(String id){
        for(int i = 0; i < registory.size(); i ++){
            if(id.equals(registory.get(i).getID())){
                return  registory.get(i).newGet();
            }
        }
        return null;

    }
}
