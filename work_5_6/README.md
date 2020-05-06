## 练习
### 以下程序的输出结果是
#### 1.
```java
class Base {
    final public void show() {
       System.out.println("Base::show() called");
    }
}
  
class Derived extends Base {
    public void show() {
       System.out.println("Derived::show() called");
    }
}
//父类中的final方法不能够被重写
class Main {
    public static void main(String[] args) {
        Base b = new Derived();;
        b.show();
    }
}
```

> 输出结果：编译报错

#### 2.

```java
class Base {
    public static void show() {
       System.out.println("Base::show() called");
    }
}
  
class Derived extends Base {
    public static void show() {
       System.out.println("Derived::show() called");
    }
}
//父类中静态方法无法被重写，所以向上转型之后，只能调用父类原有的静态方法  
class Main {
    public static void main(String[] args) {
        Base b = new Derived();;
        b.show();
    }
}
```
> 输出结果：Base::show() called 

#### 3.
```java
class Base {
    public void Print() {
        System.out.println("Base");
    }         
}
 
class Derived extends Base {    
    public void Print() {
        System.out.println("Derived");
    }
}
//x创建的是Base的对象调用其父类方法，而y和z向上转型，可以调用子类中重写父类的方法
class Main{
    public static void DoPrint( Base o ) {
        o.Print();   
    }
    public static void main(String[] args) {
        Base x = new Base();
        Base y = new Derived();
        Derived z = new Derived();
        DoPrint(x);
        DoPrint(y);
        DoPrint(z);
    }
}
```
> 输出结果： 
>
>  Base
>
> Derived
>
> Derived

#### 4.

```java
class Base {
    public void foo() { System.out.println("Base"); }
}
  
class Derived extends Base {
    private void foo() { System.out.println("Derived"); } 
}
//子类重写父类方法的访问修饰符限制必须比父类的更宽松
public class Main {
    public static void main(String args[]) {
        Base b = new Derived();
        b.foo();
    }
} 
```
> 输出结果：编译报错  

#### 5.
```java

public class NewClass { 
	public static class superclass { 
		static void print() 
		{ 
			System.out.println("print in superclass."); 
		} 
	} 
	public static class subclass extends superclass { 
		static void print() 
		{ 
			System.out.println("print in subclass."); 
		} 
	} 
	//父类中的静态方法没有被重写，向上转型之后，只能够调用父类中的静态方法
	public static void main(String[] args) 
	{ 
		superclass A = new superclass(); 
		superclass B = new subclass(); 
		A.print(); 
		B.print(); 
	} 
} 
```
> 输出结果：
>
> print in superclass.
>
> print in superclass.

#### 6.
```java

public class NewClass { 
	public static class superclass { 
		void print() 
		{ 
			System.out.println("print in superclass."); 
		} 
	} 

	public static class subclass extends superclass { 
		@Override
		void print() 
		{ 
			System.out.println("print in subclass."); 
		} 
	} 
	//子类重写了父类中的方法，指向父类的对象调用父类方法，而B向上转型之后可以调用子类重写的方法
	public static void main(String[] args) 
	{ 
		superclass A = new superclass(); 
		superclass B = new subclass(); 
		A.print(); 
		B.print(); 
	} 
} 
```
> 输出结果：
>
> print in superclass.
>
> print in subclass.

#### 7.
```java
class ClassOne
{ 
    protected void getData() 
    { 
        System.out.println("Inside ClassOne");
    } 
} 
class ClassTwo extends ClassOne
{ 
    protected void getData() 
    { 
        System.out.println("Inside ClassTwo");
    } 
} 
//子类重写父类方法，obj向上转型调用重写后的方法
public class Test 
{ 
    public static void main(String[] args) 
    { 
        ClassOne obj = new ClassTwo();
        obj.getData(); 
    } 
} 
```
> 输出结果：Inside ClassTwo

#### 8.
```java
class Test 
{ 
    void myMethod() 
    { 
        System.out.println("Test");
    } 
} 
class Derived extends Test
{ 
    void myMethod() 
    { 
        System.out.println("Derived");
    } 
    //父类对象无法转换成子类引用
    public static void main(String[] args) 
    { 
        Derived object = new Test(); 
        object.myMethod(); 
    } 
} 
```
> 输出结果：编译报错

#### 9.
```java
class ClassOne
{ 
    protected void getData() 
    { 
        System.out.println("Inside ClassOne");
    } 
} 
class ClassTwo extends ClassOne
{ 
    protected void getData() 
    { 
        System.out.println("Inside ClassTwo");
    } 
      
    protected void getValue() 
    { 
        System.out.println("ClassTwo");
    } 
} 
//obj向上转型只能够调用子类中重写父类的方法以及父类派生的方法，调用子类独有的方法需要向下转型
public class Test 
{ 
    public static void main(String[] args) 
    { 
        ClassOne obj = new ClassTwo();
        obj.getValue(); 
    } 
} 
```

> 输出结果：编译报错