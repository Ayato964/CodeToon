package codetoon.method;

import codetoon.system.Player;

import java.util.HashMap;

public class End extends MyMethod {

    public End() {
    }
    @Override
    public void action(Player host) {
        System.out.println("プログラムを実行しました");
    }
    @Override
    public String set(HashMap<Integer, String> map) {
        return null;
    }
    @Override
    public Object newInstance() {
        return new End();
    }
}
