package codetoon.util;
/** 
�f�[�^��ۊǁA���o����e�Ղɂ��邽�߂ɍ��ꂽ�N���X�B
�[���Q�l�FMinecraft
**/
public interface ContainerData<T, I>{
    public void action(int i);
    public int getCount();
    public T set(I i);
    
  }