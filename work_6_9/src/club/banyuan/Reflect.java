package club.banyuan;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Reflect {


  public static void main(String[] args) {
    printInfo(0);
  }

  public static void printInfo(Object object) {

    Class objectClass = object.getClass();
    Field[] fields = objectClass.getDeclaredFields();
    Method[] methods = objectClass.getDeclaredMethods();

    List<Field> fieldList = new ArrayList<>(Arrays.asList(fields));
    List<Method> methodList = new ArrayList<>(Arrays.asList(methods));
    fieldList.sort(Comparator.comparing(Field::getName));
    methodList.sort(Comparator.comparing(Method::getName));

    methodList.forEach(s -> System.out.println(s.toString()));
    fieldList.forEach(s -> System.out.println(s.toString()));


  }
}
