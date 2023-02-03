package codetoon.argument;

import codetoon.method.MyMethod;
import codetoon.system.Player;
import codetoon.util.Expr;
import codetoon.util.converter.ConvertSource;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;

public class IntegerArgument extends Argument<Integer, String> {
    String calcEq = "+-/*";
    private IntegerArgument(){}

    private Integer convert(String i){
        StringBuilder s = new StringBuilder().append(i);
        boolean isAllInteger = true;
        for(int c = 0; c < s.length(); c ++){
            if(!(s.charAt(c) >= '0' && s.charAt(c) <= '9')){
                isAllInteger = false;
            }
        }
        if(isAllInteger){
            return Integer.parseInt(i);
        }else if(i.indexOf("(") != -1) {
            ArrayList<MyMethod> m = ConvertSource.convert(new StringBuilder().append(i).append(";").toString(), host);
            if(m.get(0).returnAction(host) instanceof Integer)
                return (Integer) m.get(0).returnAction(host);
            else
                return NOT_ARGUMENT;
        }else{
            return convertVariableTo(i) == null ? NOT_ARGUMENT : convertVariableTo(i);
        }
    }
    @Override
    public Integer indentification(String s) {
        if(isCalcEquation(s)) {
            s = convertAll(s);
            Expr e = new Expr(s);
            return e.expr(0);
        }
        return convert(s);
    }
    public Integer indentification(String s, Player p){
        host = p;
        return indentification(s);
    }
    public String convertAll(String s){
        StringBuilder equation = new StringBuilder();
        StringBuilder variable = new StringBuilder();
        for(int i = 0; i < s.length(); i ++) {
            if (!(s.charAt(i) >= '0' && s.charAt(i) <= '9')){
                if (isCalcEquation(s.charAt(i))) {
                    if(!variable.isEmpty()) {
                        int temp = getInstance().indentification(variable.toString(), host);
                        variable = new StringBuilder();
                        equation.append(temp);
                        equation.append(s.charAt(i));
                    }else{
                        equation.append(s.charAt(i));
                    }
                } else
                    variable.append(s.charAt(i));
            }else
                equation.append(s.charAt(i));
        }
        return equation.toString();
    }
    private boolean isCalcEquation(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    private boolean isCalcEquation(String s) {
        return s.indexOf("+") != -1 || s.indexOf("-") != -1 || s.indexOf("*") != -1 || s.indexOf("/") != -1;
    }

    @Contract(pure = true)
    public static IntegerArgument getInstance() {
        return new IntegerArgument();
    }

    @Override
    public Integer getSample() {
        return 1;
    }
}
