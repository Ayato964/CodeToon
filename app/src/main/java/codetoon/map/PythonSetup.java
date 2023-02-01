package codetoon.map;

import codetoon.system.Memories;
import codetoon.system.Memory;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.*;

public class PythonSetup extends PazzleStage implements Runnable{
    public String aiOutput;
    public PythonSetup(int memorySize) {
        super(memorySize);

        setEnemyMemory(MEMORY_W, MEMORY_H, 0, 0, 0, 0, 0);
        Thread t1 = new Thread(this::run);
        t1.start();
    }
    private void setEnemyMemory(int mw, int mh, int x, int y, int w, int h, int pass){
        ArrayList<Memory> memory = new ArrayList<>();
        for(int i = 0; i < mh; i ++){
            for(int c = 0; c < mw; c ++){
                Memory t = new Memory(x + i * (w / mw), y + c * (h / mh), w / mw, h / mh, i, c);
                t.pass = pass;
                memory.add(t);
            }
        }
        Memories.opponentMemory = memory;
    }

    @Override
    public void run() {
        String python =  getClass().getClassLoader().getResource("python/main.py").toString();
        ProcessBuilder pb = new ProcessBuilder("python ", python);
        Process p;

        try {
            p = pb.start();
            p.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OutputStream o = p.getOutputStream();
        OutputStreamWriter o2 = new OutputStreamWriter(o);
        try {
            o2.write("attack(enemy.memory[1][2]);");
            o2.flush();
            o2.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (p.isAlive()) {
            InputStream input = p.getInputStream();
            InputStreamReader ireader = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(ireader);
            try {
                while ((aiOutput = reader.readLine()) != null){
                    System.out.println(aiOutput);

                }
            }catch (IOException e){

            }
        }

    }
    private class Ai implements Callable<String>{

        @Override
        public String call() throws Exception {
            String str = getClass().getClassLoader().getResource("python/main.py").toString();
            //Process Instance.
            ProcessBuilder processBuilder = new ProcessBuilder("python", str);
            System.out.println(str);
            //Process Begin
            Process process = processBuilder.start();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

            writer.write("attack(memory[1][2]);");
            writer.newLine();
            writer.flush();

            String ret = reader.readLine();
            return ret;
        }
    }
}
