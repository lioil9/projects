package club.banyuan.menu;

import club.banyuan.machine.Machine;

import java.util.Scanner;

public class TopMenu {
    public static void mainMenu(Machine machine) {
        while (true) {
            ShowMenu.graphicMachine(machine);
            displayMainMenu();
            machine.initBeBuyingProduct();
            Scanner sc = new Scanner(System.in);
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
                    while (true){
                        if(ShowMenu.buyProductMenu(machine)){
                            break;
                        }
                    }
                    break;
                case 4:
                    ShowMenu.returnMenu(machine);
                    break;
                case 9:
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
            System.out.println();
        }
    }

    public static void displayMainMenu() {
        System.out.println("What would you like to do?");
        System.out.println(" 1. Read product information\n" +
                " 2. Insert coin\n" +
                " 3. Press product button\n" +
                " 4. Press return button\n" +
                " 9. Open service menu (code required)\n" +
                " 0. Quit");
        System.out.println();
        System.out.print("Your choice:");
    }
}
