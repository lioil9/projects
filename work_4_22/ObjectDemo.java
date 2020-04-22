/**
 * 创建一个大象类
 * 属性为其高度
 */
class Elephant{
    int heightInCM; // field properties 域
}

/**
 * 创建一个狮子类
 * 属性为其高度
 */
class Lion{
    int heightInCM;
}

/**
 * 创建一个冰箱类，
 * 属性包含其高度以及要保存东西的对象
 */
class Fridge{
    int heightInCM;
    Elephant storeElephant;
    Lion storeLion;

    /**
     * 在冰箱中保存大象对象
     * @param elephant
     */
    void store(Elephant elephant){
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
    void store(Lion lion){
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
    void remove(){
        storeElephant = null;
        System.out.println("大象从冰箱里面取出来了");
    }
}

class ObjectDemo{
    /**
     * 创建一个狮子类对象并赋予其体重值的属性
     * 然后将对象返回
     * @param heightInCM
     * @return
     */
    static Lion createLion(int heightInCM){
        Lion lion = new Lion();
        lion.heightInCM = heightInCM;
        return lion;
    }
    public static void main(String[] args) {
        Elephant elephant = new Elephant();
        elephant.heightInCM = 300;

        Fridge fridge = new Fridge();
        fridge.heightInCM = 500;

        fridge.store(elephant);
        fridge.remove();

        Lion lion = createLion(100);
        fridge.store(lion);

        fridge.store(elephant);

    }
}
