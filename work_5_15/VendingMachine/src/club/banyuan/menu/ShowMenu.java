package club.banyuan.menu;

import club.banyuan.exception.BuyException;
import club.banyuan.exception.ChoiceException;
import club.banyuan.machine.Coin;
import club.banyuan.machine.Machine;

import java.util.Scanner;

/**
 * 主菜单的子菜单展示页面
 */
public class ShowMenu {

  /**
   * 展示商品信息菜单
   *
   * @param machine 贩卖机对象
   */
  public static void displayProductMenu(Machine machine) {
    System.out.println("\n(1) The displayed products are:");
    machine.displayProduct();
  }

  /**
   * 投币菜单
   *
   * @param machine 贩卖机对象
   * @return 返回布尔值，判读是否结束投币
   */
  public static boolean insertCoinMenu(Machine machine) {
    System.out.println("\n(2) Which coin would you like to insert?");
    for (Coin coin : Coin.values()) {
      System.out.println(coin.ordinal() + 1 + ". $" + coin.getPrice());
    }
    try {
      int choice = backAndChoice(Coin.values().length);
      if (choice == 0) {
        System.out.println("Going back!");
        return true;
      } else {
        for (Coin coin : Coin.values()) {
          if (coin.ordinal() == choice - 1) {
            machine.insertCoin(coin);
            System.out.println("You have inserted $" + coin.getPrice());
          }
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println();
    graphicMachine(machine);
    return false;
  }

  /**
   * 购买商品菜单
   *
   * @param machine 贩卖机对象
   * @return 返回是否结束购买
   */
  public static boolean buyProductMenu(Machine machine) {
    boolean flag = false;
    System.out.println("\n(3) Which product button would you like to press?");
    for (int i = 1; i <= machine.getLength(); i++) {
      System.out.println(i + "." + machine.selectProduct(i).getId());
    }
    try {
      int choice = backAndChoice(machine.getLength());
      if (choice == 0) {
        System.out.println("Going back!");
        return true;
      } else {
        flag = true;
        System.out.println("You have pressed button " + machine.selectProduct(choice).getId());
        machine.setBeBuyingProduct(machine.selectProduct(choice).getId());
        machine.buyProduct(machine.selectProduct(choice));
      }
    } catch (BuyException e) {
      flag = false;
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return flag;
  }

  /**
   * 退回余额菜单
   *
   * @param machine 贩卖机对象
   */
  public static void returnMenu(Machine machine) {
    System.out.println("\n(4) Return button is pressed");
    System.out.println("$" + machine.returnLefCoin() + " has been returned");
  }

  /**
   * 显示服务菜单
   *
   * @param machine 贩卖机对象
   */
  public static void showServiceMenu(Machine machine) {
    while (true) {
      System.out.println("\n(9) What would you like to do?");
      for (String serviceMenu : ServiceMenu.SERVICE_MENU) {
        System.out.println(serviceMenu);
      }
      try {
        int choice = backAndChoice(ServiceMenu.SERVICE_MENU.length);
        switch (choice) {
          case 1:
            System.out.println("\n(9-1) Machine status");
            machine.machineStatus();
            break;
          case 2:
            ServiceMenu.withdrawnMenu(machine);
            break;
          case 3:
            while (true) {
              if (ServiceMenu.refillProductMenu(machine)) {
                break;
              }
            }
            break;
          case 4:
            while (true) {
              if (ServiceMenu.changeProductMenu(machine)) {
                break;
              }
            }
            break;
          case 0:
            System.out.println("Going back!");
            return;
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * 返回和选择菜单
   *
   * @param length 选择菜单的长度
   * @return 返回选择的选项
   * @throws Exception 抛出选择和非法输入的异常
   */
  public static int backAndChoice(int length) throws Exception {
    System.out.println("0. Go back");
    System.out.println();
    Scanner sc = new Scanner(System.in);
    System.out.print("Your choice:");
    try {
      int choice = sc.nextInt();
      if (choice >= 0 && choice <= length) {
        return choice;
      } else {
        throw new ChoiceException("Invalid choice!");
      }
    } catch (ChoiceException e) {
      throw e;
    } catch (Exception e) {
      throw new Exception("Invalid input!");
    }
  }

  /**
   * 显示可视化的售货机
   *
   * @param machine 贩卖机对象
   */
  public static void graphicMachine(Machine machine) {
    System.out.println("*-----------------------------*");
    System.out.println("|      Vending   Machine      |");
    System.out.println("*-----------------------------*");
    System.out.print("|");
    for (int i = 1; i <= machine.getLength(); i++) {
      System.out.printf("%5s", machine.selectProduct(i).getId());
    }
    System.out.println("    |");
    //显示价格
    System.out.print("| ");
    for (int i = 1; i <= machine.getLength(); i++) {
      System.out.printf("  $%2d", machine.selectProduct(i).getPrice());
    }
    System.out.println("   |");
    //显示可否被购买
    System.out.print("| ");
    for (int i = 1; i <= machine.getLength(); i++) {
      machine.productStatus();
      System.out.print(machine.selectProduct(i).isEmpty() ? "  [X]"
          : (machine.selectProduct(i).isStatus() ? "  [O]" : "  [ ]"));
    }
    System.out.println("   |");
    System.out.println("*-----------------------------*");
    //显示投币余额
    System.out.printf("|                     [$%2d]   |\n", machine.getLeftCoin());
    System.out.println("|                             |");
    //显示当前购买的商品
    System.out.printf("|            [=%s=]            |\n", machine.getBeBuyingProduct());
    System.out.println("*-----------------------------*");
    System.out.println();
  }


}
