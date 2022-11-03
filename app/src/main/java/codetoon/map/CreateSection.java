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
    public void setup(Graphics h){

           }

    @Override
    public void display(Graphics g) {
        Animation.create(g).draw("新規セッションを開始しています.....", 198 / 2 , 108 / 2 - 10);
        Animation.create(g).draw("あなたのIPアドレスは以下のとおりです。", 198 / 2, 108 / 2 - 5);
        Animation.create(g).draw(address.getHostAddress(), 198 / 2, 108 / 2);
    }
}
