package codetoon.method;

import codetoon.regi.*;

public class Methods {
    public static RegistoryList<MyMethod> METHODS = new RegistoryList<MyMethod>("methods");

    public static RegistoryObject<MyMethod> END = METHODS.createRegistory("method_end", End::new);

    public static RegistoryObject<MyMethod> LOG = METHODS.createRegistory("method_log", Log::new);

    public static RegistoryObject<MyMethod> CONNECT = METHODS.createRegistory("method_connect", Connect::new);
}


