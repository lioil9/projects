public enum Enum {
  One("one", 1), Two("two",2);

  Enum(String name, int num) {
  }

  public static void main(String[] args) {
    System.out.println(Enum.valueOf("One").name());
  }

}
