package codetoon.util.converter;

import codetoon.argument.BooleanArgumet;
import codetoon.argument.IntegerArgument;
import codetoon.argument.StringArgument;
import codetoon.method.PrivateNewVariable;
import codetoon.method.PrivateVariable;
import codetoon.system.CodeToon;
import codetoon.system.Player;
import codetoon.variable.CustomVariable;
import codetoon.variable.Variables;
import org.jetbrains.annotations.NotNull;

import javax.print.DocFlavor;
import java.util.HashMap;

public class ConvertVariable {
    public static final int STRING = 0;
    public static final int INTEGER = 1;
    public static final int BOOLEAN = 2;
    public static final int OBJECT= 3;
    private static final int EXISTING = -1;
    public static PrivateVariable convert(String divide, Player host) {
        StringBuilder div = new StringBuilder().append(divide);
        int type = getType(div, 0);
        int serial = host.getSerialID();
        ContainerVariable data = new ContainerVariable(div);
        String id = host.getID() + "_" + serial + "_" + data.name;
        HashMap<Integer, String> map = getMap(id, data, host);
        map.put(10, new StringBuilder().append(type).toString());

        if(type != EXISTING) {
            PrivateNewVariable pvs = new PrivateNewVariable();
            pvs.set(map);
            return pvs;
        }else {
            if (Variables.VARIABLE.search(id)) {
                PrivateVariable pv = new PrivateVariable();
                pv.set(map);
                return pv;
            }
        }

 /*
        switch (type){
            case ConvertVariable.STRING :
                Variables.VARIABLE.createRegistory(id, () ->
                        new CustomVariable<String>(StringArgument.getInstance().indentification(data.variable)));break;
            case ConvertVariable.INTEGER:
                Variables.VARIABLE.createRegistory(id, () ->
                        new CustomVariable<Integer>(IntegerArgument.getInstance().indentification(data.variable)));break;
            case ConvertVariable.BOOLEAN:
                Variables.VARIABLE.createRegistory(id, () ->
                        new CustomVariable<Boolean>(BooleanArgumet.getInstance().indentification(data.variable)));break;
            case EXISTING:
                if (Variables.VARIABLE.search(id)) {
                    PrivateVariable pv = new PrivateVariable();
                    pv.set(map);
                    return pv;
                }
        }
        */
        return null;
    }
    private static HashMap<Integer, String> getMap(String id, ContainerVariable data, Player host){
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, id);
        map.put(1, data.variable);
        map.put(CodeToon.HOST_MAP, host.getID());
        return map;
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

    public static class ContainerVariable{
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
