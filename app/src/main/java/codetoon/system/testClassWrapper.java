package codetoon.system;
import java.io.Serializable;
import java.util.ArrayList;
public class testClassWrapper implements Serializable{
    ArrayList <Memory> memory;
    testClassWrapper(ArrayList <Memory> _memory){
        memory = _memory;
    }
}
