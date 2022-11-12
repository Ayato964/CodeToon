package codetoon.util.box;

import codetoon.util.ContainerData;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public abstract class InputTextHelper implements ContainerData<Box, Integer> {

    private InputTextBox box;
    public InputTextHelper(){}
    @Override
    public void action(int i) {
        pressedEntered(box);
    }

    @Override
    public int getCount(){
        return 1;
    }
    @Override
    public Box set(Integer integer){
        box = (InputTextBox) set();
        return box;
    }

    public abstract Box set();

    public abstract void pressedEntered(InputTextBox box);
}
