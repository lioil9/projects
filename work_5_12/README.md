### 写出以下程序的输出结果

#### 1. 以下代码能否通过编译，为什么
```java
class Outer{
 public static void main(String[] args){
  new Inner(); 
 } 
 class Inner { 
  Inner() {} 
 }  
}
//不能通过编译，内部类必须先通过新建外部类对象来新建内部类对象
```
#### 2. 以下代码能否通过编译，为什么
```
static class Outer{
 public static void main(String[] args){
 //
 }
 private class Inner{
  Inner() {} 
 }
}
//不能通过编译，外部类不能是静态类
```

#### 3.
```java
class Outer {
 String s1 = "Java";
 String s2 = "2";

 public static void main(String[] args) { 
  Outer outer = new Outer();  
 }
 Outer() { 
  Inner inner = new Inner();  
 }
 class Inner {
  String s1 = "Certification";
  String s2 = "Exam";
        
  Inner() {
   System.out.println(Outer.this.s1);
   System.out.println(this.s1);
   System.out.println(s2);
  }
 }
}
//在main方法中新建一个外部类对象，而在外部类构造方法中新建了一个内部类对象，内部类构造方法中输出Outer的s1成员变量，输出内部类的s1变量，再输出内部类的s2变量
```
> 输出结果：Java
>
> Certification
>
> Exam

#### 4. 以下代码能否通过编译，为什么

```java
class A
{
    class B
    {
        static void methodB()
        {
            System.out.println("Method B");
        }
    }
}
//不能通过编译，成员内部类中不能够定义静态成员和方法
```
#### 5.
```java
class OuterInnerStatic {
 static String s1 = "Java"; 
 static String s2 = "2";
 public static void main(String[] args) { 
  Inner inner = new Inner();  
}

 static class Inner {
  String s1 = "Certification";
  String s2 = "Exam";
  Inner() {
   System.out.println(OuterInnerStatic.s1);
   System.out.println(this.s1);
   System.out.println(s2);
  }
 }
}
//直接在外部类main方法中新建内部静态类对象，然后在构造方法中输出外部类的s1成员，静态内部类的s1和s2
```
> 输出结果：Java  
>
> Certification
>
> Exam

#### 6.

```java
class X
{
    static int x = 3131;
     
    static class Y
    {
        static int y = x++;
         
        static class Z
        {
            static int z = y++;
        }
    }
}
 
public class MainClass
{
    public static void main(String[] args)
    {
        System.out.println(X.x);
         
        System.out.println(X.Y.y);
         
        System.out.println(X.Y.Z.z);
    }
}
//调用输出外部类的静态成员变量，然后输出静态内部类的静态成员，外部类的静态成员变量先赋值再自增，
```

> 输出结果：3131  
>
> 3131  
>
> 3131

#### 7. 在下面的示例中，如何访问"XYZ"类的"i"字段？

```java
class ABC
{
    class XYZ
    {
        int i = 111;
    }
}
```

> 在外部类ABC中可以通过新建XYZ内部类对象来访问， new XYZ().i ;

#### 8. 运行以下程序时，它是否在控制台上打印“SUCCESS”？

```
class A
{
    {
        new B();
    }
     
    static class B
    {
        {
            new A().new C();
        }
    }
     
    class C
    {
        {
            System.out.println("SUCCESS");
        }
    }
}
 
public class MainClass
{
    public static void main(String[] args)
    {
        new A();
    }
}
//在新建外部类对象A时，会新建一个内部类对象B，而在静态内部类中代码块会新建内部类对象C，但是要新建内部类C的对象要先创建A类对象，所以A类和内部类B会循环调用生成对象，溢出
```

> 可以通过编译，但是不能够打印输出 "SUCCESS"

#### 9. 以下代码能否通过编译，为什么

```
class A
{
    String s = "AAA";
     
    void methodA()
    {
        System.out.println(s);
    }
     
    static class B
    {
        void methodB()
        {
            methodA();
        }
    }
}
//不能通过编译，在静态内部类中调用外部类的普通方法，需要新建对象才能够进行调用
```



