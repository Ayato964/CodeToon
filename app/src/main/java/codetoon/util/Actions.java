package codetoon.util;

public interface Actions<T, I>{
    public void action(T t);
    public I returnAction(T t);
    public Object newInstance();
}
