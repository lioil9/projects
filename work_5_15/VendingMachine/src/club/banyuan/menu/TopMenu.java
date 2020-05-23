package club.banyuan.menu;

import club.banyuan.machine.Machine;

import java.util.Scanner;

public class TopMenu {

  private static final String[] TOP_MENU = {"1. Read product information\n",
      "2. Insert coin\n",
      "3. Press product button\n",
      "4. Press return button\n",
      "9. Open service menu (code required)\n",
      "0. Quit\n"};

  /**
   * 主菜单
   *
   * @param machine 贩卖机对象
   */
  public static void mainMenu(Machine machine) {
    while (true) {
      ShowMenu.graphicMachine(machine);
      displayMainMenu();
      machine.initBeBuyingProduct();
      Scanner sc = new Scanner(System.in);
      try {
        int choice = sc.nextInt();
        switch (choice) {
          case 1:
            ShowMenu.displayProductMenu(machine);
            break;
          case 2:
            while (true) {
              if (ShowMenu.insertCoinMenu(machine)) {
                break;
              }
            }
            break;
          case 3:
            while (true) {
              if (ShowMenu.buyProductMenu(machine)) {
                break;
              }
            }
            break;
          case 4:
            ShowMenu.returnMenu(machine);
            break;
          case 9:
            ServiceMenu.initDefaultCode();
            System.out.println("(9) Opening service menu. Access code is required.");
            System.out.print("Enter access code: ");
            if (ServiceMenu.isCode(sc.next())) {
              System.out.println("Correct code!");
              ShowMenu.showServiceMenu(machine);
            } else {
              System.out.println("Incorrect code!");
            }
            break;
          case 0:
            return;
          default:
            System.out.println("Invalid choice!");
        }
      } catch (Exception e) {
        System.out.println("Invalid input!");
      }
      System.out.println();
    }
  }

  public static void displayMainMenu() {
    System.out.println("What would you like to do?");
    for (String menu : TOP_MENU) {
      System.out.print(menu);
    }
    System.out.print("\nYour choice:");
  }
}
