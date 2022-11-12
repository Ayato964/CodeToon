package codetoon.map;

import codetoon.main.Main;
import codetoon.util.ContainerData;
import codetoon.util.box.Box;
import codetoon.util.box.ContainerBox;
import codetoon.util.box.InputTextBox;
import codetoon.util.animation.Animation;

import java.awt.*;

public class JoinServer extends Map{
    Graphics g;
    ContainerBox box;
    public JoinServer(){



    }
    @Override
    public void setup(Graphics g){
        this.g = g;
        box = new ContainerBox(40, 40, 60, 10, new ContainerData<Box, Integer>() {
            @Override
            public void action(int i) {
                switch (i){
                    case 0: System.out.println("Entered Box");
                }
            }

            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public Box set(Integer integer) {
                return switch(integer.intValue()){
                    case 0 -> new InputTextBox(g, new Animation.Properties().size(40).center());
                    default -> null;
                };
            }
        });

        box.draw();
        Animation.create(g).draw("セッションID（相手のIPアドレス）を入力してください。", 0, 20,
                new Animation.Properties()
                        .center()
                        .size(40));

    }

    @Override
    public void display(Graphics g) {
        box.draw();
    }
}
