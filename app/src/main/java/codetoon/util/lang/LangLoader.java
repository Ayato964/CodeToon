package codetoon.util.lang;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.io.*;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;

public class LangLoader {
    public static final String JAPANESE = "ja_jp";
    public static final String ENGLISH = "en_us";
    public static final String CHINESE = "zh_cn";
    public static String LANGUAGE;
    private  Path path;
    private URI uri;
    private BufferedReader reader;
    public HashMap<String, String> code;
    private static LangLoader INSTANCE;
    public static String tempKey;
    private LangLoader(String lang){
        loadFile(lang);
    }
    private void loadFile(String lang){
        LANGUAGE = lang;
        code = new HashMap<>();
        URL filePath = getClass().getClassLoader().getResource("assets/codetoon/lang/" + lang + ".lang");

        if(filePath.toString().indexOf("jar:") != -1) {
            try(InputStream is = ClassLoader.getSystemResourceAsStream("assets/codetoon/lang/" + lang + ".lang")) {
                reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                JOptionPane.showMessageDialog(new JFrame(), reader.readLine());
                readFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            File file = new File(filePath.getFile());
            readFile(file);
        }

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
    public String get(String[] value, String s){
        return code.get(s) != null ? getString(value, code.get(s)) : s;
    }

    private String getString(String[] value, String s) {
            int valueCount = 0;
            StringBuilder newString = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '$') {
                    if (valueCount < value.length) {
                        newString.append(value[valueCount]);
                        valueCount++;
                    } else {
                        newString.append("NaN");
                    }
                } else {
                    newString.append(s.charAt(i));
                }
            }
            return newString.toString();
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
            code.put(s.toString(), s.toString());
        }else {
            code.put(s.substring(0, eq), s.substring(eq + 1, s.length()));
            tempKey = s.substring(0, eq);
        }
    }
    public static void create(String lang){
        if(INSTANCE == null)
            INSTANCE = new LangLoader(lang);
        else
            INSTANCE.loadFile(lang);
    }

    public static LangLoader getInstance() {
        return INSTANCE;
    }
}
