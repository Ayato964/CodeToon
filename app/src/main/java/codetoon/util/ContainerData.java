package codetoon.util;
/** 
データを保管、取り出しを容易にするために作られたクラス。
ー＞参考：Minecraft
**/
public interface ContainerData<T, I> extends Action{
    @Override
    public void action(int i);
    public int getCount();
    public T set(I i);
    
  }