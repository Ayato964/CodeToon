package codetoon.util.converter;

import java.util.HashMap;

public class ConvertArgument {

    public static HashMap convert(HashMap<Integer, String> argument, String argumentStr) {
        int c = 0;
        boolean isEq = false;
        int countEq = 0;
        StringBuilder b = new StringBuilder();
        for(int i = 0; i < argumentStr.length(); i ++){
            if((argumentStr.charAt(i) == ',' || argumentStr.charAt(i) == ';') && !isEq){
                argument.put(c, b.toString());
                b = new StringBuilder();
                c ++;
            }else {
                b.append(argumentStr.charAt(i));
            }
            if(argumentStr.charAt(i) == '(') {
                isEq = true;
                countEq ++;
            }
            if(argumentStr.charAt(i) == ')'){
                countEq --;
                if(countEq == 0)
                    isEq = false;
            }
        }
        if(!b.isEmpty()){
            argument.put(c, b.toString());
        }
        return argument;
    }
}
