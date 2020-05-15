#### 1.
```
class Main {
   public static void main(String args[]) {
      try {
         throw 10;
      }
      catch(int e) {
         System.out.println("Got the  Exception " + e);
      }
  }
}
```

> 编译报错，抛出的异常必须是Throwble类型或其子类的

#### 2.

```
class Test extends Exception { }
  
class Main {
   public static void main(String args[]) { 
      try {
         throw new Test();
      }
      catch(Test t) {
         System.out.println("Got the Test Exception");
      }
      finally {
         System.out.println("Inside finally block ");
      }
  }
}
```
> Got the Test Exception
>
> Inside finally block

#### 3.

```
class Main {
   public static void main(String args[]) {
      int x = 0;
      int y = 10;
      int z = y/x;
  }
}
```
> 编译报错，算术异常错误，`java.lang.ArithmeticException: / by zero` 

#### 4.

```
class Base extends Exception {}
class Derived extends Base  {}
 
public class Main {
  public static void main(String args[]) {
   // some other stuff
   try {
       // Some monitored code
       throw new Derived();
    }
    catch(Base b)     { 
       System.out.println("Caught base class exception"); 
    }
    //Derived异常类是Base异常类的子类，所以在try里面抛出的Derived类异常会被父类Base类接
    //收，而后面捕获Derived类异常的catch语句是不会被执行的，应当将捕获范围较小的异常类放在前面
    catch(Derived d)  { 
       System.out.println("Caught derived class exception"); 
    }
  }
} 
```
> Error , Derived异常一已经被捕获

#### 5.

```
class Test
{
    public static void main (String[] args)
    {
        try
        {
            int a = 0;
            System.out.println ("a = " + a);
            int b = 20 / a;		//执行到这里抛出算术异常
            System.out.println ("b = " + b);
        }
 				//捕获算数异常，打印内容
        catch(ArithmeticException e)
        {
            System.out.println ("Divide by zero error");
        }
 				//最终执行打印内容
        finally
        {
            System.out.println ("inside the finally block");
        }
    }
}
```
> a = 0
>
> Divide by zero erro  
>
> inside the finally block

#### 6.

```
class Test
{
    public static void main(String[] args)
    {
        try
        {
            int a[]= {1, 2, 3, 4};
            for (int i = 1; i <= 4; i++)
            {
                System.out.println ("a[" + i + "]=" + a[i] + "n");
            }
        }
         
        catch (Exception e)
        {
            System.out.println ("error = " + e);
        }
         //ArrayIndexOutOfBoundsException异常为Exception的子类应当放在其前面
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println ("ArrayIndexOutOfBoundsException");
        }
    }
}
```
> Error,已经捕获到异常错误

#### 7.

```
class Test
{
    String str = "a";
 
    void A()
    {
        try
        {
            str +="b";	//1. ab
            B();
        }
        catch (Exception e)
        {
            str += "c"; //4. abdec
        }
    }
 
    void B() throws Exception
    {
        try
        {
            str += "d";  //2. abd
            C();
        }
        catch(Exception e)
        {
            throw new Exception();
        }
        finally
        {
            str += "e"; //3. abde
        }
 
        str += "f";
 
    }
     
    void C() throws Exception
    {
        throw new Exception();
    }
 
    void display()
    {
        System.out.println(str);
    }
 
    public static void main(String[] args)
    {
        Test object = new Test();
        object.A();
        object.display();
    }
 
}

```
> abdec

#### 8.

```
class Test
{   int count = 0;
 
    void A() throws Exception
    {
        try
        {
            count++;	// 1
             
            try
            {
                count++; // 2
 
                try
                {
                    count++;	// 3
                    throw new Exception();
 
                }
                 
                catch(Exception ex)
                {
                    count++;	// 4
                    throw new Exception();
                }
            }
             
            catch(Exception ex)
            {
                count++;	//5
            }
        }
         //没有抛出异常，所以不会被执行
        catch(Exception ex) 
        {
            count++;
        }
 
    }
 
    void display()
    {
        System.out.println(count);
    }
 
    public static void main(String[] args) throws Exception
    {
        Test obj = new Test();
        obj.A();
        obj.display();
    }
}
```

> 5

#### 9.方法返回值是

```
public int myMethod(){

  try {

  return 1;

  }

  catch (Exception e){

  return 2;

  }
	//finally有return语句，try中的会被屏蔽
  finally{

  return 3;

 }

}
```
> 3 

#### 10.

```
 try {

      File file = new File("filename.txt");

      Scanner sc = new Scanner(file);		//抛出文件找不到异常

      throw new IOException();
    }
		//是IOException子类，接收异常，打印内容
    catch (FileNotFoundException e) {

      System.out.println("FileNotFoundException called!!!");

    }
    catch (IOException e) {

      System.out.println("IOException called!!!");

    } 

```
> FileNotFoundException called!!!

#### 11.

```
try {

      File file = new File("filename.txt");

      Scanner sc = new Scanner(file); //抛出异常

      throw new IOException();
    }
		//是FileNotFoundException父类，能够捕获此异常
    catch (IOException e) {

      System.out.println("IOException called!!!");

    } 
```
> IOException called!!!  

12.

```
public class Test {
 
    private static String result = "";
 
    public static void main(String[] args) {
        test(1);
        result += "*"; // 1245*
        test(0);
        System.out.println(result);
    }
 
    public static void test(int i) {
 
        result += "1"; //1. 1  // 2. 1245*1
        try {
            if (i == 0) {
                throw new RuntimeException("");
            }
            result += "2"; //1. 12
        } catch (Exception e) {
            result += "3";  //2. 1245*13
            return;
        } finally {
            result += "4"; //1. 124  //2. 1245*134
        }
        result += "5";  // 1. 1245
    }
}
```

> 1245*134