package codetoon.method.setter;

import codetoon.method.voider.Mode;
import codetoon.system.Memory;
import codetoon.system.Player;

public class SetNormalMode extends Mode
{
    @Override
    protected void change(Player host, Memory memory) {
        changeMemory(memory, new Memory(memory.getInfo()), true);
    }

    @Override
    public Object newInstance() {
        return new SetNormalMode();
    }
}
