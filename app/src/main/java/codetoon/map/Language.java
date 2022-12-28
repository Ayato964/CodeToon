package codetoon.map;

import codetoon.main.Main;
import codetoon.util.animation.Animation;
import codetoon.util.lang.LangLoader;

import java.awt.*;
import java.net.UnknownHostException;

public class Language extends Map{

    @Override
    public void setup(Graphics g) throws UnknownHostException {
        Animation.create(g).draw("lang.jp", 0, 20, new Animation.Properties().size(40).center()
                .frame(Color.WHITE, ()->LangLoader.LANGUAGE.equals(LangLoader.JAPANESE))
                .button(i ->this.changeLang(LangLoader.JAPANESE))
        );
        Animation.create(g).draw("lang.us", 0, 25, new Animation.Properties().size(40).center()
                .frame(Color.WHITE, ()->LangLoader.LANGUAGE.equals(LangLoader.ENGLISH))
                .button(i ->this.changeLang(LangLoader.ENGLISH))
        );
        Animation.create(g).draw("lang.cn", 0, 30, new Animation.Properties().size(40).center()
                .frame(Color.WHITE, ()->LangLoader.LANGUAGE.equals(LangLoader.CHINESE))
                .button(i ->this.changeLang(LangLoader.CHINESE)));

        Animation.create(g).draw("lang.exit", 20, 100, new Animation.Properties().size(40).button(i -> Main.getInstance().run(new Setting())).frame(Color.WHITE));
        Animation.create(g).draw("lang.save", 120, 100, new Animation.Properties().size(40).frame(Color.RED));

    }
    public void changeLang(String chang){
        System.out.println(chang);
        LangLoader.create(chang);
        Main.getInstance().run(new Language());
    }

    @Override
    public void display(Graphics g) {

    }
}
