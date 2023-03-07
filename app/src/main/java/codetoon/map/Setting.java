package codetoon.map;

import codetoon.main.Main;
import codetoon.system.Background;
import codetoon.system.CodeToon;
import codetoon.util.Action;
import codetoon.util.animation.Animation;
import codetoon.util.animation.AnimationSuggest;
import codetoon.util.animation.AnimationText;
import codetoon.util.animation.AnimationsPack;

import java.awt.*;
import java.net.UnknownHostException;

public class Setting extends Map{
    private Category category = Category.VIDEO;

    private AnimationsPack pack;

    private Action action = i ->{
        pack.set(category.get());
    };
    @Override
    public void setup(Graphics g){

        Animation.create(g).draw("setting.exit", 10, 115, new Animation.Properties().size(40).background(CodeToon.categoryBg).button(i -> Main.getInstance().run(new Title())).frame(CodeToon.textColor));

        setting(g, 80, 20, 120, 90);
        summary(g, 5, 20, 60, 90);
    }

    private void setting(Graphics g, int x, int y, int w, int h) {
        pack = AnimationsPack.create();
        Animation.create(g).draw("detail.title", x, y,
                new Animation.Properties().font("", Font.PLAIN, 32)
                        .color(CodeToon.textColor)
                        .background(CodeToon.categoryBg, x, y, w, h)
                        .frame(CodeToon.frameColor, x, y, w, h, ()->true)
        );
        pack.add(
                Animation.create(g).draw("detail.video.bg.mes", x, y + 10,
                        new Animation.Properties()
                                .font("", Font.PLAIN, 32)
                                .color(CodeToon.textColor)
                )
        );
        pack.add(
                Animation.create(g).draw(Background.getInstance().mode.get(), x + 35, y + 10,
                        new Animation.Properties()
                                .color(CodeToon.textColor).font("", 0, 25)
                                .setChangeText(() ->Background.getInstance().mode.get(), ()->true)
                                .button(i ->{
                                    AnimationSuggest as = AnimationSuggest.create(g, i.getX() / Main.DW, i.getY() / Main.DH + 10, 85, 3, 32)
                                            .add(Background.BackgroundMode.COLOR_ANIMATION.get(), new Animation.Properties().color(CodeToon.textColor), e->Background.getInstance().mode = Background.BackgroundMode.COLOR_ANIMATION)
                                            .add(Background.BackgroundMode.DARK.get(), new Animation.Properties().color(CodeToon.textColor), e->Background.getInstance().mode = Background.BackgroundMode.DARK)
                                            .add(Background.BackgroundMode.GRAY.get(), new Animation.Properties().color(CodeToon.textColor), e->Background.getInstance().mode = Background.BackgroundMode.GRAY);
                                    as.show();
                                })
                                .frame(CodeToon.frameColor)
                )
        );
        pack.next();

        pack.add(Animation.create(g).draw("summary.music", x, y + 10,
                new Animation.Properties(false).font("", Font.PLAIN, 32)
                        .color(CodeToon.textColor)
        ));

        pack.next();



        pack.add(Animation.create(g).draw("summary.language", x, y + 10,
                new Animation.Properties(false).font("", Font.PLAIN, 32)
                        .color(CodeToon.textColor)
        ));

    }

    private void summary(Graphics g,int x, int y, int w, int h){
        Animation.create(g).draw("summary.title", x, y,
                new Animation.Properties().font("", Font.PLAIN, 32)
                        .color(CodeToon.textColor)
                        .background(CodeToon.categoryBg, x, y, w, h)
                        .frame(CodeToon.frameColor, x, y, w, h, ()->true)
        );
        Animation.create(g).draw("summary.video", x, y + 15,
                new Animation.Properties().font("", Font.PLAIN, 50)
                        .color(CodeToon.textColor)
                        .button(i-> {
                            category = Category.VIDEO;
                            action.action(0);
                        })
                        .frame(CodeToon.frameColor,x, y + 15, w, 15, ()-> category == Category.VIDEO)
        );
        /*
        Animation.create(g).draw("summary.music", x, y + 30,
                new Animation.Properties().font("", Font.PLAIN, 50)
                        .color(CodeToon.textColor)
                        .button(i->{category = Category.MUSIC;
                            action.action(i);})
                        .frame(CodeToon.frameColor,x, y + 30, w, 15, ()-> category == Category.MUSIC)
        );

         */
        Animation.create(g).draw("summary.language", x, y + 45,
                new Animation.Properties().font("", Font.PLAIN, 50)
                        .color(CodeToon.textColor)
                        .button(i->{category = Category.LANGUAGE;action.action(0);})
                        .frame(CodeToon.frameColor,x, y + 45, w, 15, ()-> category == Category.LANGUAGE)
        );
    }

    @Override
    public void display(Graphics g) {

    }
    enum Category{
        VIDEO(0),
        MUSIC(1),
        LANGUAGE(2);

        int i;
        Category(int i) {
            this.i = i;
        }
        public int get(){
            return i;
        }
    }

}
