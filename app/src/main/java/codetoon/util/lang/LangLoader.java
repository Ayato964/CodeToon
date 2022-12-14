package codetoon.util.lang;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public class LangLoader {
    private URI filePath;
    private File file;
    public HashMap<String, String> code;
    private static LangLoader INSTANCE;
    private LangLoader(String lang){
        code = new HashMap<>();
        try {
            filePath = getClass().getResource("/assets/codetoon/lang/" + lang + ".lang").toURI();
            file = new File(filePath);
            readFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    private void readFile() {
        try {
            FileReader reader = new FileReader(file);
            StringBuilder b = new StringBuilder();
            int charCode;
            boolean isNonSpaceCollider = false;
            System.out.println("------------Language Loading-------------");
            System.out.println(reader.getEncoding());
            while ((charCode = reader.read()) != -1) {
                if(!((char) charCode == ' ' || (char) charCode == '\n') || isNonSpaceCollider){
                    if((char) charCode == ','){
                        System.out.println(b.toString());
                        addHashMap(b);
                        b = new StringBuilder();
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
    public String get(String s){
        if(code.get(s) != null) {
            return code.get(s);
        }else {
            return s;
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
            code.put(s.toString(), s.toString());
        }else {
            code.put(s.substring(0, eq), s.substring(eq + 1, s.length()));
        }
    }
    public static void create(String lang){
        if(INSTANCE == null) {
            INSTANCE = new LangLoader(lang);
        }
    }

    public static LangLoader getInstance() {
        return INSTANCE;
    }
}