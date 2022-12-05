package codetoon.util;

import java.util.*;

import codetoon.argument.BooleanArgumet;
import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.argument.StringArgument;
import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.method.*;
import codetoon.system.CodeToon;
import codetoon.system.Console;
import codetoon.system.Player;
import codetoon.variable.CustomVariable;
import codetoon.variable.VariableMode;
import codetoon.variable.Variables;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Indentification{
    private static final Indentification instance = new Indentification();
    StringBuilder program;
    StringBuilder parsent = null;
    ArrayList<StringBuilder> programs;
    ArrayList<MyMethod> method;
    private Player host;
    private Indentification(String str, Player host){
        program = new StringBuilder();
        programs = new ArrayList<>();
        method = new ArrayList<>();
        program.append(str);
        this.host = host;
        indent();
        System.out.println(method);
    }
    private Indentification(){

    }

    @Contract("_ -> param1")
    public static @NotNull StringBuilder removeEnd(@NotNull StringBuilder source) {
        int v = source.indexOf("end(");
        int ev = source.indexOf(";", v);
        source.delete(v, ev + 1);
        return source;
    }

    public HashMap<Integer, String> getArgument(StringBuilder text){
        StringBuilder inside = new StringBuilder();
        StringBuilder argments = new StringBuilder();
        HashMap<Integer, String> argment = new HashMap<>();

        if(parsent != null){
            System.out.println(parsent);
            argment.put(CodeToon.PARCENT_ARGUMENT, parsent.toString());
        }

        int  i = 0;
        boolean arg = false;
        boolean isInside = false;
        while(i < text.length() - 1){
            if(text.charAt(i) == ')'){
                arg = false;
            }

            if(isInside){
                inside.append(text.charAt(i));
            }
            if(arg && !isInside){
                argments.append(text.charAt(i));
            }
            
            if(text.charAt(i) == '('){
                arg = true;
            }
            if(text.charAt(i) == '{'){
                isInside = true;
            }
            i ++;   
        }
        argment.put(CodeToon.INSIDE_METHODS, inside.toString());
        argment.put(CodeToon.HOST_MAP, host.getID());
        int c = 0;
        StringBuilder temp = new StringBuilder();
        for(int  z= 0; z < argments.length(); z++ ){
            if(argments.charAt(z) == ','){
                argment.put(c, temp.toString());
                temp = new StringBuilder();
                c ++;
            }else{
                temp.append(argments.charAt(z));
            }
            if(z + 1 >= argments.length()){
                argment.put(c, temp.toString());
            }
        }
        return argment.isEmpty() ? null : argment;
    }
    private void indent(){
        program = removeSpace(program);
        programs = getBuilder(program);

        if(programs != null){
            for (StringBuilder stringBuilder : programs) {
                if(isMethod(stringBuilder)) {
                    MyMethod temp = Methods.METHODS.get("method_" + getMethodName(stringBuilder));
                    if (temp != null) {
                        temp.set(getArgument(stringBuilder));
                        method.add(temp);
                    }
                }else{
                    convertVariable(stringBuilder.toString());
                }
            }
        }
       // System.out.println(programs !=  null ? programs.get(0) : "None Method");
        
    }
    private boolean isMethod(@NotNull StringBuilder s){
        boolean a = false;
        StringBuilder t = new StringBuilder();
        for(int i = 0; i < s.length(); i ++){
            if(s.charAt(i) == '.'){
                if(parsent == null) {
                    parsent = t;
                }else{
                    parsent.append('.');
                    parsent.append(t);
                }
                s.delete(0, i  + 1);
                t = new StringBuilder();
                i = -1;
            }else if(s.charAt(i) == '('){
                if(Methods.METHODS.get("method_" + t.toString()) != null){
                    a = true;
                }
            }else{
                t.append(s.charAt(i));
            }
        }
        //System.out.println(t);
        return a;
    }
    public static void convertVariable(String s){
        StringBuilder variable = new StringBuilder().append(s);
        String variable_name = "";
        VariableMode states = null;
        for(int i = 0; i < variable.length(); i ++){

            if(variable.substring(0, i).equals("String")){
                states = VariableMode.STRING;
                variable.delete(0, i);
                i = 0;

            }
            if(variable.substring(0, i).equals("int")){
                states = VariableMode.INT;
                variable.delete(0, i);
                i = 0;
            }
            if(variable.substring(0, i).equals("boolean")){
                states = VariableMode.BOOLEAN;
                variable.delete(0, i);
                i = 0;
            }

            if(variable.charAt(i) == '='){
                variable_name = variable.substring(0, i);
                variable.delete(0, i + 1);
                i = 0;
            }
        }
        Player p = ((PazzleStage) Main.getInstance().getMap()).getConsole().getHost();
        String variable_ID = p.getID() + "_" + variable_name;
        System.out.println("variable_" + variable_ID);
        if(Variables.VARIABLE.search(variable_ID)){
            HashMap<Integer, String> h = new HashMap<>();
            h.put(0, variable.toString());
            Variables.VARIABLE.get(variable_ID).set(h);
        }else {
            switch (states) {
                case INT:
                    Variables.createVariable(variable_ID, () ->
                            new CustomVariable<Integer>(IntegerArgument.getInstance().indentification(
                                    variable.toString())));
                    break;
                case STRING:
                    Variables.createVariable(variable_ID, () ->
                            new CustomVariable<String>(StringArgument.getInstance().indentification(variable.toString())));
                    break;
                case BOOLEAN:
                    Variables.createVariable(variable_ID, () ->
                            new CustomVariable<Boolean>(BooleanArgumet.getInstance().indentification(variable.toString())));
                    break;
            }
        }
    }
    private String getMethodName(StringBuilder b){
        StringBuilder m = new StringBuilder();
        for(int i = 0; b.charAt(i) != '('; i ++){

            m.append(b.charAt(i));
        }
        return m.toString();
    }
    private ArrayList<StringBuilder> getBuilder(StringBuilder program){

        StringBuilder b = new StringBuilder();
        ArrayList<StringBuilder> array = new ArrayList<StringBuilder>();
        boolean isPrivate = false;
        int count = 0;
        for(int i = 0; i < program.length(); i ++) {
            if (program.charAt(i) == '{') {
                if(isPrivate) {
                    count ++;
                }else{
                    isPrivate = true;
                }
            }
            if( program.charAt(i) == '}'){
                if(count == 0) {
                    isPrivate = false;
                }else{
                    count --;
                }
            }
            if ((program.charAt(i) == ';' || program.charAt(i) == '}') && !isPrivate) {
                if(program.charAt(i) == '}')
                    b.append(program.charAt(i));
                array.add(b);
                b = new StringBuilder();
            } else {
                b.append(program.charAt(i));
            }
        }
        System.out.println(array);
        return array.isEmpty() ? null : array;
    }


    private StringBuilder removeSpace(StringBuilder str){
        for(int i = 0; i < str.length(); i ++){
            if(str.charAt(i) == ' ' || str.charAt(i) == '\n'){
                str.deleteCharAt(i);
                i = 0;
            }
        }
        return str;
    }
    public static ArrayList<MyMethod> indentification(String program, Player host ){
        Indentification indent = new Indentification(program, host);
       // System.out.println(indent.programs == null ? "NonePrograms" : indent.programs.get(0).toString());
        return indent.method.isEmpty() ? null : indent.method;
        
    }
    public static Indentification getInstance(){
        return instance;
    }
}
