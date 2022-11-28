package codetoon.map;

import codetoon.main.Main;
import codetoon.server.Server;
import codetoon.system.CodeToon;
import codetoon.util.ContainerData;
import codetoon.util.box.*;
import codetoon.util.animation.Animation;

import java.awt.*;

public class JoinServer extends Map{
    Graphics g;
    ContainerBox box;
    ContainerBox exit;
    public JoinServer(){



    }
    @Override
    public void setup(Graphics g){
        this.g = g;
        box = new ContainerBox(40, 40, 60, 10, new InputTextHelper() {
            @Override
            public Box set() {
                return new InputTextBox(g, new Animation.Properties().size(40));
            }
            @Override
            public void pressedEntered(InputTextBox box) {
                System.out.println(box.getString());
                Server.server.startServer(box.getString());
                Main.getInstance().run(new PazzleStage(5));

            }
        });
        exit = new ContainerBox(10, 100, 60, 10, new ContainerData<Box, Integer>() {
            @Override
            public void action(int i) {
                Main.getInstance().run(new Title());
            }

            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public Box set(Integer integer) {
                return new DrawingTextBox("タイトルに戻る");
            }
        });
        box.draw();
        exit.draw();
        Animation.create(g).draw("セッションID（相手のIPアドレス）を入力してください。", 0, 20,
                new Animation.Properties()
                        .size(40)
                        .center()
        );

    }

    @Override
    public void display(Graphics g) {
        box.draw();exit.draw();
    }
}
