### 以下程序的输出结果是
#### 1.
```java
interface A
{
    void myMethod();
}
 
class B
{
    public void myMethod()
    {
        System.out.println("My Method");
    }
}
 
class C extends B implements A
{
     
}
 //C继承B的myMethod方法，然后对接口A的抽象方法进行重写，调用C类对象继承重写的方法
class MainClass
{
    public static void main(String[] args) 
    {
        A a = new C();
         
        a.myMethod();
    }
}
```
> 输出结果：My Method

#### 2.

```java
interface A 
{ 
    void myMethod(); 
    void getInfo(); 
} 
  
abstract class B implements A 
{ 
    void getData() 
    { 
        System.out.println("B"); 
    } 
} 
// 抽象类B使用接口A不需要将抽象方法进行重写，Test继承B的getData方法，并对接口A中的方法重写，最后Test对象obj调用自己的方法getInfo
public class Test extends B 
{ 
    public void myMethod() 
    { 
        System.out.println("myMethod"); 
    } 
    public void getInfo() 
    { 
        System.out.println("getInfo"); 
    } 
      
    public static void main(String[] args) 
    { 
        B obj = new Test(); 
        obj.getInfo(); 
    } 
}
```
> 输出结果：getInfo

#### 3.

```java
interface A
{
    int i = 111;
}
 //B使用接口A，A中的i为常量，不能够再次重新赋值
class B implements A 
{
    void methodB()
    {
        i = 222;
    }
}
```

> 编译报错

#### 4.以下内容是否编译通过，如果不能请指出错误原因

```java
class A
{
    //Class A
}
//接口默认是抽象类只能够被继承或者继承抽象类，不能够继承实用类
interface B extends A
{
    //Interface B extending Class A
}
```
> 编译报错

#### 5.

```java
interface P
{
    String p = "PPPP";
     
    String methodP();
}
 
interface Q extends P
{
    String q = "QQQQ";
     
    String methodQ();
}
 
class R implements P, Q
{
    public String methodP()
    {
        return q+p;
    }
     
    public String methodQ()
    {
        return p+q;
    }
}
 //R使用接口P和Q，重写了接口中的方法，并且其中的字符串常量在接口调用时就已经生成，可以使用，用R的对象调用重写后的方法打印输出
public class MainClass 
{
    public static void main(String[] args)
    {
        R r = new R();
         
        System.out.println(r.methodP());
         
        System.out.println(r.methodQ());
    }
}
```
> QQQQPPPP
>
> PPPPQQQQ

#### 6.

```java
class A implements B
{   
    public int methodB(int i)
    {
        return i =+ i * i;
    }
}
 
interface B
{
    int methodB(int i);
}
 //A使用B接口并对里面的方法重写，用A对象调用方法，入参为2，赋值号后面的+代表正号，输出4
public class MainClass 
{
    public static void main(String[] args)
    {
        B b = new A();
         
        System.out.println(b.methodB(2));
    }
}
```

> 4

####  7.以下内容是否编译通过，如果不能请指出错误原因

```java
interface A
{
    {
        System.out.println("Interface A");
    }
     //接口中只能够静态方法和默认default方法才能有方法体，且接口不能初始化
    static
    {
        System.out.println("Interface A");
    }
}
```
> 编译错误

####  8.以下内容是否编译通过，如果不能请指出错误原因

```java
interface ABC
{
	public void methodOne();
	
	public void methodTwo();
}

interface PQR extends ABC
{
	public void methodOne();
	
	public void methodTwo();
}
```
> 编译通过

#### 9.

```java
interface ABC
{
	void methodOne();
}

interface PQR extends ABC
{	
	void methodTwo();
}

abstract class XYZ implements PQR
{
	public void methodOne()
	{
		methodTwo();
	}
}

class MNO extends XYZ
{
	public void methodTwo()
	{
		methodOne();
	}
}
// XYZ是抽象类不需要对接口抽象方法完全进行重写，而子类中继承methodOne方法对接口中方法进行了重写，但两个方法互相循环调用，会产生栈溢出
public class MainClass
{
	public static void main(String[] args)
	{
		ABC abc = new MNO();
		
		abc.methodOne();
	}
}
```
> 编译通过，不过运行会产生栈溢出

