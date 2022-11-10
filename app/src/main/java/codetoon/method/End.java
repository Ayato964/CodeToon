package codetoon.method;

import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class End extends MyMethod {

    public End() {
    }
    @Override
    public void action(Player host) {
        System.out.println("program executed!!");
    }
    @Override
    public String set(@NotNull HashMap<Integer, String> map) {

        return null;
    }
    @Override
    public Object newInstance() {
        return new End();
    }
}
