package codetoon.variable;

import java.util.function.Supplier;
import codetoon.regi.*;
import codetoon.system.Admin;


public class Variables {
    public static final RegistoryList<Variable<?>> VARIABLE = new RegistoryList<>("variable");

    public static void createVariable(String id, Supplier<Variable<?>> sup){
        VARIABLE.createRegistory("variable_" + id, sup);
    }   
    
}
