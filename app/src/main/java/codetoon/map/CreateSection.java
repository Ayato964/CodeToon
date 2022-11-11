package codetoon.map;

import codetoon.util.animation.Animation;

import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CreateSection extends Map{
    InetAddress address;
    public CreateSection(){
        try {
            address = InetAddress.getLocalHost();
        }catch (UnknownHostException e) {
        }
    }
    @Override
    public void setup(Graphics g){
        Animation.create(g).draw("新規セッションを開始しています・・・", 198 / 2 , 20,
                new Animation.Properties()
                        .size(40)
                        .center()
        );

        Animation.create(g).draw("あなたのIPアドレスは以下のとおりです。", 198 / 2, 40,
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
        Animation.create(g).draw("相手にIPアドレスを入力してもらってください。", 0, 80,
                new Animation.Properties()
                        .size(40)
                        .center()
                );
    }

    @Override
    public void display(Graphics g) {


    }
}
