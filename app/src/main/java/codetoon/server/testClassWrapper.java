package codetoon.server;
import codetoon.system.Memory;
import codetoon.system.Rule;

import java.io.Serializable;
import java.util.ArrayList;
public class testClassWrapper implements Serializable{
    ArrayList <Memory> memory;
    Rule rule;
    testClassWrapper(ArrayList <Memory> _memory, Rule r){
        memory = _memory;
        rule = r;
    }
}
