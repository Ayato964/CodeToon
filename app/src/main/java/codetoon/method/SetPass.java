package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.system.AbstractLockerPlayer;
import codetoon.system.CodeToon;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class SetPass extends MyMethod{
    private AbstractLockerPlayer parcent = null;
    private String percentString;
    private String new_pass = "0";
    private String old_pass = "0";
    @Override
    public Object newInstance() {
        return new SetPass();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        percentString = map.get(CodeToon.PARCENT_ARGUMENT);
        if(map.get(1) != null){
            old_pass = map.get(0);
            new_pass = map.get(1);
            System.out.println("old:" + old_pass + "   new:" + new_pass);
        }else{
            new_pass = map.get(0);
        }
        return null;
    }
    @Override
    public void action(Player host) {
        System.out.println(percentString + "<----");
        parcent = (AbstractLockerPlayer) ObjectArgument.getInstance().indentification(percentString, host);
        if(parcent != null){
            parcent.setPassWord(IntegerArgument.getInstance().indentification(old_pass), IntegerArgument.getInstance().indentification(new_pass));

        }
    }
}
