## 练习

1. 
```java
class Clidder  
{ 
    private final void flipper()  
    { 
        System.out.println("Clidder"); 
    } 
} 
  
public class Clidlet extends Clidder  
{ 
//父类的中的fipper方法被private修饰不能被继承，这里子类定义了一个flipper方法，所以直接调用的是子类的方法
    public final void flipper()  
    { 
        System.out.println("Clidlet"); 
    } 
    public static void main(String[] args)  
    { 
        new Clidlet().flipper(); 
    } 
} 
```

> 输出结果： Clidlet

2. 
```java
class Alpha  
{ 
    static String s = " "; 
    protected Alpha()  
    { 
        s += "alpha "; 
    } 
} 
class SubAlpha extends Alpha  
{ 
    private SubAlpha()  
    { 
        s += "sub "; 
    } 
} 
/**
* SubSubAlpha类继承Alpha类，在新建SubSubAlpha对象时先构造Alpha，静态变量值为 " alpha ",然后构造SubSubAlpha，s此时为" alpha subsub"
*/
public class SubSubAlpha extends Alpha  
{ 
    private SubSubAlpha()  
    { 
        s += "subsub "; 
    } 
    public static void main(String[] args)  
    { 
        new SubSubAlpha(); 
        System.out.println(s); 
    } 
}
```

> 输出结果： alpha subsub 

3. 
```java
class Grandparent  
{ 
    public void Print()  
    { 
        System.out.println("Grandparent's Print()");  
    }  
} 
  
class Parent extends Grandparent  
{ 
    public void Print()  
    { 
        System.out.println("Parent's Print()");  
    }  
} 
 //不能够使用super.super.来调用祖父类的方法，如果父类重写了祖父类的方法后，祖父类方法会被屏蔽，则子类调不到祖父类的方法
class Child extends Parent  
{ 
    public void Print()    
    { 
        super.super.Print(); 
        System.out.println("Child's Print()");  
    }  
} 
  
public class Main  
{ 
    public static void main(String[] args)  
    { 
        Child c = new Child(); 
        c.Print();  
    } 
} 
```

> 输出结果： 编译错误
>
> 4.   
```java
final class Complex {
 
    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }
// 重写了Object类的toString方法，将对象按照规定格式进行打印
    public String toString() {
        return "(" + re + " + " + im + "i)";
    }
}
 //新建对象c构造其re im变量初值为10 15;
class Main {
  public static void main(String args[])
  {
       Complex c = new Complex(10, 15);
       System.out.println("Complex number is " + c);
  }         
}
```
> 输出结果：Complex number is (10.0 + 15.0i)

5.   
```java
class A
{
    String s = "Class A";
}
 
class B extends A
{
    String s = "Class B";
 
    {
        System.out.println(super.s);
    }
}
 
class C extends B
{
    String s = "Class C";
 
    {
        System.out.println(super.s);
    }
}
/**
* 新建一个C类对象，会在之前构造A类和B类，然后执行B类中的代码块，打
* 印父类C的s值，执行C类中的代码块，打印父类B的s值，最后打印对象c的s值
*/
public class MainClass
{
    public static void main(String[] args)
    {
        C c = new C();
 
        System.out.println(c.s);
    }
}
```
> 输出结果：  
> Class A  
> Class B  
> Class C  

6. 

```java
class A
{
    static
    {
        System.out.println("THIRD");
    }
}
 
class B extends A
{
    static
    {
        System.out.println("SECOND");
    }
}
 
class C extends B
{
    static
    {
        System.out.println("FIRST");
    }
}
/**
* 新建C类对象c，此时会先加载祖父类A运行静态代码块内容，输出THIRD，
* 然后加载父类B输出 SECOND，最后加载C类，输出FIRST
*/
public class MainClass
{
    public static void main(String[] args)
    {
        C c = new C();
    }
}
```

> 输出结果：
>
> THIRD
> SECOND
> FIRST

7.  

```java
class X
{
    static void staticMethod()
    {
        System.out.println("Class X");
    }
}
 
class Y extends X
{
    static void staticMethod()
    {
        System.out.println("Class Y");
    }
}
//直接在Y类中用类方法，没有调用到父类，直接打印输出Class Y
public class MainClass
{
    public static void main(String[] args)
    {
        Y.staticMethod();
    }
}
```
> 输出结果：Class Y  
8. 
```java
class M
{
    static
    {
        System.out.println('A');
    }
 
    {
        System.out.println('B');
    }
 
    public M()
    {
        System.out.println('C');
    }
}
 
class N extends M
{
    static
    {
        System.out.println('D');
    }
 
    {
        System.out.println('E');
    }
 
    public N()
    {
        System.out.println('F');
    }
}
/**新建一个N类对象n，首先会加载父类然后在静态代码块中输出A，然后加载子类输出D
* 新建对象时则先加载父类的构造代码块输出B后输出C，再构造子类输出E F
*/
public class MainClass
{
    public static void main(String[] args)
    {
        N n = new N();
    }
}
```

