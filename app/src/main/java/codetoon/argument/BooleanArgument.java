package codetoon.argument;

import codetoon.method.MyMethod;
import codetoon.system.Admin;
import codetoon.system.Player;
import codetoon.util.converter.ConvertSource;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * <p>This class converts strings entered in the {@link codetoon.system.Console} to the Boolean type.</p>
 *
 * @since 1.7.0
 * @see Argument
 * @see codetoon.system.Console
 * @author Ayato
 */
public class BooleanArgument extends Argument<Boolean, String> {
    private static final int BEGIN = 0;
    private static final int END = 1;
    private static final int TERMS = 2;
    private BooleanArgument(){}

    @Override
    public Boolean indentification(String s) {

        return true;
    }

    @Override
    public Boolean getSample() {
        return true;
    }

/*
    private String[] getSplitEquation(String equation) {

    }

 */
    public Boolean indentification(String equation, Player host){
        this.host = host;
        ArrayList<String> str = new ArrayList<>();
    //    String[] splitEquation = getSplitEquation(equation);
        int c = 0;
        while (c <equation.length()){
            int index = equation.indexOf("||", c);
            if(index == -1) {
                str.add(equation.substring(c));
                break;
            }
            str.add(equation.substring(c, index));
            c = index + 2;
        }
        if(!str.isEmpty()) {
            String[] s = new String[str.size()];
            for (int i = 0; i < str.size(); i++) {
                s[i] = str.get(i);
            }
            return expr(s);
        }
        return expr(new String[]{equation});
    }


    public boolean expr(String @NotNull [] equations){
        for(int c = 0; c < equations.length; c ++){
            //System.out.println(equations[c] + "   SUC");
            String[] eq = equations[c].split("&&");
            for(int i = 0; i < eq.length; i ++) eq[i] = calc(eq[i]);
            if(eq.length == 1)
                equations[c] = eq[0];
            else {
                int count = 1;
                while (count < eq.length){
                    boolean a = eq[count - 1].equals("true");
                    boolean b = eq[count].equals("true");
                    StringBuilder t = new StringBuilder();
                    t.append(a && b);
                    eq[count] = t.toString();
                    count ++;
                }
                equations[c] = eq[count - 1];
            }
        }
        if(equations.length == 1)
            return equations[0].equals("true");
        else{
            int count = 1;
            while (count < equations.length){
                boolean a = equations[count - 1].equals("true");
                boolean b = equations[count].equals("true");
                StringBuilder t = new StringBuilder();
                t.append(a || b);
                equations[count] = t.toString();
                count ++;
            }
            return equations[count - 1].equals("true");
        }
    }

    public String calc(String str){
        int states = BEGIN;
        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        StringBuilder terms = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i < str.length(); i ++){

            if(str.charAt(i) == '<' || str.charAt(i) == '>'|| str.charAt(i) == '='|| str.charAt(i) == '!'){
                switch (states){
                    case BEGIN : states = TERMS;a.append(temp);temp = new StringBuilder();temp.append(str.charAt(i));break;
                    case TERMS: states = END;temp.append(str.charAt(i));terms.append(temp); temp = new StringBuilder();break;
                }
            }else{
                if(states == TERMS){
                    states = END;
                    terms.append(temp);
                    temp = new StringBuilder();
                }
                temp.append(str.charAt(i));
            }
        }
        if(states == END)
            b.append(temp);
        if(states == BEGIN)
            a.append(temp);

        if(b.isEmpty()){
            if(a.equals("true")|| a.equals("false"))
                return a.toString();
            else {
                if (a.indexOf("(") != -1) {
                    ArrayList<MyMethod> m = ConvertSource.convert(new StringBuilder().append(a).append(";").toString(), host);
                    return new StringBuilder().append(m.get(0).returnAction(host)).toString();
                } else
                    return new StringBuilder().append(convertVariableTo(a.toString())).toString();
            }
            }else{
            int intA = IntegerArgument.getInstance().indentification(a.toString(), host);
            int intB = IntegerArgument.getInstance().indentification(b.toString(), host);
            //System.out.println("A:" + intA + "  B:" + intB + "  term:" + terms);
            StringBuilder builder = new StringBuilder();
            if(terms.toString().equals("<="))
                builder.append(intA <= intB);
            if(terms.toString().equals(">="))
                builder.append(intA >= intB);
            if(terms.toString().equals("=="))
                builder.append(intA == intB);
            if(terms.toString().equals("<"))
                builder.append(intA < intB);
            if(terms.toString().equals(">"))
                builder.append(intA > intB);
            if(terms.toString().equals("!="))
                builder.append(intA != intB);
            return builder.toString();
        }
    }
    @Contract(pure = true)
    public static BooleanArgument getInstance() {return new BooleanArgument();}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("BOOLLL>");
        String s = br.readLine();
        boolean v = BooleanArgument.getInstance().indentification(s, Admin.getInstance());

        System.out.println("Answer:" + v);
    }
}
