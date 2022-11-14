package codetoon.map;

import codetoon.main.Main;
import codetoon.server.Server;
import codetoon.system.CodeToon;
import codetoon.util.ContainerData;
import codetoon.util.box.Box;
import codetoon.util.box.ContainerBox;
import codetoon.util.box.InputTextBox;
import codetoon.util.animation.Animation;
import codetoon.util.box.InputTextHelper;

import java.awt.*;

public class JoinServer extends Map{
    Graphics g;
    ContainerBox box;
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
                Main.getInstance().run(new PazzleStage(CodeToon.MEMORY_SIZE));

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
