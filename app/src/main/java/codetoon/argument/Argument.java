package codetoon.argument;

import java.util.HashMap;

import codetoon.method.*;
import codetoon.system.CodeToon;
import codetoon.system.Player;
import codetoon.variable.*;

/**
 * <p>This class converts strings entered in the {@link codetoon.system.Console} to the appropriate type.</p>
 *
 * <p>Since this class is an abstract type, use {@link IntegerArgument}, {@link StringArgument}, {@link BooleanArgument}  or {@link ObjectArgument}.</p>
 * When inheriting, write a program to convert to {@link  #indentification}.
 * Also, {@link  #getSample} is used for comparison in the convertVariableTo function.
 * Please store the object you wish to convert as a sample.
 * @since Beta.0.4.0
 * @see codetoon.system.Console
 * @see IntegerArgument
 * @see StringArgument
 * @see BooleanArgument
 * @see ObjectArgument
 * @author Ayato
 * @param <T> Output type
 * @param <I> Input type
 */
public abstract class Argument<T, I>  {
    T sample = getSample();
    protected Player host;
    public static final int NOT_ARGUMENT = -99999;
    protected final StringBuilder ERROR = new StringBuilder().append("sgsihgrgmkwrgtkrthhjthmmlghmghmls");
    protected Argument(){
        //host = ((PazzleStage) Main.getInstance().getMap()).getConsole().getHost();
    } 
    protected int search(char n, StringBuilder obj){
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

        return Methods.METHODS.get("method_" + builder);
    }
    protected T convertVariableTo(String s){
        //Player p = ((PazzleStage) Main.getInstance().getMap()).getConsole().getHost();
        String variable_ID = host.getID() + "_" + host.getSerialID() + "_" + s;
        //System.out.println(variable_ID + "    Argument");
        if(Variables.VARIABLE.search(variable_ID)){
            Variable<?> re =  Variables.VARIABLE.getThis(variable_ID);
            return sample.getClass() == re.returnAction(host).getClass() ? (T) re.returnAction(host) : null;
            //return  (T) re.action();
        }else{
            return null;
        }
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
        return Variables.VARIABLE.get(builder.toString());
    }
    protected HashMap<Integer, String> getVariable(StringBuilder data, String percent){
        HashMap<Integer, String> t = getVariable(data);
        t.put(2, percent);
        return t;
    }
    protected HashMap<Integer, String> getVariable(StringBuilder data){
        StringBuilder v = new StringBuilder();
        HashMap<Integer, String> temp = new HashMap<>();
        temp.put(CodeToon.HOST_MAP, host.getID());
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
    public abstract T getSample();
}
