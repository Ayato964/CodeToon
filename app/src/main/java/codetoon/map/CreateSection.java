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
    boolean isHost;
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
        matching(g, 10, 20, 120, 40);
        difficulty(g, 10, 61, 120, 40);
        descRule(g, 140, 10, 60, 100);


    }private void difficulty(Graphics g, int x, int y, int w, int h){
        Animation.create(g).draw("session.difficulty.title", x, y, new Animation.Properties()
                .font("", Font.BOLD, 32)
                .frame(Color.WHITE,x, y + h - 3,  w, h, ()->true)
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
        Animation.create(g).draw("session.matching.host", x + 10, y + 10, new Animation.Properties()
                .size(30));
    }
    @Override
    public void display(Graphics g) {
        backTitle.draw();

    }
}