> 输出结果：  
> A  
> D  
> B  
> C  
> E  
> F  
9. 
```java
class A
{
	static String s = "AAA";

	static
	{
		s = s + "BBB";
	}

	{
		s = "AAABBB";
	}
}

class B extends A
{
	static
	{
		s = s + "BBBAAA";
	}

	{
		System.out.println(s);
	}
}
/**
* 先运行父类A静态代码块，s为AAABBB，然后运行然后运行B中静态代码块s为AAABBBBBBAAA
* 最后重新将s赋值为AAABBB，输出s值
*/
public class MainClass
{
	public static void main(String[] args)
	{
		B b = new B();
	}
}
```
> 输出结果：AAABBB  

10. 
```java
class X
{
	int i = 101010;

	public X()
	{
		i = i++ + i-- - i;
	}

	static int staticMethod(int i)
	{
		return --i;
	}
}

class Y extends X
{
	public Y()
	{
		System.out.println(staticMethod(i));
	}
}
// 先运行父类X的构造方法内容，i的值为101011，然后运行Y类构造方法，打印输出staticMethod静态方法的返回值-1后为101010
public class MainClass
{
	public static void main(String[] args)
	{
		Y y = new Y();
	}
}
```
> 输出结果：101010    
11. 
```java
class ClassOne
{
     static int i, j = 191919;
 
     {
         --i;
     }
 
     {
         j++;
     }
}
 
public class ClassTwo extends ClassOne
{
    static
    {
        i++;
    }
 
    static
    {
        --j;
    }
/**
*在子类中直接打印，先调用加载子类，运行静态代码块的语句，
*而静态变量中i的值未显性赋值默认为0，+1后为1，j-1后为191918，打印输出
*/
    public static void main(String[] args)
    {
        System.out.println(i);
 
        System.out.println(j);
    }
}
```

> 输出结果：   
> 1  
> 191918  
12. 
```java
class A
{
	int[] a = new int[5];

	{
		a[0] = 10;
	}
}

public class MainClass extends A
{
	{
		a = new int[5];
	}

	{
		System.out.println(a[0]);
	}
/**
* 新建MainClass的对象，先运行父类构造代码块中的语句，a[0]=10,而在子类构造* 代码块中，数组a被重新赋值一个新的数组，值都为0，然后将a[0]打印输出为0
*/
	public static void main(String[] args)
	{
		MainClass main = new MainClass();
	}
}
```
> 输出结果：0   
13.
```java
class A
{
	static int i;

	static
	{
		i++;
	}

	{
		++i;
	}
}

class B extends A
{
	static
	{
		--i;
	}

	{
		i--;
	}
}
/**
* 新建B类对象，调用A类运行静态代码块i为1，再执行B类静态代码块i为0，
* 再运行A类构造代码块i为1，运行B类构造代码块i为0，最后打印输出i
*/
public class MainClass
{
	public static void main(String[] args)
	{
		System.out.println(new B().i);
	}
}
```
> 输出结果：0  
14. 
```java
public class MainClass
{
	public MainClass(int i, int j)
	{
		System.out.println(method(i, j));
	}
	
	int method(int i, int j)
	{
		return i++ + ++j;
	}
	//新建MainClass类对象并构造调用method方法，返回时 为10 + 21 ，打印结果31
	public static void main(String[] args) 
	{
		MainClass main = new MainClass(10, 20);
	}
}
```
> 输出结果：31  
15.  
```java
class X
{
	static
	{
		Y.methodOfY();
	}
}

class Y extends X
{
	static void methodOfY()
	{
		System.out.println("Hi....");
	}
}
// 这里直接调用类的静态方法，但是在调用加载Y类前会先加载父类X，然后调用Y类静态方法，打印Hi.... ，最后调用Y类静态方法打印Hi....
public class MainClass
{
	public static void main(String[] args)
	{
		Y.methodOfY();
	}
}
```
> 输出结果：  
> Hi....
> Hi....
16. 
```java
class ClassOne
{
	static int i = 111;
	
	int j = 222;
	
	{
		i = i++ - ++j;
	}
}

class ClassTwo extends ClassOne
{
	{
		j = i-- + --j;
	}
}
//首先运行ClassOne的构造代码块，得到i为-112，j为223 再运行ClassTwo中的构造代码块 j为110，i为-113
class Main {
	public static void main(String[] args) {
		ClassTwo two = new ClassTwo();
		System.out.println(two.j+" "+ ClassOne.i);
	}
}
```
>输出结果： 110 -113

>
>