

## 练习

```
javac -cp src:dist/main.jar src/club/banyuan/animal/Dog.java -d out  
java -cp dist/main.jar:lib/Dog.jar club.banyuan.Main  
jar uf dist/main.jar out/club/banyuan/animal/Dog.class  
java -cp dist/main.jar club.banyuan.Main  
```

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
* 所以在调用子类的 show方法时会自动屏蔽父类方法；从而调用子类的show方法
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
/**
 * 首先构造父类X的对象，初始化m 1111，运行代码块语句，m=m++; 后m的值为1111，打印
 */
class X {
    int m = 1111;
    {
        m = m++;
        System.out.println(m);
    }
}
//构造Y类对象 ，调用methodOfY()方法，此时m的值为1111，先计算--m，得到m值1110，然后m--先与后面的加再计算，返回值2220，打印
class Y extends X {
    {
        System.out.println(methodOfY());
    }
 
    int methodOfY() {
        return m-- + --m;
    }
}
//新建Y类对象y
public class MainClass {
    public static void main(String[] args) {
        Y y = new Y();
    }
}
```
> 输出结果：
>1111
>2220

13. 
```java
class A {
	void A()
	{
		System.out.println(1);
	}
}

class B extends A {
	void B()
	{
		A();
	}
}
//新建B类对象，也新建了一个A类父类对象，并且B类对象继承A类方法，调用B类对象方法，里面再调用基础来的A父类方法，输出1
public class MainClass {
	public static void main(String[] args)
	{
		new B().B();
	}
}
```
> 输出结果: 1

14.
```java
class A
{
	int i = 1212;
}

class B extends A
{
	A a;

	public B(A a)
	{
		this.a = a;
	}
}

public class MainClass
{
	public static void main(String[] args)
	{
        //先创建了一个A类对象a，i值为1212
		A a = new A();
        //又创建了一个B类对象，继承A类对象初始化i值1212，将a传入构造方法构造对象，此时b对象有一个a对象成员属性
		B b = new B(a);

		System.out.println(a.i);

		System.out.println(b.i);

		System.out.println(b.a.i);
        //将对象a的值进行改变，此时b对象的i值因为从父类继承是属于b对象的，没有改变
		b.a.i = 2121;

		System.out.println("--------");

		System.out.println(a.i);

		System.out.println(b.i);
	}
}
```
> 输出结果：
> 1212 
> 1212 
> 1212 
--------
> 2121 
> 1212

15.
```java
class A
{
    //入参2000 ； i除以10后为200
	int methodOfA(int i)
	{
		i /= 10;

		return i;
	}
}

class B extends A
{
    //调用methodB方法入参 100； i=2000 ,返回调用父类方法
	int methodOfB(int i)
	{
		i *= 20;

		return methodOfA(i);
	}
}

public class MainClass
{
	public static void main(String[] args)
	{
        //新建B类对象b
		B b = new B();
        //调用对象b的methodB方法入参100，返回值200 打印
		System.out.println(b.methodOfB(100));
	}
}
```
> 输出结果：200

16.
```java
class One {
	int x = 2121;
}

class Two extends One{
	int x = 1212;
	
	{
		System.out.println(x);
	}
}
/**声明创建Two类对象two,这里先是构造父类One对象其x值为2121，
*然后构造Two对象其x值为1212，最后打印输出的是当前类的对象也就是Two对象的值为1212
*/
public class MainClass {
	public static void main(String[] args)
	{
		Two two = new Two();
	}
}
```
> 输出结果：1212