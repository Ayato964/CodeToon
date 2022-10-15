package codetoon.method;

import java.util.HashMap;

public class Print extends MyMethod {

    @Override
    public Object newInstance() {
        return new Print();
    }

    @Override
    public String set(HashMap<Integer, String> map) {
        return null;
    }

    @Override
    public void action(int i) {
        System.out.println("やっとできたお");
    }
    
}
