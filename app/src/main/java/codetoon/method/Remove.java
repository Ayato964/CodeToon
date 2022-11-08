package codetoon.method;

import codetoon.main.Main;
import codetoon.map.PazzleStage;
import codetoon.system.Console;

import java.util.HashMap;

public class Remove extends MyMethod{
    @Override
    public Object newInstance() {
        return new Remove();
    }

    @Override
    public String set(HashMap<Integer, String> map) {
        return null;
    }

    @Override
    public void action(int i) {
        System.out.println("ソースを全てリセットしました。");
        Console console =((PazzleStage) Main.getInstance().getMap()).getConsole();
        console.panel.setProgram(new StringBuilder());
        console.panel.resetAll();
    }
}
