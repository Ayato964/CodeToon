package codetoon.method;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Blank  extends  MyMethod{
    @Override
    public void action(Object o) {

    }

    @Override
    public Object returnAction(Object o) {
        return null;
    }

    @Override
    public Object newInstance() {
        return new Blank();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        return null;
    }
}
