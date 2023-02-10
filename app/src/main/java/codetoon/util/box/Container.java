package codetoon.util.box;
@Deprecated(since = "1.7.0")
public interface Container<T> {
    public void add(T o);
    public void set(T o, int i);
    public void remove(int i);
    public void draw();   
}
