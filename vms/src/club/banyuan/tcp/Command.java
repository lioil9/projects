package club.banyuan.tcp;

/**
 * 发送和接收指令的枚举类
 */

public enum Command {
  UPDATE("0"), PURCHASE("1"), PASSWORD("2"), REFILL("3"), CHANGE("4");

  String command;

  Command(String s) {
    command = s;
  }

}
