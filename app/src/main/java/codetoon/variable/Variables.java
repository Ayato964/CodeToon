package codetoon.variable;

import java.util.function.Supplier;
import codetoon.regi.*;
import codetoon.system.Admin;


public class Variables {
    public static final RegistoryList<Variable<?>> VARIABLE = new RegistoryList<>("variable");

    @Deprecated(since = "Beta.0.1.0")
    public static void createVariable(String id, Supplier<Variable<?>> sup){
        VARIABLE.createRegistory(id, sup);
    }   
    
}
