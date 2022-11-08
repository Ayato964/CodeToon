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
        Animation.create(g).draw("�V�K�Z�b�V�������J�n����E�E�E", 198 / 2 , 108 / 2 - 10);
        Animation.create(g).draw("���Ȃ���IP�A�h���X�͈ȉ��̂Ƃ���ł��B", 198 / 2, 108 / 2 - 5);
        Animation.create(g).draw(address.getHostAddress(), 198 / 2, 108 / 2);
    }
}
