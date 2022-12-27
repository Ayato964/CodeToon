package codetoon.method;

import java.awt.*;
import java.util.HashMap;
import codetoon.argument.*;
import codetoon.system.Message;

public class Print extends MyMethod{
    private String mes;
    private String map;
    @Override
    public void action(int i) {

        if(StringArgument.getInstance().indentification(map) == null)
            if(IntegerArgument.getInstance().indentification(map) == Argument.NOT_ARGUMENT)
                if(BooleanArgumet.getInstance().indentification(map) == null)
                    mes = map;
                else
                    mes = new StringBuilder().append(BooleanArgumet.getInstance().indentification(map)).toString();
            else
                mes = new StringBuilder().append(IntegerArgument.getInstance().indentification(map)).toString();
        else
            mes = new StringBuilder().append(StringArgument.getInstance().indentification(map)).toString();

        Message.addMessage(mes);
    }
    @Override
    public int getCount() {
        return 1;
    }
    @Override
    public String set(HashMap<Integer, String> map) {
        this.map = map.get(0);
        return map.get(0);
    }
    @Override
    public Object newInstance() {
        return new Print();
    }
}
