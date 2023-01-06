package codetoon.method;

import java.awt.*;
import java.util.HashMap;
import codetoon.argument.*;
import codetoon.system.CodeToon;
import codetoon.system.Message;
import codetoon.system.Player;

public class Print extends MyMethod{
    private String mes;
    private String map;
    @Override
    public void action(Player host) {
        if(StringArgument.getInstance().indentification(map, host) == null)
            if(IntegerArgument.getInstance().indentification(map, host) == Argument.NOT_ARGUMENT)
                if(BooleanArgumet.getInstance().indentification(map, host) == null)
                    mes = map;
                else
                    mes = new StringBuilder().append(BooleanArgumet.getInstance().indentification(map, host)).toString();
            else
                mes = new StringBuilder().append(IntegerArgument.getInstance().indentification(map, host)).toString();
        else
            mes = new StringBuilder().append(StringArgument.getInstance().indentification(map, host)).toString();

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
