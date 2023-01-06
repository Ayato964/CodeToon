package codetoon.util;
@Deprecated(since = "1.2")
public interface ContainerDataClass<T, I> extends ContainerData<T, I> {
    public Object newInstance();
}
