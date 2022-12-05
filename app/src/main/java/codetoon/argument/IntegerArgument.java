package codetoon.argument;

import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.regi.RegistoryList;
import codetoon.system.Player;
import codetoon.variable.CustomVariable;
import codetoon.variable.Variable;
import codetoon.variable.Variables;
import org.jetbrains.annotations.Contract;

public class IntegerArgument extends Argument<Integer, String> {
    private IntegerArgument(){}
    @Override
    public Integer indentification(String i) {
        StringBuilder s = new StringBuilder().append(i);
        boolean isAllInteger = true;
        for(int c = 0; c < s.length(); c ++){
            if(!(s.charAt(c) >= '0' && s.charAt(c) <= '9')){
                isAllInteger = false;
            }
        }
        if(isAllInteger){
            return Integer.parseInt(i);
        }else{
            Player p = ((PazzleStage) Main.getInstance().getMap()).getConsole().getHost();
            String variable_ID = p.getID() + "_" + i;
            System.out.println(variable_ID);
            if(Variables.VARIABLE.search("variable_" + variable_ID)){
                Variable<?> re =  Variables.VARIABLE.get("variable_" + variable_ID);
                return (Integer) re.action();
            }else {
                return NOT_ARGUMENT;
            }
        }
    }
    @Contract(pure = true)
    public static IntegerArgument getInstance() {
        return new IntegerArgument();
    }
}
