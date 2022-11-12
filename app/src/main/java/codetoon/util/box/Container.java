package codetoon.util.box;

public interface Container<T> {
    public void add(T o);
    public void set(T o, int i);
    public void remove(int i);
    public void draw();   
}
