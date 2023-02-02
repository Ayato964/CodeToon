package codetoon.util.converter;

import codetoon.method.Methods;
import codetoon.method.MyMethod;
import codetoon.system.CodeToon;
import codetoon.system.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class ConvertMethod {

    @Contract(pure = true)
    public static @Nullable MyMethod convert(@NotNull String s, Player host){
        StringBuilder percent = null;
        StringBuilder builder = new StringBuilder();
        boolean begin = false;
        int beginCount = 0;
        for(int i = 0; i < s.length(); i ++){
            if(s.charAt(i) == '('){
                begin = true;
                beginCount ++;
            }
            if(s.charAt(i) == '.' && !begin){
                if(percent == null){
                    percent = builder;
                }else{
                    percent.append('.');
                    percent.append(builder);
                }
                builder = new StringBuilder();
            }else{
                builder.append(s.charAt(i));
            }
            if(s.charAt(i) == ')'){
                beginCount --;
                if(beginCount == 0)
                    begin = false;
            }
        }
        String me = builder.substring(0, builder.indexOf("("));
        if(Methods.METHODS.search("method_" + me))
            return addArgumentToMethod(Methods.METHODS.get("method_" + me), percent, s, host);
        else
            return null;

    }

    private static MyMethod addArgumentToMethod(MyMethod myMethod, StringBuilder percent, String s, @NotNull  Player host) {
        HashMap<Integer, String> argument = new HashMap<>();
        int beginArgument = 0;
        int endArgument = 0;
        int beginParent = 0;
        int endParent = 0;
        int parentCount = 0;
        if(percent != null)
            argument.put(CodeToon.PARCENT_ARGUMENT, percent.toString());
        argument.put(CodeToon.HOST_MAP, host.getID());
        for(int i = 0; i < s.length(); i ++){
            if(s.charAt(i) == ')'){
                parentCount --;
                if(parentCount == 0){
                    endArgument = i;
                }
            }
            if(s.charAt(i) == '('){
                parentCount ++;
                if(parentCount == 1){
                    beginArgument = i;
                }
            }
            if(s.charAt(i) == '}'){
                parentCount --;
                if (parentCount == 0)
                    endParent = i;
            }
            if(s.charAt(i) == '{'){
                parentCount ++;
                if (parentCount == 1)
                    beginParent = i;
            }
        }
        if(endParent - beginParent != 0)
            argument.put(CodeToon.INSIDE_METHODS, s.substring(beginParent + 1, endParent));
        if(endArgument - beginArgument != 0)
            ConvertArgument.convert(argument, s.substring(beginArgument + 1, endArgument));
        myMethod.set(argument);
        return myMethod;
    }
}
