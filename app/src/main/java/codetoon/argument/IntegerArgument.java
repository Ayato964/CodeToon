package codetoon.argument;

import org.jetbrains.annotations.Contract;

public class IntegerArgument extends Argument<Integer, String> {
    private static final IntegerArgument instance = new IntegerArgument();
    private IntegerArgument(){}
    @Override
    public Integer indentification(String i) {
        StringBuilder s = new StringBuilder().append(i);
        boolean isAllInteger = true;
        for(int c = 0; c < s.length(); c ++){
            if(!(s.charAt(c) >= '0' && s.charAt(c) <= '9')){
                isAllInteger = false;
            }
        }
        if(isAllInteger){
            return Integer.parseInt(i);
        }else{
            return NOT_ARGUMENT;
        }
    }
    @Contract(pure = true)
    public static IntegerArgument getInstance() {
        return instance;
    }
}
