package codetoon.util.lang;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class LangLoader {
    private URI filePath;
    private File file;
    public HashMap<String, String> code;
    private static LangLoader INSTANCE;
    public static String tempKey;
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
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
       // System.out.println(code.get(s));
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
        if(INSTANCE == null) {
            INSTANCE = new LangLoader(lang);
        }
    }

    public static LangLoader getInstance() {
        return INSTANCE;
    }
}
