package codetoon.map;

import codetoon.server.Server;
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


    }
}
