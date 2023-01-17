package codetoon.system;

import codetoon.util.lang.LangLoader;

public enum Difficulty {
    EASY("session.difficulty.easy"),
    NORMAL("session.difficulty.normal"),
    HIGH("session.difficulty.high"),
    VERY_HIGH("session.difficulty.very.high");
    String str;
    Difficulty(String s) {
        str = LangLoader.getInstance().get(null, s);
    }
    public String toString(){
        return str;
    }
}
