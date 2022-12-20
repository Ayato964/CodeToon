package codetoon.util.converter;

import codetoon.argument.BooleanArgumet;
import codetoon.argument.IntegerArgument;
import codetoon.argument.StringArgument;
import codetoon.system.Player;
import codetoon.variable.CustomVariable;
import codetoon.variable.Variables;
import org.jetbrains.annotations.NotNull;

import javax.print.DocFlavor;
import java.util.HashMap;

public class ConvertVariable {
    private static final int STRING = 0;
    private static final int INTEGER = 1;
    private static final int BOOLEAN = 2;
    private static final int OBJECT= 3;
    private static final int EXISTING = -1;
    public static void convert(String divide, Player host) {
        StringBuilder div = new StringBuilder().append(divide);
        int type = getType(div, 0);
        int serial = host.getSerialID();
        ContainerVariable data = new ContainerVariable(div);
        HashMap<Integer, String> v = new HashMap<>();
        v.put(0, data.variable);
        String id = host.getID() + "_" + serial + "_" + data.name;
        switch (type){
            case STRING :
                Variables.VARIABLE.createRegistory(id, () ->
                        new CustomVariable<String>(StringArgument.getInstance().indentification(data.variable)));break;
            case INTEGER :
                Variables.VARIABLE.createRegistory(id, () ->
                        new CustomVariable<Integer>(IntegerArgument.getInstance().indentification(data.variable)));break;
            case BOOLEAN:
                Variables.VARIABLE.createRegistory(id, () ->
                        new CustomVariable<Boolean>(BooleanArgumet.getInstance().indentification(data.variable)));break;
            case EXISTING:
                if(Variables.VARIABLE.search(id))
                    Variables.VARIABLE.getThis(id).set(v);
                break;
        }

    }
    public static int getType(@NotNull StringBuilder divide, int c){
        if(divide.charAt(c) == '=')
            return EXISTING;

        String type = divide.substring(0, c);
        if(type.equals("String"))
            return removeType(STRING, divide, 0, c);
        if(type.equals("int"))
            return removeType(INTEGER, divide, 0, c);
        if (type.equals("boolean"))
            return removeType(BOOLEAN, divide, 0, c);
        return getType(divide, c + 1);
    }
    private static int removeType(int type, StringBuilder divide, int begin, int end){
        divide.delete(begin, end);
        return type;
    }

    static class ContainerVariable{
        String name;
        String variable;
        protected ContainerVariable(String n, String v){
            name = n;
            variable = v;
        }
        public ContainerVariable(StringBuilder s){
            for(int i = 0; i < s.length(); i ++){
                if(s.charAt(i) == '=') {
                    name = s.substring(0, i);
                    variable = s.substring(i + 1, s.length());
                }
            }
        }

    }
}
