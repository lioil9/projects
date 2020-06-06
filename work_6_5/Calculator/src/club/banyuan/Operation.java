package club.banyuan;

public enum Operation {
  ADD("+"), SUB("-"), MUL("*"), DIV("/"), MOD("%");

  private final String name;

  Operation(String name) {
    this.name = name;
  }

  public static Operation getOperation(String name){
    for (Operation value : Operation.values()) {
      if(value.name.equals(name)){
        return value;
      }
    }
    return null;
  }
}
