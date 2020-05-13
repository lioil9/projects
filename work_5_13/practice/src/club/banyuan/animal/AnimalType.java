package club.banyuan.animal;

import java.lang.reflect.Field;

public class AnimalType {

  public static final AnimalType TIGER = new AnimalType("老虎", 1);
  public static final AnimalType LION = new AnimalType("狮子", 2);
  public static final AnimalType ELEPHANT = new AnimalType("大象", 3);

  private final String typeName;
  // TODO 对code进行初始化，1 表示老虎，2表示狮子，3表示大象
  private final int code;

  private AnimalType(String typeName, int code) {
    this.typeName = typeName;
    this.code = code;
  }

  public String getTypeName() {
    return typeName;
  }

  public int getCode() {
    return code;
  }

  // TODO
  public static AnimalType valueOf(int code){
    switch (code) {
      case 1:
        return TIGER;
      case 2:
        return LION;
      case 3:
        return ELEPHANT;
      default:
        return null;
    }
  }

  // TODO
  public static AnimalType valueOf(String typeName) {
    switch (typeName){
      case "老虎":
        return TIGER;
      case "狮子":
        return LION;
      case "大象":
        return ELEPHANT;
      default:
        return null;
    }
//    if(LION.getTypeName().equals(typeName)){
//      return LION;
//    }else if(TIGER.getTypeName().equals(typeName)){
//      return TIGER;
//    }else if(ELEPHANT.getTypeName().equals(typeName)){
//      return ELEPHANT;
//    }
//    return null;
  }

  @Override
  public String toString() {
    return this.typeName;
  }
}
