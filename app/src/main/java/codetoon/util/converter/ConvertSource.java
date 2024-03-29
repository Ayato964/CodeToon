package codetoon.util.converter;

import codetoon.method.*;
import codetoon.method.voider.For;
import codetoon.system.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class ConvertSource {

    @Contract(pure = true)
    public static @Nullable ArrayList<MyMethod> convert(String s, Player host){
        ArrayList<MyMethod> methods = new ArrayList<>();
        StringBuilder b = new StringBuilder().append(s);
        return divideConvert(methods, removeVoid(b, 0), 0, host);
    }
    private static @NotNull StringBuilder removeVoid(@NotNull StringBuilder b, int i){
        if(b.charAt(i) == ' ' || b.charAt(i) == '\n')
            return removeVoid(b.deleteCharAt(i), 0);
        if(i + 1 < b.length())
            return removeVoid(b, i + 1);

        return b;
    }
    private static ArrayList<MyMethod> divideConvert(ArrayList<MyMethod> methods, StringBuilder b, int c, Player host){
        boolean isParent = false;
        boolean isBigParent = false;
        int parentCount = 0;
        for(int i = c; i < b.length(); i ++){
            if(b.charAt(i) == '('){
                parentCount ++;
                isParent = true;
            }
            if( b.charAt(i) == '{'){
                parentCount ++;
                isBigParent = true;
            }
            if(b.charAt(i) == '}'){
                parentCount--;
                if(parentCount == 0){
                    return divideConvert(convertAll(methods, b.substring(c, i + 1), host), b, i + 1, host);
                }
            }
            if(b.charAt(i) == ';' && !isParent && !isBigParent){
                //System.out.println(b.substring(c, i));
                return divideConvert(convertAll(methods, b.substring(c, i), host), b, i + 1, host);
            }
            if(b.charAt(i) == ')'){
                parentCount --;
                if(parentCount == 0)
                    isParent = false;
            }
        }
        return methods;
    }
    private static ArrayList<MyMethod> convertAll(ArrayList<MyMethod> methods, String divide, Player host){
        if(isMethod(divide)) {
            methods.add(ConvertMethod.convert(divide, host));
        }else{
            PrivateVariable v = ConvertVariable.convert(divide, host);
            if(v != null) {
                methods.add(v);
            }

        }
        return methods;
    }
    private static boolean isMethod(String s){
        StringBuilder builder = new StringBuilder().append(s);
        boolean isParent = false;
        for(int i = 0; i < builder.length(); i ++){
            if(builder.charAt(i) == '=' && !isParent){
                return false;
            }
            if(builder.charAt(i) == '('){
                isParent = true;
            }
        }
        return (s.indexOf("++") == -1 && s.indexOf("(") != -1) || s.indexOf("(") != -1;
    }
    public static boolean OnEndMethod(String s){
        return s.indexOf("end();") != -1;
    }
    public static boolean OnRemoveMethod(String s){
        return s.indexOf("remove();") != -1;
    }
    public static boolean OnCallMethod(String s){
        return s.indexOf("call(") != -1;
    }

    public static int getMethodCount(ArrayList<MyMethod> methods, Player host) {
        int have = isHave(Methods.FOR.get(), methods);
        int run = isHave(Methods.CALL.get(), methods);
        if(run != -1)
            return 3;
        if( have != -1)
            return methods.size() + getMethodCount(convert(((For) methods.get(have)).inside, host), host);


        return methods.size() - (isHave(new PrivateVariable(), methods) != -1 ? 1 : 0) - (isHave(new PrivateNewVariable(), methods) != -1 ? 1 : 0);
    }
    public static int isHave(MyMethod s, ArrayList<MyMethod> m){
        for(int i = 0; i < m.size(); i ++){
            if(s.getClass() == m.get(i).getClass())
                return i;
        }
        return -1;
    }
}
