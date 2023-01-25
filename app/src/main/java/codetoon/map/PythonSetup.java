package codetoon.map;

import codetoon.system.Rule;
import org.python.util.PythonInterpreter;

import java.awt.*;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Properties;

public class PythonSetup extends PazzleStage{
    public PythonSetup(int memorySize) {
        super(memorySize);
        Properties props = new Properties();
        props.put("python.console.encoding", "UTF-8");

        PythonInterpreter.initialize(System.getProperties(), props, new String[0]);
        try (PythonInterpreter interp = new PythonInterpreter()) {
        }
    }
}
