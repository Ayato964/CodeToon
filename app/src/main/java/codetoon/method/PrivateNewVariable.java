package codetoon.method;

import codetoon.argument.BooleanArgumet;
import codetoon.argument.IntegerArgument;
import codetoon.argument.ObjectArgument;
import codetoon.argument.StringArgument;
import codetoon.system.Memory;
import codetoon.system.Player;
import codetoon.util.converter.ConvertVariable;
import codetoon.variable.CustomVariable;
import codetoon.variable.Variables;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class PrivateNewVariable extends PrivateVariable{
    private int type;
    @Override
    public Object newInstance() {
        return new PrivateNewVariable();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        super.set(map);
        type = IntegerArgument.getInstance().indentification(map.get(10));
        return null;
    }

    @Override
    public void action(Player host) {
        System.out.println(variable);
        switch (type){
            case ConvertVariable.STRING :
                Variables.VARIABLE.createRegistory(id, () ->
                        new CustomVariable<String>(StringArgument.getInstance().indentification(variable, host)));break;
            case ConvertVariable.INTEGER:
               // System.out.println("newInstance:" + variable);
                Variables.VARIABLE.createRegistory(id, () ->
                        new CustomVariable<Integer>(IntegerArgument.getInstance().indentification(variable, host)));break;
            case ConvertVariable.BOOLEAN:
                Variables.VARIABLE.createRegistory(id, () ->
                        new CustomVariable<Boolean>(BooleanArgumet.getInstance().indentification(variable, host)));break;
            case ConvertVariable.MEMORY:System.out.println(id);
                Variables.VARIABLE.createRegistory(id, ()->
                        new CustomVariable<Memory>(((Memory)ObjectArgument.getInstance().indentification(variable, host)))
                        );break;

           }
    }

}
