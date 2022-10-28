package codetoon.util;

import java.util.*;
import codetoon.method.*;
import codetoon.system.CodeToon;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Indentification{
    private static final Indentification instance = new Indentification();
    StringBuilder program;
    StringBuilder parsent = null;
    ArrayList<StringBuilder> programs;
    ArrayList<MyMethod> method;
    private Indentification(String str){
        program = new StringBuilder();
        programs = new ArrayList<>();
        method = new ArrayList<>();
        program.append(str);
        indent();

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
        StringBuilder argments = new StringBuilder();
        HashMap<Integer, String> argment = new HashMap<>();
        if(parsent != null){
            argment.put(CodeToon.PARCENT_ARGUMENT, parsent.toString());
        }
        int  i = 0;
        boolean arg = false;
        while(i < text.length() - 1){


            if(arg){
                argments.append(text.charAt(i));
            }
            
            if(text.charAt(i) == '('){
                arg = true;
            }
            i ++;   
        }
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
                MyMethod temp = Methods.METHODS.get("method_" + getMethodName(stringBuilder));
                if (temp != null) {
                    temp.set(getArgument(stringBuilder));
                    method.add(temp);
                }
            }
        }
       // System.out.println(programs !=  null ? programs.get(0) : "None Method");
        
    }
    private String getMethodName(StringBuilder b){
        StringBuilder m = new StringBuilder();
        for(int i = 0; b.charAt(i) != '('; i ++){
            if(b.charAt(i) == '.'){
                parsent = m;
                m = new StringBuilder();
            }else {
                m.append(b.charAt(i));
            }
        }
        return m.toString();
    }
    private ArrayList<StringBuilder> getBuilder(StringBuilder program){

        StringBuilder b = new StringBuilder();
        ArrayList<StringBuilder> array = new ArrayList<StringBuilder>();
        for(int i = 0; i < program.length(); i ++){
            
            if(program.charAt(i) == ';'){
                array.add(b);
                b = new StringBuilder();
            }else{
                b.append(program.charAt(i));
            }

        }
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
    public static ArrayList<MyMethod> indentification(String program ){
        Indentification indent = new Indentification(program);
       // System.out.println(indent.programs == null ? "NonePrograms" : indent.programs.get(0).toString());
        return indent.method.isEmpty() ? null : indent.method;
        
    }
    public static Indentification getInstance(){
        return instance;
    }
}
