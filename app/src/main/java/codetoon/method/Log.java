package codetoon.method;

import java.util.HashMap;
import codetoon.argument.*;
public class Log extends MyMethod{
    private String mes;
    @Override
    public void action(int i) {
        System.out.println(mes != null ? mes : "");
    }
    @Override
    public int getCount() {
        return 1;
    }
    @Override
    public String set(HashMap<Integer, String> map) {
        mes = new StringArgument().indentification(map.get(0));
        return map.get(0);
    }
    @Override
    public Object newInstance() {
        return new Log();
    }
}
