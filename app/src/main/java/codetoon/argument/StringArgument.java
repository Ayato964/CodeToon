package codetoon.argument;


import codetoon.method.MyMethod;
import codetoon.system.Player;
import codetoon.util.converter.ConvertSource;

import java.util.ArrayList;
/**
 *   <p>This class converts strings entered in the {@link codetoon.system.Console} to the String type.</p>
 *
 * @since Beta.0.4.0
 * @see Argument
 * @see codetoon.system.Console
 * @author Ayato
 */
public class StringArgument extends Argument<String, String> {
    private static final StringArgument instance= new StringArgument();
    private StringArgument(){}
    @Override
    public String indentification(String s) {
        StringBuilder builder = new StringBuilder().append(s);
        if(builder.charAt(0) == '\"'){
             return convertStringAll(builder);
        }else if(builder.indexOf("(") != -1) {
            ArrayList<MyMethod> m = ConvertSource.convert(builder.append(";").toString(), host);
            if(!m.isEmpty())
                if (m.get(0) != null)
                    return (String) m.get(0).returnAction(host);
                else return "";
                else return "";

        }else{
            return convertVariableTo(s) == null ? "" : convertVariableTo(s);
        }
    }
    public String indentification(String s, Player p){
        host = p;
        return indentification(s);
    }

    public String convertStringAll(StringBuilder s){
        StringBuilder t = new StringBuilder();
        for(int i = 1; i < s.length(); i ++){
            if(s.charAt(i) != '\"'){
                t.append(s.charAt(i));
            }
        
        }
        return t.toString();
    }

    public static StringArgument getInstance() {
        return new StringArgument();
    }

    @Override
    public String getSample() {
        return "";
    }
}
