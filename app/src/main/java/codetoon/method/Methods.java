package codetoon.method;

import codetoon.regi.*;

public class Methods {
    public static final RegistoryList<MyMethod<?>> METHODS = new RegistoryList<>("methods");
    public static final RegistoryObject<MyMethod<?>> Attack = METHODS.createRegistory("method_attack", Attack::new);
    public static final RegistoryObject<MyMethod<?>> CONNECT = METHODS.createRegistory("method_connect", Connect::new);
    public static final RegistoryObject<MyMethod<?>> DISCONNECT = METHODS.createRegistory("method_disconnect", Disconnect::new);
    public static final RegistoryObject<MyMethod<?>> END = METHODS.createRegistory("method_end", End::new);
    public static final RegistoryObject<MyMethod<?>> FOR = METHODS.createRegistory("method_for", For::new);
    public static final RegistoryObject<MyMethod<?>> GET_W = METHODS.createRegistory("method_getW", GetW::new);
    public static final RegistoryObject<MyMethod<?>> GET_H = METHODS.createRegistory("method_getH", GetH::new);
    public static final RegistoryObject<MyMethod<?>> IF = METHODS.createRegistory("method_if", If::new);
    public static final RegistoryObject<MyMethod<?>> LOOP = METHODS.createRegistory("method_loop", Loop::new);
    public static final RegistoryObject<MyMethod<?>> SETPASS = METHODS.createRegistory("method_setPass", SetPass::new);
    public static final RegistoryObject<MyMethod<?>> PRINT = METHODS.createRegistory("method_print", Print::new);
    public static final RegistoryObject<MyMethod<?>> RANDOM = METHODS.createRegistory("method_random", Random::new);
    public static final RegistoryObject<MyMethod<?>> REMOVE = METHODS.createRegistory("method_remove", Remove::new);
    public static final RegistoryObject<MyMethod<?>> RECOVERY = METHODS.createRegistory("method_recovery", Recovery::new);
    public static final RegistoryObject<MyMethod<?>> STATES = METHODS.createRegistory("method_states", States::new);
}


