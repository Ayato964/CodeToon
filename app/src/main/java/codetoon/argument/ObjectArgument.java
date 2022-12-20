package codetoon.argument;

import codetoon.method.*;
import codetoon.system.Admin;
import codetoon.util.*;
import codetoon.variable.*;

public class ObjectArgument extends Argument<Object, String> {
    private static final ObjectArgument instance = new ObjectArgument();
    private String percent;
    private ObjectArgument(){}
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
      //  System.out.println(ObjName.getClass());
           if(ObjName.toString() != ERROR.toString()){
                if(ObjName instanceof MyMethod){
                    MyMethod method = (MyMethod) ObjName;
                    method.set(Indentification.getInstance().getArgument(builder));
                    return method;
                }
                
                if(ObjName instanceof Variable){
                    Variable<?> v = (Variable<?>) ObjName;
                   // System.out.println(ObjName.toString() + "   this isArray : " + v.isArray);
                    
                    if(v.isArray){
                        v.set(percent == null ? getVariable(builder) : getVariable(builder, percent));
                        return v.action();
                    }
                    return v.action();
                }
                if(ObjName instanceof Admin){
                    return ObjName;
                }
           }
        return null;
    }

    public static ObjectArgument getInstance() {
        return new ObjectArgument();
    }

    @Override
    public Object getSample() {
        return null;
    }
}
