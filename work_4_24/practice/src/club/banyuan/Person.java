package club.banyuan;

public class Person {

  private String name;
  private int age;
  public static int count1 = 0, count2 = 0;
  
  {
    count1++;
  }

  public Person(final String name, final int age) {
    this.name = name;
    this.age = age;
    count2++;
  }

  public Person(final String name) {
    this.name = name;
  }

  public Person() {
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(final int age) {
    this.age = age;
  }

}