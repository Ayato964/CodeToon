package codetoon.map;

import codetoon.main.Main;
import codetoon.server.Server;
import codetoon.system.Difficulty;
import codetoon.system.Rule;
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
import static java.awt.Color.WHITE;

public class CreateSection extends Map{
    Rule rule;
    InetAddress address;
    ContainerBox backTitle;
    boolean isHost;
    private String ipAdress;
    public CreateSection(){
        rule = Rule.create();
        rule.dif = Difficulty.EASY;
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
                if(Server.server.startServer)
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

        Animation.create(g).draw("session.create", 10, 110, new Animation.Properties()
                .font("", Font.BOLD, 32)
                .center()
                .frame(WHITE)
                .button(i -> {
                    System.out.println("ServerStarted");
                    Server.server.startServer(Server.isHost == true ? null : ipAdress, rule);
                }).setChangeText("session.loading", () -> Server.server.startServer)
                .color(WHITE)
        );


    }private void difficulty(Graphics g, int x, int y, int w, int h){
        Animation.create(g).draw("session.difficulty.title", x, y, new Animation.Properties()
                .font("", Font.BOLD, 32)
                .frame(Color.WHITE,x, y + h - 3,  w, h, ()->true)
        );
        Animation.create(g).draw("session.difficulty.easy", x + 33, y + 7, new Animation.Properties()
                .font("", Font.BOLD, 60)
                .button(i->{rule.dif = Difficulty.EASY;
                            rule.memory_w = 3;
                            rule.memory_h = 3;
                            rule.isShowingOpponentMemory = true;
                })
                .frame(WHITE, x + 33, y + 9, 30, 10, ()->rule.dif == Difficulty.EASY)
        );
        Animation.create(g).draw("session.difficulty.normal", x + 80, y + 7, new Animation.Properties()
                .font("", Font.BOLD, 60)
                .button(i->{rule.dif = Difficulty.NORMAL;
                    rule.memory_w = 5;
                    rule.memory_h = 5;
                })
                .frame(WHITE, x + 80, y + 9, 30, 10, ()->rule.dif == Difficulty.NORMAL)

        );
        Animation.create(g).draw("session.difficulty.high", x + 33, y + 22, new Animation.Properties()
                .font("", Font.BOLD, 60)
                .button(i-> {rule.dif = Difficulty.HIGH;

                    rule.memory_w = 5;
                    rule.memory_h = 5;
                    rule.isSettingMemoryPassword = true;
                })
                .frame(WHITE, x + 33, y + 24, 30, 10, ()->rule.dif == Difficulty.HIGH)
        );
        Animation.create(g).draw("session.difficulty.very.high", x + 80, y + 22, new Animation.Properties()
                .font("", Font.BOLD, 60)
                .button(i->{
                    rule.dif = Difficulty.VERY_HIGH;
                    rule.memory_w = 7;
                    rule.memory_h = 7;
                    rule.isSettingMemoryPassword = true;
                })
                .frame(WHITE, x + 80, y + 24, 30, 10, ()->rule.dif == Difficulty.VERY_HIGH)
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
        pack.add(Animation.create(g).draw("session.matching.client.mes1", x + 3, y + 17, new Animation.Properties(false)
                .size(40)
                .textArea(90, 10, Color.WHITE, mes -> {ipAdress = mes;})
        ));
        pack.add(Animation.create(g).draw("session.matching.client.mes2", x + 3, y + 27, new Animation.Properties(false)
                .font("", Font.BOLD, 28)
                .color(RED)
        ));
        pack.next();
        pack.add(Animation.create(g).draw("session.matching.host.mes1", x + 3, y + 17, new Animation.Properties(false)
                .font("", Font.BOLD, 40)
                .color(Color.WHITE)
        ));
        pack.add(Animation.create(g).draw(address.getHostAddress() +"", x + 65, y + 17, new Animation.Properties(false)
                .font("", Font.BOLD, 40)
                .color(RED)
        ));
        pack.add(Animation.create(g).draw("session.matching.host.mes2", x + 3, y + 27, new Animation.Properties(false)
                .font("", Font.BOLD, 28)
                .color(RED)
        ));
        Animation.create(g).draw("session.matching.host", x + 10, y + 10, new Animation.Properties()
                .size(30)
                .checkBox(i -> {
                    Server.isHost = true;
                    pack.set(1);},
                        i ->{
                    Server.isHost = false;
                    pack.set(0);})
        );


    }
    @Override
    public void display(Graphics g) {
        backTitle.draw();

    }
}
