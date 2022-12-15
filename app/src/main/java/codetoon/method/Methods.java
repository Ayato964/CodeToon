package codetoon.method;

import codetoon.regi.*;

public class Methods {
    public static RegistoryList<MyMethod> METHODS = new RegistoryList<MyMethod>("methods");

    public static RegistoryObject<MyMethod> END = METHODS.createRegistory("method_end", End::new);

    public static RegistoryObject<MyMethod> PRINT = METHODS.createRegistory("method_print", Print::new);

    public static RegistoryObject<MyMethod> LOOP = METHODS.createRegistory("method_loop", Loop::new);
    public static RegistoryObject<MyMethod> CONNECT = METHODS.createRegistory("method_connect", Connect::new);
    public static RegistoryObject<MyMethod> REMOVE = METHODS.createRegistory("method_remove", Remove::new);

    public static RegistoryObject<MyMethod> CALC = METHODS.createRegistory("method_calc", Calc::new);

    public static RegistoryObject<MyMethod> Lock = METHODS.createRegistory("method_lock", Lock::new);

    public static RegistoryObject<MyMethod> Attack = METHODS.createRegistory("method_attack", Attack::new);

    public static RegistoryObject<MyMethod> RECOVERY = METHODS.createRegistory("method_recovery", Recovery::new);

    public static RegistoryObject<MyMethod> IF = METHODS.createRegistory("method_if", If::new);
    public static RegistoryObject<MyMethod> STATES = METHODS.createRegistory("method_states", States::new);
}


