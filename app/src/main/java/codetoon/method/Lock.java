package codetoon.method;

import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.system.CodeToon;
import codetoon.system.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Lock extends MyMethod{
    private Player parcent = null;
    private int pass = 0;
    @Override
    public Object newInstance() {
        return new Lock();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        Object t = ObjectArgument.getInstance().indentification(map.get(CodeToon.PARCENT_ARGUMENT));
        if(t instanceof Player){
            parcent = (Player) t;
            Integer i = IntegerArgument.getInstance().indentification(map.get(0));
            StringBuilder temp = new StringBuilder(i.toString());
            System.out.println(temp.length());
            if(!temp.isEmpty()) {
                if (temp.length() <= 4) {
                    pass = i.intValue();
                }
            }
        }
        return null;
    }

    @Override
    public void action(int i) {
        if(parcent != null){
            parcent.setPassWord(pass);
        }
        System.out.println(parcent.getName() + "に" + pass + "のパスコードを設定しました。");
    }
}
