package codetoon.system;

import codetoon.main.Main;
import codetoon.map.Loser;
import codetoon.map.Winner;
import codetoon.server.Server;

import java.awt.*;
import java.util.ArrayList;

public class CodeToon{
    public static final String GAME_VERSION = "1.0.10";
    public static boolean isGameStart = false;
    public static boolean DEBUG = false;
    public static int MEMORY_SIZE = 5;
    public static final int INSIDE_METHODS = 192010;

    public static final int HOST_MAP = 1101;

    public static final Graphics GRAPHICS = Main.getMainGraphics();
    public static final int PARCENT_ARGUMENT = 1098;
    public static final int INFINITY = -100000;
    private CodeToon(ArrayList<Memory> m){
        boolean b = m == null;
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
                    isGameStart = false;
                    Memories.stopAll();
                    Console.getInstance().setVisible(false);
                    Server.server.end();
                    Main.getInstance().run(new Winner());

                    Server.server.sendOpponentCopy();
                    Server.server.sendMyCopy();
                }
                if (isAllHacked(o)) {
                    isGameStart = false;
                    Memories.stopAll();
                    Console.getInstance().setVisible(false);
                    Main.getInstance().run(new Loser());

                    Server.server.sendOpponentCopy();
                    Server.server.sendMyCopy();
                }
            }
        }
    }
    private static boolean isAllHacked(ArrayList<Memory> m){
        for(int i = 0; i < m.size(); i ++){
            if(m.get(i).getStates() != EnumMemoryStates.HACKED){
                return false;
            }
        }
        return true;
    }
}
