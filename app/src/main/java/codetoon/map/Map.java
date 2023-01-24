package codetoon.map;

import java.awt.*;
import java.net.UnknownHostException;

public abstract class Map{
    public Map(){
    }
    public abstract void setup(Graphics g) throws UnknownHostException;
    public abstract void display(Graphics g);
  }