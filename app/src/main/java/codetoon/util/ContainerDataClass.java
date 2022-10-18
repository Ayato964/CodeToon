package codetoon.util;

public interface ContainerDataClass<T, I> extends ContainerData<T, I> {
    public Object newInstance();
}
