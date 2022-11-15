package codetoon.method;

import java.awt.*;
import java.util.HashMap;
import codetoon.argument.*;
import codetoon.system.Message;

public class Print extends MyMethod{
    private String mes;
    @Override
    public void action(int i) {

        Message.addMessage(mes != null ? mes : "", Color.BLACK);
    }
    @Override
    public int getCount() {
        return 1;
    }
    @Override
    public String set(HashMap<Integer, String> map) {
        mes = StringArgument.getInstance().indentification(map.get(0));
        return map.get(0);
    }
    @Override
    public Object newInstance() {
        return new Print();
    }
}
