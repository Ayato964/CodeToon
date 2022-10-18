package codetoon.method;

import java.util.HashMap;
import codetoon.util.*;

public abstract class MyMethod implements ContainerDataClass<String, HashMap<Integer, String>>{
    public MyMethod(){
    
    }
    @Override
    public abstract Object newInstance();
    @Override
    public int getCount() {
        return 0;
    }
    
    @Override
    public abstract  String set(HashMap<Integer, String> map);
    
    @Override
    public abstract void action(int i);
}