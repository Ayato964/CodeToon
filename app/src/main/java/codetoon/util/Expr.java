package codetoon.util;

import java.io.*;

/**
 * Source is made by "BASIN"...!!
 * **/
public class Expr {
  char c;
  int pos = 0, val = 0;
  String str, o = "+-*/";

  public Expr(String s) {
    str = s;
  }

  char get() {
    while (pos < str.length())
      if ((c = str.charAt(pos++)) != ' ') {
        if (!Character.isDigit(c))
          return c;
        for (val = c - '0';;) {
          if (pos == str.length())
            return c = '0';
          c = str.charAt(pos++);
          if (!Character.isDigit(c)) {
            pos--;
            return c = '0';
          }
          val = val * 10 + (c - '0');
        }
      }
    return c = 0;
  }

  public int expr(int i) {
    int v;
    if (i < o.length())
      for (v = expr(i + 2); c == o.charAt(i) || c == o.charAt(i + 1);)
        if (c == '+')
          v += expr(i + 2);
        else if (c == '-')
          v -= expr(i + 2);
        else if (c == '*')
          v *= expr(i + 2);
        else
          v /= expr(i + 2);
    else if (get() == '0') {
      v = val;
      get();
    } else if (c == '(') {
      v = expr(0);
      if (c == ')')
        get();
      else
        c = 2;
    } else if (c == '+')
      v = expr(i);
    else if (c == '-')
      v = -expr(i);
    else {
      v = 0;
      c = 1;
    }
    return v;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("数式を入力>");
    String s = br.readLine();
    Expr e = new Expr(s);
    int v = e.expr(0);
    System.out.println(e.c == 0 ? " " + v : " Error");
  }
}