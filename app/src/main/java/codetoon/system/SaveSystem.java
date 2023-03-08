package codetoon.system;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class SaveSystem {
    private static SaveSystem instance = new SaveSystem();
    HashMap<String, Object> data;
    private BufferedReader reader;
    private String tempKey;


    private SaveSystem(){
        data = new HashMap<>();
        URL filePath = getClass().getClassLoader().getResource("saves/save.data");
        if(filePath != null) {
            if (filePath.toString().indexOf("jar:") != -1) {
                try (InputStream is = ClassLoader.getSystemResourceAsStream("saves/save.data")) {
                    reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    readFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                File file = new File(filePath.getFile());
                readFile(file);
            }
        }
    }

    public void save(){

    }
    public void load(){

    }
    private void readFile(File f){
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        readFile();
    }
    private void readFile() {
        try {
            //FileReader reader = new FileReader(file);
            StringBuilder b = new StringBuilder();
            int charCode;
            boolean isNonSpaceCollider = false;
            System.out.println("------------Language Loading-------------");
            System.out.println(System.getProperty("file.encoding"));
            while ((charCode = reader.read()) != -1) {
                if(!((char) charCode == ' ' || (char) charCode == '\n') || isNonSpaceCollider){
                    if((char) charCode == ','){
                        System.out.println(b);
                        addHashMap(b);
                        b = new StringBuilder();
                        isNonSpaceCollider = false;
                    }else if((char) charCode == '\"') {
                        isNonSpaceCollider = !isNonSpaceCollider;
                    }else {
                        b.append((char) charCode);
                    }
                }
            }
            System.out.println("----------------------------------------");
        }catch (IOException e){

        }
    }
    private void addHashMap(StringBuilder s){
        int eq = -1;
        for(int i = 0; i < s.length(); i ++){
            if(s.charAt(i) == '='){
                eq = i;
                break;
            }
        }
        if(eq == -1){
            data.put(s.toString(), s.toString());
        }else {
            data.put(s.substring(0, eq), s.substring(eq + 1, s.length()));
            tempKey = s.substring(0, eq);
        }
    }

    public static SaveSystem getInstance() {
        return instance;
    }
}