#### 10.

```java
abstract class A
{
    {
        System.out.println(1);
    }
     
    static
    {
        System.out.println(2);
    }
}
 
public class MainClass
{
    public static void main(String[] args)
    {
        A a = new A() { };
    }
}
//新建A对象，首先调用静态代码块，然后再调用构造代码块
```
> 输出结果：2
>
> 1  

#### 11.

```java
class A
{
    static String s = "AAA";
     
    class B
    {
        String s = "BBB";
         
        void methodB()
        {
            System.out.println(s);
        }
    }
}
 
public class MainClass
{
    public static void main(String[] args)
    {
        A a = new A();
         
        System.out.println(a.s);
         
        A.B b = a.new B();
         
        System.out.println(b.s);
         
        b.methodB();
    }
}
//新建一个A类对象然后输出A的静态成员，新建内部类对象再输出内部类的成员，然后用方法输出
```

> 输出结果：AAA
>
> BBB
>
> BBB

#### 12. 以下代码能否通过编译，为什么

```java
class A
{
    void methodOne()
    {
        class B
        {
            void methodTwo()
            {
                System.out.println("Method Two");
            }
        }
    }
     
    void methodThree()
    {
        new B().methodTwo();
    }
}
//不能通过编译，方法内部类只能够在该方法中使用，所以无法在其他方法中调用
```

#### 13
```
class X
{
    {
        System.out.println(1);
    }
     
    static
    {
        System.out.println(2);
    }
     
    public X()
    {
        new Y();
    }
     
    static class Y
    {
        {
            System.out.println(3);
        }
         
        static
        {
            System.out.println(4);
        }
    }
}
 
public class MainClass
{
    public static void main(String[] args)
    {
        X x = new X();
         
        X.Y y = new X.Y();
    }
}
//先新建外部类X类对象，依次调用类中的静态代码块，构造代码块，构造方法，然后新建内部类Y对象，调用
//其静态代码块，构造代码块，再新建内部类Y对象时，只调用其构造代码块
```

> 输出结果：2
>
> 1
>
> 4
>
> 3
>
> 3

#### 14. 以下代码能否通过编译，为什么

```
class A
{
    class B
    {
        {
            System.out.println(1);
        }
         
        static
        {
            System.out.println(2);
        }
    }
}
//不能通过编译，因为非静态内部类中依靠外部类进行实例化，所以其里面不能够有静态成员，所以静态代码块也不能有
```

#### 15.
```
class ABC
{
    int i = 10101;
     
    {
        i--;
    }
     
    public ABC()
    {
        --i;
    }
     
    class XYZ
    {
        int i = this.i;
         
        {
            i++;
        }
         
        public XYZ() 
        {
            ++i;
        }
    }
}
 
public class MainClass
{
    public static void main(String[] args)
    {
        ABC abc = new ABC();
         
        System.out.println(abc.i);
         
        ABC.XYZ xyz = abc.new XYZ();
         
        System.out.println(xyz.i);
         
        ABC.XYZ xyz1 = new ABC().new XYZ();
         
        System.out.println(xyz1.i);
    }
}
//先新建外部类对象，然后对i自减两次，得到10099，在内部类中对i赋值时，只用this时指代的是内部类
//对象，所以i初值为0，则自增两次后为2
```
> 输出结果：10099
>
> 2
>
> 2

#### 16.

```
class P
{
    String s = "PPP";
     
    {
        System.out.println(s);
    }
     
    String methodP()
    {
        class Q
        {
            String s = P.this.s+"QQQ";
             
            {
                System.out.println(s);
            }
        }
         
        return new Q().s+s;
    }
}
 
public class MainClass
{
    public static void main(String[] args)
    {
        P p = new P();
         
        System.out.println(p.methodP());
    }
}
//新建外部类P对象,打印输出s字符串，再调用methodP方法时，最终返回新建一个内部类Q时会输出拼接后
//的字符串PPPQQQ，最后返回再次拼接的字符串PPPQQQPPP输出
```

