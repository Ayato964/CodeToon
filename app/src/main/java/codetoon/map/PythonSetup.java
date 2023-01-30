package codetoon.map;

import codetoon.system.Memories;
import codetoon.system.Memory;
import codetoon.system.Rule;
import codetoon.variable.MemoryVariable;
import codetoon.variable.Variables;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.*;

public class PythonSetup extends PazzleStage implements Runnable{
    public PythonSetup(int memorySize) {
        super(memorySize);
/*
        Properties props = new Properties();
        props.put("python.console.encoding", "UTF-8");
        PythonInterpreter.initialize(System.getProperties(), props, new String[0]);
        try (PythonInterpreter interp = new PythonInterpreter()) {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("python/main.py");
            interp.execfile(stream);
        }

 */
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
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Ai());

        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

    }
    private class Ai implements Callable<String>{

        @Override
        public String call() throws Exception {
            String str = getClass().getClassLoader().getResource("./python/main.py").toString();
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
