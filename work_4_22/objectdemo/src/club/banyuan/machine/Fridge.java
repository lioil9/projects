package club.banyuan.machine;

import club.banyuan.animal.Elephant;
import club.banyuan.animal.Lion;

/**
 * 创建一个冰箱类，
 * 属性包含其高度以及要保存东西的对象
 */
public class Fridge{
    public int heightInCM;
    public Elephant storeElephant;
    public Lion storeLion;

    /**
     * 在冰箱中保存大象对象
     * @param elephant
     */
    public void store(Elephant elephant){
        if(storeLion != null)
            System.out.println("大象不能装，冰箱已经满了");
        else{
            storeElephant = elephant;
            System.out.println("大象被装进去了");
        }   
    }

    /**
     * 重载store方法，保存狮子对象
     * @param lion
     */
    public void store(Lion lion){
        if(storeElephant != null)
            System.out.println("狮子不能装，冰箱已经满了");
        else{
            storeLion = lion;
            System.out.println("狮子被装进去了");
        }       
    }

    /**
     * 将大象从冰箱中移除
     * @return
     */
    public void remove(){
        storeElephant = null;
        System.out.println("大象从冰箱里面取出来了");
    }
}
