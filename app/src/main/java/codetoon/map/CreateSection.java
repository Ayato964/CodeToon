package codetoon.map;

import codetoon.main.Main;
import codetoon.server.Server;
import codetoon.util.ContainerData;
import codetoon.util.animation.Animation;
import codetoon.util.box.Box;
import codetoon.util.box.ContainerBox;
import codetoon.util.box.DrawingTextBox;

import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CreateSection extends Map{
    InetAddress address;
    ContainerBox backTitle;
    public CreateSection(){
        try {
            address = InetAddress.getLocalHost();
        }catch (UnknownHostException e) {
        }
    }
    @Override
    public void setup(Graphics g){
        backTitle = new ContainerBox(10, 100, 50, 10, new ContainerData<Box, Integer>() {
            @Override
            public void action(int i) {
                Server.server.stopConnection();
                Main.getInstance().run(new Title());
            }

            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public Box set(Integer integer) {
                return new DrawingTextBox("session.back.title");
            }
        });
        Animation.create(g).draw("session.mes1", 198 / 2 , 20,
                new Animation.Properties()
                        .size(40)
                        .center()
        );

        Animation.create(g).draw("session.mes2", 198 / 2, 40,
                new Animation.Properties()
                        .size(40)
                        .center()
        );
        Animation.create(g).draw(address.getHostAddress(), 198 / 2, 60,
            new Animation.Properties()
                    .color(Color.RED)
                    .size(50)
                    .center()
        );
        Animation.create(g).draw("session.mes3", 0, 80,
                new Animation.Properties()
                        .size(40)
                        .center()
                );
    }

    @Override
    public void display(Graphics g) {
        backTitle.draw();

    }
}
