package codetoon.map;

import codetoon.util.Display;
import codetoon.util.Setup;

import java.awt.*;
import java.net.UnknownHostException;

/**
 * <p>This class sets the graphics for the map.</p>
 * <p>When adding a map, be sure to inherit from this class.</p>
 * <p>When the {@link  codetoon.main.Main#run(Map)} of the {@link codetoon.main.Main} class is executed, <br>
 * the {@link #setup(Graphics)} function is executed only once<br>
 * and the {@link #display(Graphics)} function is executed repeatedly.</p>
 * <p>The <code>display</code> function is managed by the {@link codetoon.util.Tick} class.</p>
 *
 * <p>If you are drawing an animation using the {@link codetoon.util.animation.Animation} class, be sure to write the program in the <code>setup</code> function.</p>
 *
 * @see codetoon.util.Tick
 * @see codetoon.main.Main
 * @see codetoon.util.animation.Animation
 * @see #setup(Graphics)
 * @see #display(Graphics)
 * @author Ayato
 */

public abstract class Map implements Display, Setup{
    public Map(){
    }
    public abstract void setup(Graphics g);
    public abstract void display(Graphics g);
  }