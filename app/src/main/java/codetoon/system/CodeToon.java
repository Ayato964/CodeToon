package codetoon.system;

import codetoon.main.Main;
import codetoon.map.Loser;
import codetoon.map.Winner;
import codetoon.server.Server;

import java.awt.*;
import java.util.ArrayList;

public class CodeToon{
    public static final String GAME_VERSION = "1.4.8";
    public static Rule RULE;
    public static boolean isGameStart = false;
    public static boolean DEBUG = false;
    public static final int INSIDE_METHODS = 192010;
    public static final  int METHOD_MAX_COUNT = 4;

    public static final int HOST_MAP = 1101;

    public static final Graphics GRAPHICS = Main.getMainGraphics();
    public static final int PARCENT_ARGUMENT = 1098;
    public static final int INFINITY = -100000;
    private CodeToon(ArrayList<Memory> m){
    }

    public static void gameStart(){
        isGameStart = true;
        Thread t = new Thread(CodeToon::gameObserver);
        t.start();
    }
    public static void gameObserver(){
        new CodeToon(Memories.opponentMemory);
        while (isGameStart && !DEBUG){
            System.out.print(Memories.opponentMemory == null ? "" : "");
            if(Memories.opponentMemory != null) {
                ArrayList<Memory> om = Memories.opponentMemory;
                ArrayList<Memory> o = Memories.memory;
                //System.out.println("HEHEHE");
                if (isAllHacked(om)) {
                    Server.server.sendOpponentCopy();
                    Server.server.sendMyCopy();
                    isGameStart = false;
                    Memories.stopAll();
                    Console.getInstance().setVisible(false);
                    Server.server.end();
                    Main.getInstance().run(new Winner());
                }
                if (isAllHacked(o)) {
                    Server.server.sendOpponentCopy();
                    Server.server.sendMyCopy();
                    isGameStart = false;
                    Memories.stopAll();
                    Console.getInstance().setVisible(false);
                    Main.getInstance().run(new Loser());

                }
            }
        }
    }
    private static boolean isAllHacked(ArrayList<Memory> m){
        if(m.isEmpty() || m == null)
            return false;
        for (Memory memory : m) {
            if (memory.getStates() != EnumMemoryStates.HACKED) {
                return false;
            }
        }
        return true;
    }
}
