package codetoon.util.converter;

import codetoon.method.PrivateNewVariable;
import codetoon.method.PrivateVariable;
import codetoon.system.CodeToon;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ConvertVariable {
    public static final int STRING = 0;
    public static final int INTEGER = 1;
    public static final int BOOLEAN = 2;
    public static final int MEMORY= 3;
    private static final int EXISTING = -1;
    public static PrivateVariable convert(String divide, Player host) {
        StringBuilder div = new StringBuilder().append(divide);
        int type = getType(div, 0);
        //System.out.println(type);
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
           // System.out.println("IS Already Variables");
                PrivateVariable pv = new PrivateVariable();
                pv.set(map);
                return pv;
        }

    //    return null;
    }
    private static HashMap<Integer, String> getMap(String id, ContainerVariable data, Player host){
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, id);
        map.put(1, data.variable);
        map.put(2, data.name);
        map.put(CodeToon.HOST_MAP, host.getID());
        return map;
    }
    public static int getType(@NotNull StringBuilder divide, int c){
        if(divide.charAt(c) == '=' || divide.indexOf("++") != -1 || divide.indexOf("--") != -1)
            return EXISTING;

        String type = divide.substring(0, c);
        if(type.equals("String"))
            return removeType(STRING, divide, 0, c);
        if(type.equals("int"))
            return removeType(INTEGER, divide, 0, c);
        if (type.equals("boolean"))
            return removeType(BOOLEAN, divide, 0, c);
        if(type.equals("Memory"))
            return removeType(MEMORY, divide, 0, c);
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
                convert(s, i, "+=");
                convert(s, i, "-=");
                convert(s, i, "/=");
                convert(s, i, "*=");

            }
        }
        private void convert(StringBuilder s, int i, String str){
            if(s.charAt(i) == '=') {
                if (s.substring(i - 1, i + 1).equals(str)) {
                    name = s.substring(0, i - 1);
                    variable = name + str.charAt(0);
                    variable += s.substring(i + 1, s.length());
                }
            }else if(s.charAt(i) == '+' || s.charAt(i) == '-'){
                StringBuilder eq = new StringBuilder().append(s.charAt(i)).append(s.charAt(i));
                int c = s.indexOf(eq.toString());
                if(c != -1){
                    StringBuilder one = new StringBuilder().append(1);
                    name = s.substring(0, c);
                    variable = name + s.charAt(i) + one;
                }


            }
        }
    }
}
