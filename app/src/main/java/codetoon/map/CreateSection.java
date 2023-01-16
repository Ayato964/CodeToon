package codetoon.map;

import codetoon.main.Main;
import codetoon.server.Server;
import codetoon.util.ContainerData;
import codetoon.util.animation.Animation;
import codetoon.util.animation.AnimationsPack;
import codetoon.util.box.Box;
import codetoon.util.box.ContainerBox;
import codetoon.util.box.DrawingTextBox;

import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.awt.Color.RED;

public class CreateSection extends Map{
    InetAddress address;
    ContainerBox backTitle;
    boolean isHost;
    private String ipAdress;
    public CreateSection(){
        isHost = false;
        try {
            address = InetAddress.getLocalHost();
        }catch (UnknownHostException e) {
        }
    }
    @Override
    public void setup(Graphics g) {
        backTitle = new ContainerBox(10, 100, 50, 10, new ContainerData<Box, Integer>() {
            @Override
            public void action(int i) {
              //  Server.server.stopConnection();
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
        matching(g, 10, 20, 120, 40);
        difficulty(g, 10, 61, 120, 40);
        descRule(g, 140, 10, 60, 100);


    }private void difficulty(Graphics g, int x, int y, int w, int h){
        Animation.create(g).draw("session.difficulty.title", x, y, new Animation.Properties()
                .font("", Font.BOLD, 32)
                .frame(Color.WHITE,x, y + h - 3,  w, h, ()->true)
        );
        Animation.create(g).draw("session.difficulty.easy", x + 33, y + 5, new Animation.Properties()
                .font("", Font.BOLD, 60)
        );
        Animation.create(g).draw("session.difficulty.normal", x + 60, y + 5, new Animation.Properties()
                .font("", Font.BOLD, 60)
        );
        Animation.create(g).draw("session.difficulty.high", x + 33, y + 20, new Animation.Properties()
                .font("", Font.BOLD, 60)
        );
        Animation.create(g).draw("session.difficulty.very.high", x + 60, y + 20, new Animation.Properties()
                .font("", Font.BOLD, 60)
        );

    }
    private void descRule(Graphics g, int x, int y, int w, int h){
        Animation.create(g).draw("session.desc.title", x, y, new Animation.Properties()
                .font("", Font.BOLD, 32)
                .frame(Color.WHITE,x, y + h - 3,  w, h, ()->true)
        );


    }
    private void matching(Graphics g, int x, int y, int w, int h){
        Animation.create(g).draw("session.matching.title", x, y, new Animation.Properties()
                .font("", Font.BOLD, 32)
                .frame(Color.WHITE,x, y + h - 3,  w, h, ()->true)
        );
        AnimationsPack pack = AnimationsPack.create();
        pack.add(Animation.create(g).draw("session.matching.client.mes1", x + 3, y + 15, new Animation.Properties(false)
                .size(40)
                .textArea(90, 10, Color.WHITE, mes -> {ipAdress = mes;})
        ));
        pack.add(Animation.create(g).draw("session.matching.client.mes2", x + 3, y + 25, new Animation.Properties(false)
                .font("", Font.BOLD, 28)
                .color(RED)
        ));
        pack.next();
        pack.add(Animation.create(g).draw("session.matching.host.mes1", x + 3, y + 15, new Animation.Properties(false)
                .font("", Font.BOLD, 60)
                .color(Color.WHITE)
        ));
        pack.add(Animation.create(g).draw(address.getHostAddress() +"", x + 45, y + 15, new Animation.Properties(false)
                .font("", Font.BOLD, 60)
                .color(RED)
        ));
        pack.add(Animation.create(g).draw("session.matching.host.mes2", x + 3, y + 25, new Animation.Properties(false)
                .font("", Font.BOLD, 28)
                .color(RED)
        ));
        Animation.create(g).draw("session.matching.host", x + 10, y + 8, new Animation.Properties()
                .size(30)
                .checkBox(i -> { pack.set(1);}, i ->{pack.set(0);})
        );

    }
    @Override
    public void display(Graphics g) {
        backTitle.draw();

    }
}
