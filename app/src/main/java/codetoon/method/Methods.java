package codetoon.method;

import codetoon.method.getter.*;
import codetoon.method.setter.*;
import codetoon.method.voider.*;
import codetoon.regi.*;

public class Methods {
    public static final RegistoryList<MyMethod<?>> METHODS = new RegistoryList<>("methods");
    public static final RegistoryObject<MyMethod<?>> Attack = METHODS.createRegistory("method_attack", codetoon.method.voider.Attack::new);
    public static final RegistoryObject<MyMethod<?>> BLANK = METHODS.createRegistory("method_blank", Blank::new);
    public static final RegistoryObject<MyMethod<?>> CONNECT = METHODS.createRegistory("method_connect", Connect::new);
    public static final RegistoryObject<MyMethod<?>> CALL = METHODS.createRegistory("method_call", Call::new);
    public static final RegistoryObject<MyMethod<?>> DISCONNECT = METHODS.createRegistory("method_disconnect", Disconnect::new);
    public static final RegistoryObject<MyMethod<?>> END = METHODS.createRegistory("method_end", End::new);
    public static final RegistoryObject<MyMethod<?>> FOR = METHODS.createRegistory("method_for", For::new);
    public static final RegistoryObject<MyMethod<?>> GET = METHODS.createRegistory("method_get", Get::new);
    public static final RegistoryObject<MyMethod<?>> GET_W = METHODS.createRegistory("method_getW", GetW::new);
    public static final RegistoryObject<MyMethod<?>> GET_H = METHODS.createRegistory("method_getH", GetH::new);
    public static final RegistoryObject<MyMethod<?>> GET_BEFORE_PASS = METHODS.createRegistory("method_getBeforePass", GetBeforePass::new);
    public static final RegistoryObject<MyMethod<?>> GET_UP_MEMORY = METHODS.createRegistory("method_getUpMemory", GetUpMemory::new);
    public static final RegistoryObject<MyMethod<?>> GET_DOWN_MEMORY = METHODS.createRegistory("method_getDownMemory", GetDownMemory::new);
    public static final RegistoryObject<MyMethod<?>> GET_LEFT_MEMORY = METHODS.createRegistory("method_getLeftMemory", GetLeftMemory::new);
    public static final RegistoryObject<MyMethod<?>> GET_RIGHT_MEMORY = METHODS.createRegistory("method_getRightMemory", GetRightMemory::new);
    public static final RegistoryObject<MyMethod<?>> IF = METHODS.createRegistory("method_if", If::new);
    public static final RegistoryObject<MyMethod<?>> IS_HACKED = METHODS.createRegistory("method_isHacked", IsHacked::new);
    public static final RegistoryObject<MyMethod<?>> IS_LOCKED = METHODS.createRegistory("method_isLocked", IsLocked::new);
    public static final RegistoryObject<MyMethod<?>> IS_NORMAL_MEMORY = METHODS.createRegistory("method_isNormalMemory", IsNormalMemory::new);
    public static final RegistoryObject<MyMethod<?>> IS_SAVE_MEMORY = METHODS.createRegistory("method_isSaveMemory", IsSaveMemory::new);
    public static final RegistoryObject<MyMethod<?>> IS_HASH_MEMORY = METHODS.createRegistory("method_isHashMemory", IsHashMemory::new);

   // public static final RegistoryObject<MyMethod<?>> LOOP = METHODS.createRegistory("method_loop", Loop::new);
    public static final RegistoryObject<MyMethod<?>> PUT = METHODS.createRegistory("method_put", Put::new);
    public static final RegistoryObject<MyMethod<?>> SETPASS = METHODS.createRegistory("method_setPass", SetPass::new);
    public static final RegistoryObject<MyMethod<?>> SET_NORMAL_MODE = METHODS.createRegistory("method_setNormalMode", SetNormalMode::new);
    public static final RegistoryObject<MyMethod<?>> SET_SAVE_MODE = METHODS.createRegistory("method_setSaveMode", SetSaveMode::new);
    public static final RegistoryObject<MyMethod<?>> SET_HASH_MODE = METHODS.createRegistory("method_setHashMode", SetHashMode::new);
    public static final RegistoryObject<MyMethod<?>> PRINT = METHODS.createRegistory("method_print", Print::new);
    public static final RegistoryObject<MyMethod<?>> RANDOM = METHODS.createRegistory("method_random", Random::new);
    public static final RegistoryObject<MyMethod<?>> REMOVE = METHODS.createRegistory("method_remove", Remove::new);
    public static final RegistoryObject<MyMethod<?>> RECOVERY = METHODS.createRegistory("method_recovery", Recovery::new);
    public static final RegistoryObject<MyMethod<?>> RUN = METHODS.createRegistory("method_run", Run::new);

 //   public static final RegistoryObject<MyMethod<?>> STATES = METHODS.createRegistory("method_states", States::new);
}


