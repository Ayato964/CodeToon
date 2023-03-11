package codetoon.map;

import codetoon.main.Main;
import codetoon.system.Background;
import codetoon.system.CodeToon;
import codetoon.util.Action;
import codetoon.util.InputColorSup;
import codetoon.util.animation.*;
import codetoon.util.lang.LangLoader;
import org.python.compiler.Code;

import java.awt.*;

public class Setting extends Map{
    private Category category = Category.VIDEO;

    private AnimationsPack pack;

    private Action action = i ->{
        pack.set(category.get());
    };
    @Override
    public void setup(Graphics g){

        Animation.create(g).draw("setting.exit", 10, 115, new Animation.Properties().size(40).background(CodeToon.categoryBg).button(i -> Main.getInstance().run(new Title())).frame(CodeToon.frameColor).color(CodeToon.textColor));
        Animation.create(g).draw("setting.changed", 50, 115, new Animation.Properties()
                .center()
                .size(40).background(CodeToon.categoryBg).button(i -> Main.getInstance().run(new Setting())).frame(CodeToon.frameColor).color(CodeToon.textColor));

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
        AnimationColorChooser.create(pack, g, "detail.video.framecolor.mes", x, y + 20, new InputColorSup() {
            @Override
            public Color getColor() {
                return CodeToon.frameColor;
            }

            @Override
            public void setColor(Color c) {
                CodeToon.frameColor = c;
            }
        }).isStart(true).show();

        AnimationColorChooser.create(pack, g, "detail.video.categorybg.mes", x, y + 30, new InputColorSup() {
            @Override
            public Color getColor() {
                return CodeToon.categoryBg;
            }

            @Override
            public void setColor(Color c) {
                int alpha = CodeToon.categoryBg.getAlpha();
                Color n = new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
                CodeToon.categoryBg = n;
            }
        }).isStart(true).show();
        pack.next();

        pack.add(Animation.create(g).draw("summary.music", x, y + 10,
                new Animation.Properties(false).font("", Font.PLAIN, 32)
                        .color(CodeToon.textColor)
        ));

        pack.next();



        pack.add(Animation.create(g).draw("detail.text.lang.mes", x, y + 10,
                new Animation.Properties(false).font("", Font.PLAIN, 32)
                        .color(CodeToon.textColor)
        ));
        pack.add(
                Animation.create(g).draw("detail.text.lang." + LangLoader.LANGUAGE, x + 35, y + 10,
                        new Animation.Properties(false).font("", 0, 32).frame(CodeToon.frameColor).color(CodeToon.textColor)
                                .button(e->{
                                    AnimationSuggest as = AnimationSuggest.create(g, e.getX() / Main.DW, e.getY() / Main.DH + 10, 20, 10, 32);
                                    LangLoader.setLangSuggest(g, as);
                                    as.show();
                                })
                                .setChangeText(()->"detail.text.lang." + LangLoader.LANGUAGE, ()->true)
                )
        );
        AnimationColorChooser.create(pack, g, "detail.text.color.mes", x, y + 20, new InputColorSup() {
            @Override
            public Color getColor() {
                return CodeToon.textColor;
            }

            @Override
            public void setColor(Color c) {
                CodeToon.textColor = c;
            }
        }).isStart(false).show();
        AnimationColorChooser.create(pack, g, "detail.text.important.color.mes", x, y + 30, new InputColorSup() {
            @Override
            public Color getColor() {
                return CodeToon.textIMPORTANTColor;
            }

            @Override
            public void setColor(Color c) {
                CodeToon.textIMPORTANTColor = c;
            }
        }).isStart(false).show();

/*
        pack.add(
                Animation.create(g).draw("detail.text.color.mes", x, y + 20, new Animation.Properties(false).font("", 0, 32).color(CodeToon.textColor))
        );
        pack.add(
                Animation.create(g).draw("detail.text.color.r", x + 35, y + 20, new Animation.Properties(false).font("", 0, 32).color(CodeToon.textColor))
        );
        pack.add(
                Animation.create(g).draw("" + CodeToon.textColor.getRed(), x + 40, y + 20,
                        new Animation.Properties(false).font("", 0, 32)
                                .textArea(10, 6, CodeToon.frameColor, mes -> {
                                    Color c = new Color(Integer.parseInt(mes), CodeToon.textColor.getGreen(), CodeToon.textColor.getBlue());
                                    CodeToon.textColor = c;
                                })
                        )
        ); pack.add(
                Animation.create(g).draw("detail.text.color.g", x + 50, y + 20, new Animation.Properties(false).font("", 0, 32).color(CodeToon.textColor))
        );
        pack.add(
                Animation.create(g).draw("" + CodeToon.textColor.getGreen(), x + 55, y + 20,
                        new Animation.Properties(false).font("", 0, 32)
                                .textArea(10, 6, CodeToon.frameColor, mes -> {
                                    Color c = new Color(CodeToon.textColor.getRed(), Integer.parseInt(mes), CodeToon.textColor.getBlue());
                                    CodeToon.textColor = c;
                                })
                )
        ); pack.add(
                Animation.create(g).draw("detail.text.color.b", x + 65, y + 20, new Animation.Properties(false).font("", 0, 32).color(CodeToon.textColor))
        );
        pack.add(
                Animation.create(g).draw("" + CodeToon.textColor.getBlue(), x + 70, y + 20,
                        new Animation.Properties(false).font("", 0, 32)
                                .textArea(10, 6, CodeToon.frameColor, mes -> {
                                    Color c = new Color(CodeToon.textColor.getRed(), CodeToon.textColor.getGreen(), Integer.parseInt(mes));
                                    CodeToon.textColor = c;
                                })
                )
        );

 */
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
                        .button(i-> {
                            category = Category.VIDEO;
                            action.action(0);
                        })
                        .frame(CodeToon.frameColor,x, y + 15, w, 15, ()-> category == Category.VIDEO)
                        .color(CodeToon.textColor)
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

        Animation.create(g).draw("summary.text", x, y + 45,
                new Animation.Properties().font("", Font.PLAIN, 50)
                        .button(i->{category = Category.LANGUAGE;action.action(0);})
                        .frame(CodeToon.frameColor,x, y + 45, w, 15, ()-> category == Category.LANGUAGE)
                        .color(CodeToon.textColor)

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
