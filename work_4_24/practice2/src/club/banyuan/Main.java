package club.banyuan;

import club.banyuan.Person;

class Main{
    public static void main(String[] args) {
    Person person = new Person();
    Person zhangsan = new Person("张三", 18);
    Person lisi = new Person("李四");

    person.speak();
    zhangsan.speak();
    lisi.speak();

    }
}