> 输出结果：PPP
>
> PPPQQQ
>
> PPPQQQPPP

#### 17. 

```
class A
{
    void methodA1(int i)
    {
        System.out.println(i+++i);
    }
     
    void methodA2(int i)
    {
        System.out.println(--i-i--);
    }
}
 
class B
{
    A a = new A()
    {
        void methodA1(int i)
        {
            System.out.println(++i+i++);
        }
         
        void methodA2(int i)
        {
            System.out.println(i---i);
        }
    };
}
 
public class MainClass
{
    public static void main(String[] args)
    {
        A a = new A();
         
        a.methodA1(10);
         
        a.methodA2(10);
         
        B b = new B();
         
        b.a.methodA1(10);
         
        b.a.methodA2(10);
    }
}

```

> > 输出结果：21
> >
> > 0
> >
> > 22
> >
> > 1  

#### 18. 以下代码能否通过编译，为什么

```
class One
{
    void methodOne()
    {
        public class Two
        {
             
        }
    }
}
//无法通过编译，因为局部内部类是不能够有访问修饰符的
```

#### 19.

```
class One
{
    {
        System.out.println("ONE");
    }
     
    class Two
    {
        {
            System.out.println("TWO");
        }
    }
     
    static
    {
        System.out.println("THREE");
    }
     
    static class Three
    {
        {
            System.out.println("FOUR");
        }
         
        static
        {
            System.out.println("FIVE");
        }
    }
}
 
public class MainClass
{
    public static void main(String[] args)
    {
        One one = new One();
         
        One.Two two = one.new Two();
         
        One.Three three = new One.Three();
    }
}
```

> 输出结果：THREE
>
> ONE
>
> TWO
>
> FIVE
>
> FOUR  

#### 20. 以下代码能否通过编译，为什么

```
class A
{
    class B extends A
    {
        class C extends B
        {
            class D extends C
            {
                 
            }
        }
    }
}
//能通过编译，内部类中也可以实现继承
```

#### 21.
```
abstract class X
{
    static String s1 = "STATIC";
     
    String s2 = "NON-STATIC";
     
    abstract void methodX(); 
     
    static abstract class Y
    {
        String s1 = "NON-STATIC";
         
        static String s2 = "STATIC";
         
        abstract void methodY();
    }
}
 
public class MainClass
{
    public static void main(String[] args)
    {
        new X() 
        {   
            void methodX()
            {
                System.out.println(s1+" "+s2);
            }
        }.methodX();
         
        new X.Y() 
        {   
            void methodY() 
            {
                System.out.println(s1+" "+s2);
            }
        }.methodY();
    }
}
```

> 输出结果：STATIC NON-STATIC
>
> NON-STATIC   STATIC

#### 22. 在下面的示例中，如何访问“内部类”的“i”字段？

```
class OuterClass
{
    static class InnerClass
    {
        int i;
    }
}
```

> 可以直接新建内部类对象进行调用，new InnerClass().i

#### 23.

```
class X
{   
    static
    {
        class Y
        {   
            {
                System.out.println(1);
            }
        }
    }
     
    {
        class Y
        {
            {
                System.out.println(2);
            }
        }
    }
}
 
public class MainClass
{
    public static void main(String[] args)
    {
        new X();
    }
}
```

> 无任何输出，这里只是新建了外部类X对象，但是并没有在静态代码块和构造代码块中调用局部内部类对象，所以里面的语句不会执行。

#### 24.

```
class A
{   
    abstract class B
    {
        abstract void method();
    }
     
    {
        new B()
        {
             
            @Override
            void method()
            {
                System.out.println("BBB");
            }
        }.method();
    }
}
 
public class MainClass
{
    public static void main(String[] args)
    {
        new A();
    }
}
//这里在A的构造代码块中使用匿名内部类实现了抽象B类，重写了B的抽象方法并调用打印BBB
```

> 输出结果：BBB

#### 25.

