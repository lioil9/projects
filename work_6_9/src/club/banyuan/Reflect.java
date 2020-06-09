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
    List classList = new ArrayList();

    Class objectClass = object.getClass();
    Field[] fields = objectClass.getDeclaredFields();
    Method[] methods = objectClass.getDeclaredMethods();
    List<Field> fieldList = new ArrayList<>(Arrays.asList(fields));
    List<Method> methodList = new ArrayList<>(Arrays.asList(methods));
    fieldList.sort(Comparator.comparing(Field::getName));
    methodList.sort(Comparator.comparing(Method::getName));

    classList.addAll(Arrays.asList(fields));
    classList.addAll(Arrays.asList(methods));

    classList.sort((o1, o2) -> {
      if (o1 instanceof Field && o2 instanceof Field) {
        return ((Field) o1).getName().compareTo(((Field) o2).getName());
      } else if (o1 instanceof Field && o2 instanceof Method) {
        return ((Field) o1).getName().compareTo(((Method) o2).getName());
      } else if (o1 instanceof Method && o2 instanceof Field) {
        return ((Method) o1).getName().compareTo(((Field) o2).getName());
      } else if (o1 instanceof Method && o2 instanceof Method) {
        return ((Method) o1).getName().compareTo(((Method) o2).getName());
      }
      return 0;
    });


    classList.forEach(s -> System.out.println(s.toString()));
    System.out.println(classList.size());

    methodList.forEach(s -> System.out.println(s.toString()));
    fieldList.forEach(s -> System.out.println(s.toString()));


  }
}
