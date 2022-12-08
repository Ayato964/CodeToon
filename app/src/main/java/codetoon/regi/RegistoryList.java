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
    public boolean search(String s){
        for(int i = 0; i < registory.size(); i ++){
            if(registory.get(i).getID().equals(s)){
                return true;
            }
        }
        return false;
    }
    public void deleteAll(String id){
        for(int i = 0; i < registory.size(); i ++){
            if(isInClude(registory.get(i).getID(), id)){
                registory.remove(i);
                i = 0;
            }
        }
    }
    private boolean isInClude(String id, String search){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < id.length(); i ++){
            s.append(id.charAt(i));
            if(s.toString().equals(search)){
                return true;
            }
        }
        return false;
    }
    public T get(String id){
        for(int i = 0; i < registory.size(); i ++){
            if(id.equals(registory.get(i).getID())){
                return  registory.get(i).newGet();
            }
        }
        return null;

    }
    public T getThis(String id){
        for(int i = 0; i < registory.size(); i ++){
            if(id.equals(registory.get(i).getID())){
                return  (T) registory.get(i).get();
            }
        }
        return null;

    }
}