```
class X
{   
    void methodX()
    {
        class Y
        {
            static void methodY()
            {
                 
            }
        }
    }
}
//非静态内部类中不能有静态成员，所以静态方法不能够放在Class Y里
```

> 编译报错

#### 26. 如何在class A以外的其他类中实例化 class B

```
class A
{
    void methodA()
    {
        class B
        {
             
        }
    }
}
```

> 无法实例化Class B，因为局部内部类创建内部类的实例只能够在当前methodA方法中，外部类中无法创建其实例

#### 27.

```
public class Outer 
{ 
	public static int temp1 = 1; 
	private static int temp2 = 2; 
	public int temp3 = 3; 
	private int temp4 = 4; 
	
	public static class Inner 
	{ 
		private static int temp5 = 5; 
		
		private static int getSum() 
		{ 
			return (temp1 + temp2 + temp3 + temp4 + temp5); 
		} 
	} 
	
	public static void main(String[] args) 
	{ 
		Outer.Inner obj = new Outer.Inner(); 
		System.out.println(obj.getSum()); 
	} 
	
} 

```

> 编译报错，因为temp3和temp4是非静态成员变量，无法在静态方法getSum中使用非静态成员变量

#### 28.

```
public class Outer  
{ 
    private static int data = 10; 
    private static int LocalClass() 
    { 
        class Inner 
        { 
            public int data = 20; 
            private int getData() 
            { 
                return data; 
            } 
        }; 
        Inner inner = new Inner(); 
        return inner.getData(); 
    } 
      
    public static void main(String[] args) 
    { 
        System.out.println(data * LocalClass()); 
    } 
} 
//在LocalClass方法中定义一个局部内部类，并新建他然后返回其值，最后将外部类的静态变量与外部类静
//态方法得到的值相乘
```

> 输出结果：200

#### 29.

```
interface Anonymous 
{ 
	public int getValue(); 
} 
public class Outer 
{ 
	private int data = 15; 
	public static void main(String[] args) 
	{ 
		Anonymous inner = new Anonymous() 
				{ 
					int data = 5; 
					public int getValue() 
					{ 
						return data; 
					} 
					public int getData() 
					{ 
						return data; 
					} 
				}; 
		Outer outer = new Outer(); 
		System.out.println(inner.getValue() + inner.getData() + outer.data); 
	} 
} 

```

> 编译报错，匿名内部类中实现了接口，但是inner是接口的数据类型只能够调用实现类对接口重写的方法，不能调用实现类独有的方法。

#### 30.

```java
public class Outer 
{ 
    private int data = 10; 
      
    class Inner 
    { 
        private int data = 20; 
        private int getData() 
        { 
            return data; 
        } 
        public void main(String[] args) 
        { 
            Inner inner = new Inner(); 
            System.out.println(inner.getData()); 
              
        } 
    } 
    private int getData() 
    { 
        return data; 
    } 
    public static void main(String[] args) 
    { 
        Outer outer = new Outer(); 
        Outer.Inner inner = outer.new Inner(); 
        System.out.printf("%d", outer.getData()); 
        inner.main(args); 
    } 
} 
//新建外部类对象，再新建内部类对象，调用输出外部类的data 10，然后再调用内部类的main方法，新建
//内部类对象输出，内部类的data
```

> 1020

#### 31.

```
interface OuterInterface 
{ 
    public void InnerMethod(); 
    public interface InnerInterface 
    { 
        public void InnerMethod(); 
    } 
} 
public class Outer implements OuterInterface.InnerInterface, OuterInterface 
{ 
    public void InnerMethod() 
    { 
        System.out.println(100); 
    } 
      
      
    public static void main(String[] args) 
    { 
        Outer obj = new Outer(); 
        obj.InnerMethod(); 
    } 
} 
// 定义一个接口，有一个抽象方法InnerMethod，并且在内部定义一个内部接口，里面有一个同名方法，
// 用实例类来实现这两个接口，重写这两个同名方法，然后新建实例类对象，调用方法会调用实现类重写的方法
```

> 输出结果：100

