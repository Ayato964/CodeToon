package codetoon.argument;

import java.util.ArrayList;


public class BooleanArgumet extends Argument<Boolean, String>{
    private static BooleanArgumet INSTANCE = new BooleanArgumet();

    private int begin = 0; //削除された範囲左の値まで;
    @Override
    public  Boolean indentification(String s) {
        StringBuilder program = new StringBuilder().append(s);
        boolean answer = getInBrackets(program).booleanValue();
        return answer;
    }

    public static BooleanArgumet getInstance() {
        return new BooleanArgumet();
    }
    private StringBuilder getCalcAnd(StringBuilder s){
        boolean before;
        boolean after;
        int bracketCount = 0;
        boolean isBracket = false;
        for(int i = 0; i < s.length() - 1; i ++){
            if(s.charAt(i) == '('){
                isBracket = true;
                bracketCount ++;
            }
            if(s.charAt(i) == ')'){
                bracketCount --;
                if(bracketCount == 0)
                    isBracket = false;
            }

            /** && is must **/
            if(s.charAt(i) == '&'){
                if(isBooleanAnd(s.substring(i, i + 2)) && !isBracket){
                    before = getUnderBool(s, i - 1);
                    System.out.println("Before::" + before);
                    after = getUpperBool(s, i + 1 - begin);
                    System.out.println("After::" + after);
                    if(before && after){
                        s.replace(i - begin - 1, i + 1 - begin, "true");
                        System.out.println("Answer:" + s);
                    }else{
                        s.replace(i - begin - 1, i + 1 - begin, "false");
                        System.out.println("Answer:" + s);
                    }
                }
                i = 0;
                begin = 0;
            }
        }
        return s;
    }
    private StringBuilder getCalcOr(StringBuilder s){
        boolean before;
        boolean after;
        int bracketCount = 0;
        boolean isBracket = false;
        for(int i = 0; i < s.length() - 1; i ++){
            if(s.charAt(i) == '('){
                isBracket = true;
                bracketCount ++;
            }
            if(s.charAt(i) == ')'){
                bracketCount --;
                if(bracketCount == 0)
                    isBracket = false;
            }

            /** && is must **/
            if(s.charAt(i) == '|'){
               // System.out.println("isOR");
                if(isBooleanOR(s.substring(i, i + 2)) && !isBracket){
                    before = getUnderBool(s, i - 1);
                    after = getUpperBool(s, i + 1 - begin);
                    if(before || after){
                        s.replace(i - begin - 1, i + 1 - begin, "true");
                    }else{
                        s.replace(i - begin - 1, i + 1  - begin, "false");
                    }
                }
                i = 0;
                begin = 0;
            }
        }
        return s;
    }
    private Boolean getInBrackets(StringBuilder s) {
        s = getCalcAnd(s);
        System.out.println(s);
        s = getCalcOr(s);
        return getBoolean(s);
    }
    private boolean getUpperBool(StringBuilder s, int count) {
        StringBuilder b = new StringBuilder();
        int bracketCount = 0;
        int begin = 0;
        int end = 0;
        boolean isBracket = false;
        for(int i = count; i < s.length(); i ++){
            if(!isBooleanDeadOperater(s.charAt(i))) {
                b.append(s.charAt(i));
            }else if(s.charAt(i) == '(') {
                if(!isBracket) {
                    begin = i + 1;
                }else {
                    bracketCount ++;
                }
                isBracket = true;
            }else if (s.charAt(i) == ')') {
                if(bracketCount == 0){
                    isBracket = false;
                    end = i - 1;
                }
                bracketCount --;
            }else{
                break;
            }
        }
        if(end != 0){
            return new BooleanArgumet().indentification(s.substring(begin, end));
        }
        s.delete(count, count + b.length());
        System.out.println(s);
        return getBoolean(b);
    }

