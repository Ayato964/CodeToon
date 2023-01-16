package codetoon.util.animation;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class AnimationsPack {
    private int id;
    private AnimationsPack next;
    private ArrayList<Animation> list;

    private AnimationsPack(){
        next = null;
        list = new ArrayList<>();
        id = 0;
    }
    private AnimationsPack(int id){
        this();
        this.id = id;
    }


    public void add(Animation animation){
        if(next == null)
            list.add(animation);
        else
            next.add(animation);

    }
    public void next(){
        next = create(id + 1);
    }
    public void set(int id){
        stopAll();

        if(this.id != id) {
            if (next != null) {
                next.set(id);
            }
        }else{
            for(Animation a : list)
                a.myProp.start();
        }
    }
    public void stopAll(){
        for(Animation a: list)
            a.myProp.stop();
        if(next != null)
            next.stopAll();
    }
    public static AnimationsPack create(){
        return new AnimationsPack();
    }
    private AnimationsPack create(int id){
        return new AnimationsPack(id);
    }
}
