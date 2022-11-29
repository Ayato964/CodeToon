package codetoon.argument;

import java.util.HashMap;
import codetoon.method.*;
import codetoon.system.Memorys;
import codetoon.variable.*;

public abstract class Argument<T, I>  {
    public static final int NOT_ARGUMENT = -99999;
    protected final StringBuilder ERROR = new StringBuilder().append("sgsihgrgmkwrgtkrthhjthmmlghmghmls");
    protected Argument(){

    } 
    protected int serch(char n, StringBuilder obj){
        for(int i = 0; i < obj.length(); i ++){
            if(obj.charAt(i) == n){
                return i;
            }
        }
        return -1;
    } 
    protected MyMethod isMethod(StringBuilder data){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i< data.length(); i ++){
            if(data.charAt(i) != '('){
                builder.append(data.charAt(i));
                
            }else{
                break;
            }

        }

        return Methods.METHODS.get("method_" + builder.toString());
    }
    protected Variable<?> isArgument(StringBuilder data){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < data.length(); i ++){
            if(data.charAt(i) != '['){
                builder.append(data.charAt(i));
            }else{
                break;
            }
        }
        return Variables.VARIABLE.get("variable_" +  builder.toString());
    }
    protected HashMap<Integer, String> getVariable(StringBuilder data, String percent){
        HashMap<Integer, String> t = getVariable(data);
        t.put(2, percent);
        return t;
    }
    protected HashMap<Integer, String> getVariable(StringBuilder data){
        StringBuilder v = new StringBuilder();
        HashMap<Integer, String> temp = new HashMap<>();
        boolean is = false;
        int c = 0;
        for(int i = 0; i < data.length(); i ++){
            
            if(data.charAt(i) == ']'){
                temp.put(c, v.toString());
                c ++;
                v = new StringBuilder();
                is = false;
            }

            if(is){
                v.append(data.charAt(i));
            }

            if(data.charAt(i) == '['){
                is = true;
            }

        }
        return temp.isEmpty() ? null : temp;
    }
    public abstract  T indentification(I i);
}
