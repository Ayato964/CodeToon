package codetoon.argument;

import codetoon.method.*;
import codetoon.system.Admin;
import codetoon.system.Player;
import codetoon.util.*;
import codetoon.util.converter.ConvertArgument;
import codetoon.util.converter.ConvertSource;
import codetoon.variable.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectArgument extends Argument<Object, String> {
    private static final ObjectArgument instance = new ObjectArgument();
    private String percent;
    private ObjectArgument(){}
    public Object indentification(String s, Player host){
        this.host = host;
        return indentification(s);
    }
    @Override
    public Object indentification(String s) {
        StringBuilder builder = new StringBuilder().append(s);
        int pi = search('.', builder);
        if(pi != -1){
            percent = builder.substring(0, pi);
            builder.delete(0, pi + 1);
        }else{
            percent = null;
        }

   //     System.out.println(builder);
        Object ObjName =
       isMethod(builder) == null ?
        isArgument(builder) == null ?
        !builder.toString().equals(Admin.getInstance().getID()) ? ERROR
        : Admin.getInstance()
        : isArgument(builder)
        : isMethod(builder);
        System.out.println(ObjName.toString() + "   " + s);
           if(!ObjName.toString().equals(ERROR.toString())){
                if(ObjName instanceof MyMethod){
                    ArrayList<MyMethod> m = ConvertSource.convert(new StringBuilder().append(builder).append(";").toString(), host);
                    return  m.get(0).returnAction(host);
                }
                
                if(ObjName instanceof Variable){
                    Variable<?> v = (Variable<?>) ObjName;
                   // System.out.println(ObjName.toString() + "   this isArray : " + v.isArray);
                    
                    if(v.isArray){
                        v.set(percent == null ? getVariable(builder) : getVariable(builder, percent));
                        return  v.action();
                    }
                    return  v.action();
                }
                if(ObjName instanceof Admin){
                    return  ObjName;
                }
           }else{
               return convertVariableTo(s);
           }
        return null;
    }

    public static ObjectArgument getInstance() {
        return new ObjectArgument();
    }
    @Override
    protected Object convertVariableTo(String s){
        //Player p = ((PazzleStage) Main.getInstance().getMap()).getConsole().getHost();
        String variable_ID = host.getID() + "_" + host.getSerialID() + "_" + s;
        //System.out.println(variable_ID + "    Argument");
        if(Variables.VARIABLE.search(variable_ID)){
            Variable<?> re =  Variables.VARIABLE.getThis(variable_ID);
            //return sample.getClass() == re.action().getClass() ? (T) re.action() : null;
            return   re.action();
        }else{
            return null;
        }
    }

    @Override
    public Object getSample() {
        return null;
    }
}
