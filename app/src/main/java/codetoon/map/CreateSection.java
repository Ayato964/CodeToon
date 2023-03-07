package codetoon.map;

import codetoon.main.Main;
import codetoon.server.Server;
import codetoon.system.CodeToon;
import codetoon.system.Difficulty;
import codetoon.system.Rule;
import codetoon.util.ContainerData;
import codetoon.util.animation.Animation;
import codetoon.util.animation.AnimationsPack;
import codetoon.util.animation.Width;
import codetoon.util.box.Box;
import codetoon.util.box.ContainerBox;
import codetoon.util.box.DrawingTextBox;
import codetoon.util.lang.LangLoader;

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
        rule.memory_w = 3;
        rule.memory_h = 3;
        rule.isShowingOpponentMemory = true;
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
                .background(CodeToon.categoryBg)
                .frame(WHITE)
                .button(i -> {
                    System.out.println("ServerStarted");
                    Server.server.startServer(Server.isHost == true ? null : ipAdress, rule);
                }).setChangeText(() ->"session.loading", () -> Server.server.startServer)
                .color(WHITE)
        );


    }private void difficulty(Graphics g, int x, int y, int w, int h){
        Animation.create(g).draw("session.difficulty.title", x, y, new Animation.Properties()
                .font("", Font.BOLD, 32)
                .background(CodeToon.categoryBg, x, y, w, h)
                .frame(Color.WHITE,x, y,  w, h, ()->true)
        );
        Animation.create(g).draw("session.difficulty.easy", x + 33, y + 7, new Animation.Properties()
                .font("", Font.BOLD, 60)
                .button(i->{rule.dif = Difficulty.EASY;
                            rule.memory_w = 3;
                            rule.memory_h = 3;
                            rule.isSettingMemoryPassword = false;
                            rule.isShowingOpponentMemory = true;
                })
                .frame(WHITE, x + 33, y + 9, 30, 10, ()->rule.dif == Difficulty.EASY)
        );
        Animation.create(g).draw("session.difficulty.normal", x + 80, y + 7, new Animation.Properties()
                .font("", Font.BOLD, 60)
                .button(i->{rule.dif = Difficulty.NORMAL;
                    rule.memory_w = 5;
                    rule.memory_h = 5;
                    rule.isShowingOpponentMemory = false;
                    rule.isSettingMemoryPassword = false;
                })
                .frame(WHITE, x + 80, y + 9, 30, 10, ()->rule.dif == Difficulty.NORMAL)

        );
        Animation.create(g).draw("session.difficulty.high", x + 33, y + 22, new Animation.Properties()
                .font("", Font.BOLD, 60)
                .button(i-> {rule.dif = Difficulty.HIGH;

                    rule.memory_w = 5;
                    rule.memory_h = 5;
                    rule.isShowingOpponentMemory = false;
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
                    rule.isShowingOpponentMemory = false;
                    rule.isSettingMemoryPassword = true;
                })
                .frame(WHITE, x + 80, y + 24, 30, 10, ()->rule.dif == Difficulty.VERY_HIGH)
        );

    }
    private void descRule(Graphics g, int x, int y, int w, int h){
        Animation.create(g).draw("session.desc.title", x, y, new Animation.Properties()
                .font("", Font.BOLD, 32)
                .background(CodeToon.categoryBg, x, y , w, h)
                .frame(Color.WHITE,x, y,  w, h, ()->true)
        );

        Animation.create(g).draw(new String[]{rule.dif.toString()}, "session.desc.difficulty", x, y + 10, new Animation.Properties()
                        .size(32)
                        .color(WHITE)
                        .changeArgument(()->new String[]{rule.dif.toString()})

                );
        Animation.create(g).draw(new String[]{new StringBuilder().append(rule.memory_w).toString(),
                new StringBuilder().append(rule.memory_h).toString()}, "session.desc.memory.size", x, y + 20, new Animation.Properties()
                .size(32)
                .color(WHITE)
                .changeArgument(()->new String[]{new StringBuilder().append(rule.memory_w).toString(),
                        new StringBuilder().append(rule.memory_h).toString()})

        );
        Animation.create(g).draw(new String[]{"false"}, "session.desc.show.opponent", x, y + 30, new Animation.Properties()
                .size(30)
                .color(WHITE)
             //   .setWidth(w - 2)
                .changeArgument(()-> new String[]{new StringBuilder().append(rule.isShowingOpponentMemory).toString()})
        );
        Animation.create(g).draw(new String[]{"false"}, "session.desc.give.firstpass", x, y + 40, new Animation.Properties()
                .size(30)
                .color(WHITE)
               // .setWidth(w - 2)
                .changeArgument(()-> new String[]{new StringBuilder().append(rule.isSettingMemoryPassword).toString()})
        );
        /*
        Animation.create(g).draw(new String[]{""}, "session.desc.map", x , y + 50, new Animation.Properties()
                        .size(22)
                        .color(WHITE)
                        .setWidth(w - 2)
                .changeArgument(()->new String[]{LangLoader.getInstance().get(null, "session.map." + getDif())})
        );

         */
    }
    private String getDif(){
        return switch (rule.dif){

            case EASY -> "easy";
            case NORMAL -> "normal";
            case HIGH -> "high";
            case VERY_HIGH -> "very.high";
        };
    }
    private void matching(Graphics g, int x, int y, int w, int h){
        Animation.create(g).draw("session.matching.title", x, y, new Animation.Properties()
                .font("", Font.BOLD, 32)
                .background(CodeToon.categoryBg, x, y, w, h)
                .frame(Color.WHITE,x, y,  w, h, ()->true)
        );
        AnimationsPack pack = AnimationsPack.create();
        pack.add(Animation.create(g).draw("session.matching.client.mes1", x + 3, y + 17, new Animation.Properties(true)
                .size(40)
                .textArea(90, 10, Color.WHITE, mes -> {ipAdress = mes;})
        ));
        pack.add(Animation.create(g).draw("session.matching.client.mes2", x + 3, y + 27, new Animation.Properties(true)
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