#### 10.
```java
interface X
{
    char c = 'A';
     
    char methodX();
}
 
class Y implements X
{
    {
        System.out.println(c);
    }
     
    public char methodX()
    {
        char c = this.c;
         
        return ++c;
    }
}
// 首先在实例化Y对象时运行普通代码块的语句，打印A，然后执行Y类重写接口的方法，将常量赋值给变量并且加1打印为B，然后直接调用对象y里面从接口中的使用的常量，再用接口名直接调用常量c
public class MainClass
{
    public static void main(String[] args)
    {
        Y y = new Y();
         
        System.out.println(y.methodX());
         
        System.out.println(y.c);
         
        System.out.println(X.c);
    }
}
```
> A  
> B  
> A  
> A  
#### 12.以下内容是否编译通过，如果不能请指出错误原因
```java
interface A
{
    void methodA();
}
//不能够在方法中再声明定义接口
class B implements A
{
    public void methodA()
    {
        interface C
        {
            int i = 123;
        }
    }
}
```
> 编译错误  
#### 13.
```java
interface One
{
    String s = "FINAL";
     
    String methodONE();
}
 
interface Two
{
    String methodONE();
}
 
abstract class Three
{
    String s = "NOT FINAL";
     
    public abstract String methodONE();
}
 
class Four extends Three implements One, Two
{
    public String methodONE()
    {
        String s = super.s + One.s;
         
        return s;
    }
}
// 生成Four类对象，并且重写One和Two接口的方法，将父类的字符串变量s和接口中字符串常量拼接，并且将four对象指向接口One引用，调用常量s字符串 FINAL
public class MainClass
{
    public static void main(String[] args)
    {
        Four four = new Four();
         
        System.out.println(four.methodONE());
         
        One one = four;
         
        System.out.println(one.s);
    }
}

```
> NOT FINALFINAL  
> FINAL  
#### 14.
```java
interface X
{
    void method();
}
 
class Y
{
    public void method()
    {
        System.out.println("CLASS Y");
    }
}
 
class Z extends Y implements X
{
     
}
// Z继承Y实例接口X，继承Y中的方法重写接口中的方法，然后调用Z对象重写的方法
public class MainClass
{
    public static void main(String[] args)
    {
        X x = new Z();
         
        x.method();
    }
}
```
> CLASS Y  
#### 15.
```java
interface A
{
    int methodA();
}
 
interface B
{
    int methodB();
}
 
interface C
{
    int methodC();
}
 
class D implements A, B, C
{
    int i = 999+111;
     
    public int methodA()
    {
        i =+ i / i;
         
        return i;
    }
     
    public int methodB()
    {
        i =- i * i;
         
        return i;
    }
     
    public int methodC()
    {
        i = ++i - --i;
         
        return i;
    }
}
 //D重写A B C接口的方法，首先用D对象打印输出对象成员变量，然后调用方法A返回i为1；再乘上自己的负值为-1，再用方法C返回 0-(-1) 为1
public class MainClass
{
    public static void main(String[] args)
    {
        D d = new D();
         
        System.out.println(d.i);
         
        System.out.println(d.methodA());
         
        System.out.println(d.methodB());
         
        System.out.println(d.methodC());
    }
}
```
> 1110  
> 1  
> -1  
> 1  
#### 16.
```java
interface One
{
    int i = 222;
     
    interface OneTwo
    {
        int i = One.i+One.i;
         
        interface OneTwoThree
        {
            int i = OneTwo.i + OneTwo.i;
        }
    }
    //可以在接口内部嵌套定义接口
}
> 编译成功  
```
#### 17.
```java
interface A
{
	String A = "AAA";
	
	String methodA();
}

interface B
{
	String B = "BBB";
	
	String methodB();
}

class C implements A, B
{
	public String methodA()
	{
		return A+B;
	}
	
	public String methodB()
	{
		return B+A;
	}
}

class D extends C implements A, B
{
	String D = "DDD";
	
	public String methodA()
	{
		return D+methodB();
	}
}
// C类使用接口A，B重写接口的方法并打印输出，D类继承C并且重写父类中的方法以及接口的方法，最后调用的是子类重写的方法
public class MainClass
{
	public static void main(String[] args)
	{
		C c = new C();
		
		System.out.println(c.methodA());
		
		System.out.println(c.methodB());
		
		c = new D();
		
		System.out.println(c.methodA());
		
		System.out.println(c.methodB());
	}
}
```
> AAABBB  
> BBBAAA  
> DDDBBBAAA  
> BBBAAA  
#### 18.
```java
interface X
{
    void methodX();
}
 
interface Y extends X
{
    void methodY();
}
 //Z类需要重写接口Y的父类中的抽象方法
class Z implements Y
{
    public void methodY()
    {
        System.out.println("Method Y");
    }
}
```
> 编译报错  
#### 19.
```java
abstract class A
{
    abstract void myMethod(Number N);
}
 
interface B
{
    abstract void myMethod(Object O);
}
 
class C extends A implements B
{
    void myMethod(Number N) 
    {
        System.out.println("Number");
    }
     
    public void myMethod(Object O)
    {
        System.out.println("Object");
    }
}
//当父类与接口方法冲突时，默认先调用父类方法，而在对象指向接口引用时，调用的是重写接口的方法
public class MainClass
{
    public static void main(String[] args)
    {
        A a = new C();
         
        a.myMethod(new Integer(121));
         
        B b = new C();
         
        b.myMethod(new Integer(121));
         
        C c = new C();
         
        c.myMethod(new Integer(121));
    }
}
```
> Number  
> Object  
> Number  
#### 20.
```java
class A { }
 
class B extends A { }
 
class C extends B { }
 
interface ABC
{
    void method(A a);
}
 
interface PQR
{
    void method(B b);
}
 
class M implements ABC, PQR
{   
    public void method(A a)
    {
        System.out.println(2);
    }
     
    public void method(B b) 
    {
        System.out.println(3);
    }
}
//B类继承A，C继承B类，传入A对象打印2，传入B对象打印3，当传入C对象，向上转型传入父类对象调用传参为父类对象的方法，打印3
public class MainClass
{
    public static void main(String[] args)
    {
        M m = new M();
         
        m.method(new A());
         
        m.method(new B());
         
        m.method(new C());
    }
}
```
> 2  
> 3  
> 3  