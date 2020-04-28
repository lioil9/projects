## 练习
1. 
```java
class Base {
    public void show() {
       System.out.println("Base::show() called");
    }
}
class Derived extends Base {
    public void show() {
       System.out.println("Derived::show() called");
    }
} 
/**
* 这里子类重写父类的方法，b指向的是子类对象Derived
* 而在调用时b可以调用父类方法，但是指向的子类对象中对其进行重写，调用子类方法；
*/
public class Main {
    public static void main(String[] args) {
        Base b = new Derived();
        b.show();
    }
}
```
> 输出结果：Derived::show() called

2. 
```java
class A {
    int i = 10;
}
class B extends A {
    int i = 20;
}
/**
* 这里的a是父类对象A的引用，而指向的是子类对象B；
* 而因为子类与父类中有同名成员变量，对象的属性不具有多态，则在声明父类A时i值就已经确定
*/
public class MainClass {
    public static void main(String[] args) {
        A a = new B();
        System.out.println(a.i);
    }
}
```
> 输出结果：10

3.
```java
class A {
    {
        System.out.println(1);
    }
}
class B extends A {
    {
        System.out.println(2);
    }
}
 
class C extends B {
    {
        System.out.println(3);
    }
}
/**
* 子类对象在创建时会首先创建一个父类对象，普通代码块则在这个类的对象创建时调用，
* 所以创建c对象前会依次创建A和B类的对象，然后依次调用A，B，C的代码块
*/
public class MainClass {
    public static void main(String[] args) {
        C c = new C();
    }
}
```
> 输出结果：
>1
>2
>3

4.
```java
class A {
    public A() {
        System.out.println("Class A Constructor");
    }
}
class B extends A {
    public B() {
        System.out.println("Class B Constructor");
    }
}
class C extends B {
    public C() {
        System.out.println("Class C Constructor");
    }
}
/**
* 同上一题，在创建C类的对象前，先创建A，B的对象，构造时就依次输出A，B，C中的字符串
*/
public class MainClass {
    public static void main(String[] args) {
        C c = new C();
    }
}
```
> 输出结果：
> Class A Constructor
> Class B Constructor
> Class C Constructor

5. 
```java
class X {
    public X(int i) {
        System.out.println(1);
    }
}
/**
* 父类构造方法中是一个有参构造方法，而子类构造方法中是一个无参构造
* 因为在构造子类Y时需要先构造父类X，但在Y构造方法中没有调用父类构造方法
*/
class Y extends X {
    public Y() {
        System.out.println(2);
    }
}
```
> 输出结果：编译错误

6. 
```java
public class A {
/**
* 在子类构造方法中使用 super() 应当是构造器中的第一个语句才能够先构造父类然后再构造子类
*/
    public A() {
        System.out.println(1);
        super();
        System.out.println(2);
    }
}
```
> 输出结果： 编译错误

7. 
```java
public class A {
    public A(int i){
 
    }
}
/**
* B类继承子类，B中会有一个隐式的无参构造方法，但父类A中只有一个有参的构造方法
* 所以想构造B时，需要在构造器中显示调用父类的有参构造方法
*/
class B extends A {
 
}
```
> 输出结果： 编译错误

8. 
```java
public class A {
    public A() {
        super();
        this(10);
    }
 /**
* 这里的 this(10) 是调用当前类的构造方法；但是当前类的其他构造方法中也有隐式的 super()构造方法
* 而之前已经有一个 super() 父类构造，相当于构造了两次父类对象，是不允许的；所以 this() 在调用其他构造方法时需要在第一行
*/
    public A(int i) {
        System.out.println(i);
    }
}
```
> 输出结果： 编译错误

9.
```java
class M{
    int i;

    public M(int i) {
        this.i = i--;
    }
}
class N extends M {
    public N(int i) {
        super(++i);
        System.out.println(i);
    }
}
/**
* 这里创建一个N类的子类对象前会先创建M类对象
* 在构造父类M对象时会传入 26+1 的值给父类构造器，又i--是先赋值再-1；
* 所以父类的i为27，此时构造子类对象时继承父类i的值为27，输出为27
*/
public class MainClass {
    public static void main(String[] args) {
        N n = new N(26);
    }
}
```
> 输出结果：27

10. 
```java
/**
* 构建父类，传入参数为26 打印
* 当前父类i值为260
*/
class M {
    int i = 51;
    public M(int j) {
        System.out.println(i);
        this.i = j * 10;
    }
}
/**
* 继承父类i的值260 输出，改变子类i的值为520
*/
class N extends M {
    public N(int j) {
        super(j);
        System.out.println(i);
        this.i = j * 20;
    }
}
/**
* 创建N对象n 入参为26
* 输出子类的i值520
*/
public class MainClass {
    public static void main(String[] args) {
        N n = new N(26);
        System.out.println(n.i);
    }
}
```
> 输出结果：
>51 
>260 
>520

11.
```java
class X {
    private int m = 48;
}
//子类不能够继承父类中的private变量
class Y extends X {
    void methodOfY() {
        System.out.println(m);
    }
}
```
> 输出结果：编译错误

12. 
```java
class X {
    int m = 1111;
    {
        m = m++;
        System.out.println(m);
    }
}
 
class Y extends X {
    {
        System.out.println(methodOfY());
    }
 
    int methodOfY() {
        return m-- + --m;
    }
}
//
public class MainClass {
    public static void main(String[] args) {
        Y y = new Y();
    }
}
```
> 输出结果：
>1111
>2220