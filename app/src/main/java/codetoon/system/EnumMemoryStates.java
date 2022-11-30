package codetoon.system;

import java.awt.*;

public enum EnumMemoryStates {
    NONE(Color.WHITE),
    HACKED(Color.RED),
    USED(Color.GREEN);
    private Color c;

    private EnumMemoryStates(Color col) {
        c = col;
    }

    public Color getColor() {
        return c;
    }
}
