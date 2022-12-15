package codetoon.method;

import java.awt.*;
import java.util.HashMap;
import codetoon.argument.*;
import codetoon.system.Message;

public class Print extends MyMethod{
    private String mes;
    @Override
    public void action(int i) {
        Message.addMessage(mes);
    }
    @Override
    public int getCount() {
        return 1;
    }
    @Override
    public String set(HashMap<Integer, String> map) {

        if(StringArgument.getInstance().indentification(map.get(0)) == null){
            if(IntegerArgument.getInstance().indentification(map.get(0)) == Argument.NOT_ARGUMENT){
                if(BooleanArgumet.getInstance().indentification(map.get(0)) == null){
                    mes = map.get(0);
                }else{
                    mes = new StringBuilder().append(BooleanArgumet.getInstance().indentification(map.get(0))).toString();
                }
            }else {
                mes = new StringBuilder().append(IntegerArgument.getInstance().indentification(map.get(0))).toString();

            }
        }else {
            mes = new StringBuilder().append(StringArgument.getInstance().indentification(map.get(0))).toString();

        }
        return map.get(0);
    }
    @Override
    public Object newInstance() {
        return new Print();
    }
}
