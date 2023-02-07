package codetoon.variable;

import java.util.HashMap;

import codetoon.system.Player;
import codetoon.util.*;

public abstract class Variable<T> implements Actions<Player, T>, Setter {
    public boolean isArray = setIsArray();


    public abstract boolean setIsArray();    
    @Override
    public void action(Player i) {
       returnAction(null);
    }
    public abstract T returnAction(Player p);
}