    private boolean getUnderBool(StringBuilder s, int count) {
        StringBuilder b = new StringBuilder();
        int c = 0;
        int bracketCount = 0;
        int begin = 0;
        int end = 0;
        boolean isBracket = false;

        for(int i = count; i >= 0; i --){
            if(!isBooleanDeadOperater(s.charAt(i))) {
                b.append(s.charAt(i));
            }else if(s.charAt(i) == ')') {
                if(!isBracket){
                    end = i - 1;
                }else{
                    bracketCount ++;
                }
                isBracket = true;
            } else if (s.charAt(i) == '(') {
                if(bracketCount == 0){
                    begin = i + 1;
                    isBracket = false;
                }else{
                    bracketCount --;
                }
            }else{
            break;
            }

            c = i;
        }
        if(end != 0){
            return new BooleanArgumet().indentification(s.substring(begin, end));
        }
        s.delete(c, count + 1);
        System.out.println(s);
        this.begin = count - c;

        b = getSortUnderToUpper(b);
        return getBoolean(b);
    }

    private boolean getBoolean(StringBuilder s) {
        for(int i = 0; i < s.length() - 1; i ++){
            if (isBooleanOperater(s.substring(i, i + 2))) {
                if(s.substring(i, i + 2).equals("<=")) {
                    return (Integer) this.getValue(s, 0) <= (Integer) this.getValue(s, i + 2);
                }
                if(s.substring(i, i + 2).equals(">=")) {
                    return (Integer) this.getValue(s, 0) >= (Integer) this.getValue(s, i + 2);
                }
                if(s.substring(i, i + 2).equals("==")) {
                    Object o1 = this.getValue(s, 0);
                    Object o2 = this.getValue(s, i + 2);
                    if(o1 instanceof Integer){
                        return ((Integer) o1).intValue() == ((Integer)o2).intValue();
                    }
                        return (o1).equals(o2);
                }
                if(s.substring(i, i + 2).equals("!=")){
                    Object o1 = this.getValue(s, 0);
                    Object o2 = this.getValue(s, i + 2);
                    if(o1 instanceof Integer){
                        return ((Integer) o1).intValue() != ((Integer)o2).intValue();
                    }
                    return !(o1).equals(o2);
                }
            }else if(isBooleanOperater(s.charAt(i))){
                return  switch(s.charAt(i)){
                    case '<' -> (Integer)this.getValue(s,0) < (Integer)this.getValue(s,i + 1);
                    case '>' -> (Integer)this.getValue(s,0) > (Integer) this.getValue(s,i + 1);
                    default -> false;
                };
            }
        }
        return (boolean) getValue(s, 0);
    }

    private Object getValue(StringBuilder b, int i) {
        StringBuilder value = new StringBuilder();
        for (int c = i; c < b.length(); c++) {
            if (!(isBooleanOperater(b.charAt(c)) || (c + 1 < b.length() && isBooleanOperater(b.substring(c, c + 2))))) {
                value.append(b.charAt(c));
            }else{
                break;
            }
        }
        if (value.toString().equals("true")) {
            return true;
        } else if (value.toString().equals("false")) {
            return false;
        }else{
            Integer integer = IntegerArgument.getInstance().indentification(value.toString());
           // System.out.println(integer);
            if(integer == NOT_ARGUMENT){
                String s = StringArgument.getInstance().indentification(value.toString());
                if(s != null){
                    return s;
                }
            }else{
                return integer;
            }
        }
        return null;
    }

    private boolean isBooleanOperater(char charAt){
        return charAt == '<' || charAt == '>';
    }
    private boolean isBooleanOperater(String charAt){
        return charAt.equals("<=") || charAt.equals(">=") || charAt.equals("==") || charAt.equals("!=");
    }

    private StringBuilder getSortUnderToUpper(StringBuilder b) {
        StringBuilder newBuilder = new StringBuilder();
        for(int i = b.length() - 1; i >= 0; i --){
            newBuilder.append(b.charAt(i));
        }
        return newBuilder;
    }


    private boolean isBooleanDeadOperater(char charAt) {
        return charAt == '&' || charAt == '|' || charAt == '!' || charAt == ')' || charAt == '(';
    }

    private boolean isBooleanAnd(String s) {
        return s.equals("&&");
    }
    private boolean isBooleanOR(String s){
        return s.equals("||");
    }

    @Override
    public Boolean getSample() {
        return true;
    }
}
