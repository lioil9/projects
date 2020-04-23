package club.banyuan;

import club.banyuan.animal.Elephant;
import club.banyuan.animal.Lion;
import club.banyuan.machine.Fridge;

class Main{
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
