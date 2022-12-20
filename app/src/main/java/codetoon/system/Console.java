package codetoon.system;

import codetoon.main.*;
import codetoon.util.*;
import codetoon.method.*;
import codetoon.regi.*;
import codetoon.util.converter.ConvertSource;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.image.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.*;

/** ユーザーがプログラムを打ち込むインターフェース **/
public class Console extends JFrame implements KeyListener{
  private int x, y, w, h;
  public ConsolePanel panel;
  private Player host;

  private ArrayList<MyMethod> methods;
  public Console(int x, int y, int w, int h){
    this.x = x * Main.DW;
    this.y = y * Main.DH;
    this.w = w * Main.DW;
    this.h = h * Main.DH;
    methods = null;
    host = null;
    setLocation(this.x, this.y);
    panel = new ConsolePanel();
    add("Center", panel);
    pack();

    addKeyListener(this);
  }

  public void setHost(Player h){
    host = h;
  }

  public Player getHost() {
    return host;
  }

  public ArrayList<MyMethod> getMethods() {
    return methods;
  }

  public class ConsolePanel extends JLabel{
    private final BufferedImage image;
    private final Graphics g;
    private int program_count;
    private StringBuilder program;
    Font font = new Font("Serif", Font.PLAIN, 32);
    public ConsolePanel(){
      program_count = 0;
      image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
      setIcon(new ImageIcon(image));
      g = image.createGraphics(); 
      program = new StringBuilder();
      setHost(Admin.getInstance());
      drawString(program.isEmpty() ? "" : program.toString(), 20, 30);
    }

    public void setProgram(StringBuilder program) {
      this.program = program;
    }

    /** テキストエディタ作成 **/

    public void drawInputKey(KeyEvent e){
      if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
        program.deleteCharAt(program_count - 1);
        program_count --;
      }else{
        program.insert(program_count, e.getKeyChar());
        program_count ++;
        if(e.getKeyChar() == '\n' && program.charAt(program_count - 2) == '{') {
          for(int i = 0; i < 4; i ++){
            program.insert(program_count, ' ');
            program_count ++;
          }
        }
      }
      //System.out.println(program.toString());
      
      g.setFont(font); 
      drawString(program.toString(), 20, 20);
      repaint();
    }
    public void setCarsor(KeyEvent e){
      switch(e.getKeyCode()){
        case KeyEvent.VK_LEFT: if(isNotOutOfIndex())program_count --; drawString(program.toString(), 20, 20);break;
        case KeyEvent.VK_RIGHT:if(isNotOutOfLength()) program_count ++; drawString(program.toString(), 20, 20);break;
        case KeyEvent.VK_UP: upCarsour();drawString(program.toString(), 20, 20);break;
        case KeyEvent.VK_DOWN:downCarsour();drawString(program.toString(), 20, 20);break;
        
      }
      repaint();
    }
    private boolean isNotOutOfIndex(){
      return program_count > 0;
    }
    private boolean isNotOutOfLength(){
      return program_count < program.length();
    }
    private void downCarsour(){
      int a = getEnterPoint(program_count);
      int b = a + getCountStringLine(a) + 2;
      int c = program_count - a;
      if(getCountStringLine(b) >= getCountStringLine(a) -1){
        program_count = b + c - 1; 
    }else{
        program_count = b + getCountStringLine(b);
    }
      
    }
    private void upCarsour(){
      int a = getEnterPoint(program_count);
      System.out.println("よってaの値を" + a + "とする。");
      int b = getEnterPoint(a - 2);
      int c = program_count - a;
      if(getCountStringLine(b) >= getCountStringLine(a)){
        program_count = b + c;
      }else{
        program_count = b + getCountStringLine(b);
      }
    }
    private int getCountStringLine(int beginLine){
      int count = 0;
      for(int i = beginLine - 1; !(i + 1 >= program.length() || program.charAt(i + 1) == '\n'); i ++){
        count ++;
      }
      //System.out.println("Length::" + count);
      return count;
    }
    
    private int getEnterPoint(int begin){
      if(begin - 1 > 0 && program.charAt(begin - 1) != '\n'){
        return getEnterPoint(begin - 1);
      }
      if(begin - 1 <= 0){
        return 0;
      }
      if(program.charAt(begin - 1) == '\n'){
        return begin;
      }
      return 0;
    } 
    private int getEnterNum(){
      int i = 0;
      for(int c = 0; c < program_count; c ++){
        if(program.charAt(c) == '\n'){
          i ++;
        }
      }
      return i;

    }
    public void drawString(){
      drawString(program.isEmpty() ? " " : program.toString(), 20, 30);
    }
    private void drawString(@NotNull String str, int x, int y){
      int yy = y;
      reset();
      for(String line : str.split("\n")){
       g.drawString(line, x, yy += g.getFontMetrics().getHeight());     
      
      }
      drawPoint(x, y);
    }
    private void drawPoint(int x, int y){ 
      g.drawString("Terminal_Host-> " + host.getName(), 10, 20);
      setTitle("Terminal:" + host.getName());
      int point = getEnterPoint(program_count);
      g.fillRect(x + g.getFontMetrics().stringWidth(program.substring(point, program_count)), y + (g.getFontMetrics().getHeight() * getEnterNum()) , 5, g.getFontMetrics().getHeight());

    }
    public void reset(){
      g.clearRect(0, 0, getWidth(), getHeight());
    }
    public void resetAll(){
      g.clearRect(0, 0, getWidth(), getHeight());
      if(!isHave(Methods.CONNECT)) {
        program = new StringBuilder();
      }
      program_count = 0;
      drawString(program.isEmpty() ? "" : program.toString(),20, 30);

    }
    
  }
  /** Program識別 **/
  public <T extends MyMethod> boolean isHave(RegistoryObject<T> m){
    if(methods != null){
      for(int i = 0; i < methods.size(); i ++){
        if(methods.get(i).getClass() == m.get().getClass() ){
          return true;
        }
      }
    }
    return false;
  }
  
  @Override
   public void keyTyped(KeyEvent e){
        panel.drawInputKey(e);
      //  methods = Indentification.indentification(panel.program.toString(), host);
        methods = ConvertSource.convert(panel.program.toString(), host);
        if(isHave(Methods.END)){
            host.endMethod(this, methods, panel.program);
        }
        if(isHave(Methods.REMOVE)){
          Methods.REMOVE.get().action(0);
        }
        //System.out.println(methods == null);
   }
  @Override
  public void keyPressed(KeyEvent e){
      panel.setCarsor(e);
  }
  @Override
  public void keyReleased(KeyEvent e){
 
  }
}