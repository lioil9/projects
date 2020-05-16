package club.banyuan.menu;

import club.banyuan.machine.Coin;
import club.banyuan.machine.Machine;
import club.banyuan.machine.Product;

import java.util.Scanner;

public class ShowMenu {

    public static void displayProductMenu(Machine machine) {
        System.out.println("\n(1) The displayed products are:");
        machine.displayProduct();
    }

    public static boolean insertCoinMenu(Machine machine) {
        boolean flag = false;
        System.out.println("\n(2) Which coin would you like to insert?");
        for (Coin coin : Coin.values()) {
            System.out.println(coin.ordinal() + 1 + ". $" + coin.getPrice());
        }
        int choice = backAndChoice();
        if (choice > 0 && choice <= Coin.values().length) {
            for (Coin coin : Coin.values()) {
                if (coin.ordinal() == choice - 1) {
                    machine.insertCoin(coin);
                    System.out.println("You have inserted $"+coin.getPrice());
                }
            }
        } else if (choice == 0) {
            System.out.println("Going back!");
            return true;
        } else {
            System.out.println("Invalid choice!");
        }
        System.out.println();
        graphicMachine(machine);
        return flag;
    }

    public static boolean buyProductMenu(Machine machine){
        boolean flag = false;
        System.out.println("\n(3) Which product button would you like to press?");
        for(int i=1; i<=5; i++){
            System.out.println(i+"."+machine.selectProduct(i).getId());
        }
        int choice = backAndChoice();
        if(choice>0 && choice<=5){
            flag = true;
            System.out.println("You have pressed button "+machine.selectProduct(choice).getId());
            machine.setBeBuyingProduct(machine.selectProduct(choice).getId());
            try{
                machine.buyProduct(machine.selectProduct(choice));
            } catch (Exception e){
                flag = false;
            }
        }else if(choice == 0){
            System.out.println("Going back!");
            return true;
        }
        return flag;
    }

    public static void returnMenu(Machine machine){
        System.out.println("\n(4) Return button is pressed");
        System.out.println("$"+machine.returnLefCoin()+" has been returned");
    }

    public static int backAndChoice() {
        System.out.println("0. Go back");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.print("Your choice:");
        return sc.nextInt();
    }

    public static void graphicMachine(Machine machine) {
        System.out.println("*---------------------------*");
        System.out.println("|     Vending   Machine     |");
        System.out.println("*---------------------------*");
        System.out.println("|   A    B    C    D    E   |");
        System.out.print("|");
        for (int i = 1; i <= 5; i++) {
            System.out.printf("  $%2d", machine.selectProduct(i).getPrice());
        }
        System.out.println("  |");
        System.out.print("|");
        for (int i = 1; i <= 5; i++) {
            machine.productStatus();
            if (machine.selectProduct(i).isEmpty()) {
                System.out.print("  [X]");
            } else {
                if (machine.selectProduct(i).isStatus()) {
                    System.out.print("  [O]");
                } else {
                    System.out.print("  [ ]");
                }
            }
        }
        System.out.println("  |");
        System.out.println("*---------------------------*");
        System.out.print("|                    [$");
        System.out.printf("%2d", machine.getLeftCoin());
        System.out.println("]  |");
        System.out.println("|                           |");
        System.out.println("|           [="+machine.getBeBuyingProduct()+"=]           |");
        System.out.println("*---------------------------*");
        System.out.println();
    }


}
