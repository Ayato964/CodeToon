package codetoon.method.setter;

import codetoon.method.voider.Mode;
import codetoon.system.Memory;
import codetoon.system.Player;
import codetoon.system.SaveMemory;

public class SetSaveMode extends Mode {
    @Override
    protected void change(Player host, Memory memory) {
        changeMemory(memory, new SaveMemory(memory.getInfo()), false);
    }

    @Override
    public Object newInstance() {
        return new SetSaveMode();
    }
}
