package club.banyuan.menu;

import club.banyuan.machine.Machine;
import club.banyuan.machine.Product;

import java.util.Scanner;

/**
 * 服务菜单
 */
public class ServiceMenu {

  public static final String[] SERVICE_MENU = {"1. Inspect machine status\n",
      "2. Withdraw all money\n",
      "3. Refill product\n",
      "4. Change product"};
  private static String code;

  /**
   * 初始化密码
   */
  public static void initDefaultCode() {
    setCode("1110");
  }

  private static void setCode(String code) {
    ServiceMenu.code = code;
  }

  /**
   * 返回密码是否正确
   *
   * @param code 输入密码
   * @return 布尔值密码是否正确
   */
  public static boolean isCode(String code) {
    return ServiceMenu.code.equals(code);
  }

  /**
   * 提取机器余额
   *
   * @param machine 贩卖机对象
   */
  public static void withdrawnMenu(Machine machine) {
    System.out.println("\n(9-2) All money is being withdrawn.");
    System.out.println("$" + machine.withdrawMoney() + " is withdrawn.");
  }

  /**
   * 给机器中商品补货
   *
   * @param machine 贩卖机对象
   * @return
   */
  public static boolean refillProductMenu(Machine machine) {
    System.out.println("\n(9-3) Which product would you like to refill?");
    for (int i = 1; i <= machine.getLength(); i++) {
      System.out.println(i + "." + machine.selectProduct(i).getId());
    }
    try {
      int choice = ShowMenu.backAndChoice(machine.getLength());
      if (choice == 0) {
        System.out.println("Going back!");
      } else {
        Product obj = machine.selectProduct(choice);
        obj.refill();
        System.out.println("You have refilled product " + obj.getId() + " to full.");
      }
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  /**
   * 改变商品的信息
   *
   * @param machine 贩卖机对象
   * @return 返回是否结束当前菜单
   */
  public static boolean changeProductMenu(Machine machine) {
    System.out.println("\n(9-4) Which product would you like to change?");
    for (int i = 1; i <= machine.getLength(); i++) {
      System.out.println(i + "." + machine.selectProduct(i).getId());
    }
    try {
      int choice = ShowMenu.backAndChoice(machine.getLength());
      if (choice == 0) {
        System.out.println("Going back!");
      } else {
        Product obj = machine.selectProduct(choice);
        Scanner sc = new Scanner(System.in);
        try {
          System.out.print("Enter new product name:");
          obj.setName(sc.nextLine());
          System.out.print("Enter new product price:");
          obj.setPrice(sc.nextInt());
        } catch (Exception e) {
          System.out.println("Invalid input!");
          return false;
        }
        obj.refill();
        System.out.println("The new product " + obj.getId() + " has been filled to full.");
      }
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

}
