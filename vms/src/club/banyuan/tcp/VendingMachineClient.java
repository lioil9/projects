package club.banyuan.tcp;

import static club.banyuan.machine.FlowStatus.ADMIN_CHANGE_PRODUCT;
import static club.banyuan.machine.FlowStatus.ADMIN_INSPECT_STATUS;
import static club.banyuan.machine.FlowStatus.ADMIN_REFILL_PRODUCT;
import static club.banyuan.machine.FlowStatus.ADMIN_ROOT;
import static club.banyuan.machine.FlowStatus.ADMIN_WITHDRAW_MONEY;
import static club.banyuan.machine.FlowStatus.INSERT_COIN;
import static club.banyuan.machine.FlowStatus.OPEN_SERVICE_MENU;
import static club.banyuan.machine.FlowStatus.PRESS_PRODUCT_BTN;
import static club.banyuan.machine.FlowStatus.PRESS_RETURN_BTN;
import static club.banyuan.machine.FlowStatus.QUIT;
import static club.banyuan.machine.FlowStatus.READ_PRODUCT_INFO;
import static club.banyuan.machine.FlowStatus.ROOT;

import club.banyuan.machine.FlowStatus;
import club.banyuan.machine.Shelf;
import club.banyuan.machine.VendingMachineException;
import club.banyuan.menu.Menu;
import club.banyuan.menu.MenuFlow;
import club.banyuan.menu.MenuNode;
import club.banyuan.menu.MenuType;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class VendingMachineClient implements MenuFlow<FlowStatus> {

  public static final String NO_PURCHASE = "=";
  public static final String PRODUCT_SELL_OUT = "X";
  public static final String PRODUCT_AVAILABLE = "O";
  public static final String PRODUCT_DEFAULT = " ";
  public static final int FULL_INVENTORY = 10;


  private Menu<FlowStatus> menu;
  private int userAmount;
  private int salesAmount;
  private String purchasedProduct = NO_PURCHASE;
  private Shelf[] shelves;

  private FlowStatus flowStatus = ROOT;

  /*
   * 构造代码块
   * 在创建对象的时候进行初始化动作。
   */ {
    initMenu();
  }

  public void setShelves(Shelf[] shelves) {
    this.shelves = shelves;
  }

  /**
   * 更新接收服务端的货架，每次客户端对商品进行改动时都会接收新的Shelf数组并更新
   * @param socket
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public void updateShelves(Socket socket) throws IOException, ClassNotFoundException {
    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
    BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
    oos.writeObject(Command.UPDATE);
    oos.flush();
    ObjectInputStream ois = new ObjectInputStream(bis);
    setShelves((Shelf[]) ois.readObject());
  }


  private void initMenu() {
  /*
  初始化菜单
   */

    List<MenuNode<FlowStatus>> list = Arrays.asList(
        new MenuNode<>(null, null, "What would you like to do?", ROOT, null,
            MenuType.ROOT),
        new MenuNode<>(" 1. Read product information", "1", "(1) The displayed products are:",
            READ_PRODUCT_INFO, ROOT, MenuType.SINGLE),
        new MenuNode<>(" 2. Insert coin", "2", "(2) Which coin would you like to insert?",
            INSERT_COIN, ROOT, MenuType.PARENT),

        new MenuNode<>(" 1. $1", "1", null,
            null, INSERT_COIN, MenuType.SINGLE),
        new MenuNode<>(" 2. $2", "2", null,
            null, INSERT_COIN, MenuType.SINGLE),
        new MenuNode<>(" 3. $5", "3", null,
            null, INSERT_COIN, MenuType.SINGLE),
        new MenuNode<>(" 4. $10", "4", null,
            null, INSERT_COIN, MenuType.SINGLE),
        new MenuNode<>(" 0. Go back", "0", null,
            null, INSERT_COIN, MenuType.GO_BACK),

        new MenuNode<>(" 3. Press product button", "3",
            "(3) Which product button would you like to press?",
            PRESS_PRODUCT_BTN, ROOT, MenuType.SINGLE),
        new MenuNode<>(" 4. Press return button", "4", "(4) Return button is pressed.",
            PRESS_RETURN_BTN, ROOT, MenuType.SINGLE),

        new MenuNode<>(" 9. Open service menu (code required)", "9", null,
            OPEN_SERVICE_MENU, ROOT, MenuType.PARENT),
        new MenuNode<>(null, null, "(9) What would you like to do?",
            ADMIN_ROOT, OPEN_SERVICE_MENU, MenuType.PARENT),
        new MenuNode<>(" 1. Inspect machine status", "1", "(9-1) Machine status",
            ADMIN_INSPECT_STATUS, ADMIN_ROOT, MenuType.SINGLE),
        new MenuNode<>(" 2. Withdraw all money", "2", "(9-2) All money is being withdrawn.",
            ADMIN_WITHDRAW_MONEY, ADMIN_ROOT, MenuType.SINGLE),
        new MenuNode<>(" 3. Refill product", "3", "(9-3) Which product would you like to refill?",
            ADMIN_REFILL_PRODUCT, ADMIN_ROOT, MenuType.SINGLE),
        new MenuNode<>(" 4. Change product", "4", "(9-4) Which product would you like to change?",
            ADMIN_CHANGE_PRODUCT, ADMIN_ROOT, MenuType.SINGLE),
        new MenuNode<>(" 0. Go back", "0", null,
            null, ADMIN_ROOT, MenuType.GO_BACK),

        new MenuNode<>(" 0. Quit", "0", null,
            QUIT, ROOT, MenuType.SINGLE));
    menu = new Menu<>(list);
    menu.setMenuFlow(this);
  }


  public void displayShelves(Socket socket) throws IOException, ClassNotFoundException {
    updateShelves(socket);
    StringBuilder stringBuilder = new StringBuilder();
    buildLogoDisplay(stringBuilder);
    buildShelvesDisplay(stringBuilder);
    buildBottomDisplay(stringBuilder);
    System.out.println(stringBuilder.toString());
    System.out.println();
  }

  private void buildBottomDisplay(StringBuilder stringBuilder) {
    stringBuilder.append("*---------------------------*").append(System.lineSeparator());
    stringBuilder.append(String.format("|                    [$%2s]  |", userAmount))
        .append(System.lineSeparator());
    stringBuilder.append("|                           |").append(System.lineSeparator());
    stringBuilder.append(String.format("|           [=%s=]           |", purchasedProduct))
        .append(System.lineSeparator());
    stringBuilder.append("*---------------------------*");
  }

  private void buildLogoDisplay(StringBuilder stringBuilder) {
    stringBuilder
        .append("*---------------------------*").append(System.lineSeparator())
        .append("|     Vending   Machine     |").append(System.lineSeparator())
        .append("*---------------------------*").append(System.lineSeparator());
  }

  private void buildShelvesDisplay(StringBuilder stringBuilder) {

    String[] codeTemplate = new String[shelves.length];
    int[] priceTemplate = new int[shelves.length];
    String[] inventoryTemplate = new String[shelves.length];

    for (int i = 0; i < shelves.length; i++) {
      codeTemplate[i] = shelves[i].getCode();
      priceTemplate[i] = shelves[i].getPrice();
      // 如果用户投币的金额如果小于 产品的金额，不显示内容。
      // 如果用户投币的金额大于产品金额，显示O
      // 如果产品库存为0，显示X
      inventoryTemplate[i] = shelves[i].getInventory() == 0 ? PRODUCT_SELL_OUT
          : (shelves[i].getPrice() <= userAmount ? PRODUCT_AVAILABLE : PRODUCT_DEFAULT);
    }

    stringBuilder.append(String
        .format("|   %s    %s    %s    %s    %s   |", codeTemplate[0], codeTemplate[1],
            codeTemplate[2], codeTemplate[3], codeTemplate[4])).append(System.lineSeparator());

    stringBuilder.append(String
        .format("|  $%2s  $%2s  $%2s  $%2s  $%2s  |", priceTemplate[0], priceTemplate[1],
            priceTemplate[2], priceTemplate[3], priceTemplate[4])).append(System.lineSeparator());
    stringBuilder.append(String
        .format("|  [%s]  [%s]  [%s]  [%s]  [%s]  |", inventoryTemplate[0], inventoryTemplate[1],
            inventoryTemplate[2], inventoryTemplate[3], inventoryTemplate[4]))
        .append(System.lineSeparator());
  }

  public void start(Socket socket) throws IOException, ClassNotFoundException {
    displayShelves(socket);
    String userInput;
    MenuNode<FlowStatus> menuNode;
    while (true) {
      switch (flowStatus) {
        case ROOT:
          updateShelves(socket);
          System.out.println();
          menu.display();
          menuNode = menu.scanUserInput();
          flowStatus = changeMenuFromRoot(menuNode.getInputMatches());
          menu.toNextMenu(flowStatus);
          break;
        case READ_PRODUCT_INFO:
          System.out.println();
          menu.display();
          displayProductInfo(socket);
          menu.back();
          break;
        case INSERT_COIN:
          System.out.println();
          menu.display();
          menuNode = menu.scanUserInput();
          flowStatus = changeMenuFromInsertCoin(menuNode.getInputMatches(), socket);
          menu.toNextMenu(flowStatus);
          break;
        case PRESS_PRODUCT_BTN:
          displayShelvesMenuForPressBtn(socket);
          userInput = scanUserInput(
              getInputPredicate(shelves.length),
              t -> {
                try {
                  displayShelvesMenuForPressBtn(socket);
                } catch (IOException | ClassNotFoundException e) {
                  e.printStackTrace();
                }
              }
          );
          flowStatus = changeMenuFromPressProductBtn(userInput, socket);
          menu.toNextMenu(flowStatus);
          break;
        case PRESS_RETURN_BTN:
          menu.display();
          returnUserAmount();
          displayShelves(socket);
          menu.back();
          break;
        case OPEN_SERVICE_MENU:
          flowStatus = authentication(socket);
          if (flowStatus == ROOT) {
            displayShelves(socket);
          }
          menu.toNextMenu(flowStatus);
          break;
        case ADMIN_ROOT:
          menu.display();
          menuNode = menu.scanUserInput();
          flowStatus = changeMenuFromAdminRoot(menuNode.getInputMatches());
          menu.toNextMenu(flowStatus);
          break;
        case ADMIN_INSPECT_STATUS:
          menu.display();
          displayMachineStatus(socket);
          menu.back();
          break;
        case ADMIN_WITHDRAW_MONEY:
          menu.display();
          withdrawMoney();
          menu.back();
          break;
        case ADMIN_REFILL_PRODUCT:
          menu.display();
          displayShelvesAsMenu();
          userInput = scanUserInput(getInputPredicate(shelves.length), t -> {
            menu.display();
            displayShelvesAsMenu();
          });
          refillProduct(userInput, socket);
          menu.back();
          break;
        case ADMIN_CHANGE_PRODUCT:
          displayShelvesAsMenu();
          userInput = scanUserInput(getInputPredicate(shelves.length),
              t -> {
                displayShelvesAsMenu();
              });
          changeProduct(userInput, socket);
          menu.back();
          break;
        case QUIT:
          System.exit(0);
      }
    }
  }

  private void displayShelvesMenuForPressBtn(Socket socket)
      throws IOException, ClassNotFoundException {
    System.out.println();
    displayShelves(socket);
    System.out.println();
    displayShelvesAsMenu();
  }

  private void changeProduct(String userInput, Socket socket)
      throws IOException, ClassNotFoundException {
    int index = Integer.parseInt(userInput);
    if (index - 1 >= 0) {
      Shelf shelf = shelves[index - 1];
      Scanner scanner = new Scanner(System.in);
      System.out.printf("You are changing product %s.\n", shelf.getCode());
      System.out.print("Enter new product name:");
      String name = scanner.next();
      int price;
      try {
        System.out.print("Enter new product price:");
        price = scanner.nextInt();
      } catch (Exception e) {
        System.out.println("Invalid input");
        changeProduct(userInput, socket);
        return;
      }
      shelf.setName(name);
      shelf.setPrice(price);
      shelf.setInventory(FULL_INVENTORY);

      ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
      oos.writeObject(Command.CHANGE);
      oos.flush();
      oos.writeObject(shelf);
      oos.flush();
      updateShelves(socket);
      System.out.printf("The new product %s has been filled to full.\n", shelf.getCode());

    }
  }

  private void refillProduct(String userInput, Socket socket)
      throws IOException, ClassNotFoundException {
    int index = Integer.parseInt(userInput);
    if (index - 1 >= 0) {
      Shelf refileShelf = shelves[index - 1];
      ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
      oos.writeObject(Command.REFILL);
      oos.flush();
      oos.writeObject(refileShelf);
      oos.flush();
      System.out.printf("You have refilled product %s to full.\n", refileShelf.getCode());
      updateShelves(socket);
    }
  }

  private void displayShelvesAsMenu() {
    menu.display();
    for (int i = 0; i < shelves.length; i++) {
      System.out.printf("%d. %s\n", i + 1, shelves[i].getCode());
    }
    System.out.println("0. Go back");
  }

  private void withdrawMoney() {
    System.out.printf("$%s is withdrawn.\n", salesAmount);
    salesAmount = 0;
  }

  /**
   * (9-1) Machine status Amount of revenue: $6 Amount of inserted coins: $0 A. Juice ($10) (5 left)
   * B. Cola ($6) (sold out) C. Tea ($5) (2 left) D. Water ($8) (1 left) E. Coffee ($7) (9 left)
   */
  private void displayMachineStatus(Socket socket) throws IOException, ClassNotFoundException {
    updateShelves(socket);
    System.out.printf("Amount of revenue: $%s\n", salesAmount);
    System.out.printf("Amount of inserted coins: $%s\n", userAmount);
    for (Shelf shelf : shelves) {
      int inventory = shelf.getInventory();
      String inventoryLeft = inventory == 0 ? "sold out" : inventory + " left";
      System.out.printf("%s. %s ($%s) (%s)\n", shelf.getCode(), shelf.getName(), shelf.getPrice(),
          inventoryLeft);
    }
    System.out.println();
  }

  private FlowStatus changeMenuFromAdminRoot(String userInput) {
    switch (userInput) {
      case "1":
        return ADMIN_INSPECT_STATUS;
      case "2":
        return ADMIN_WITHDRAW_MONEY;
      case "3":
        return ADMIN_REFILL_PRODUCT;
      case "4":
        return ADMIN_CHANGE_PRODUCT;
      case "0":
        return ROOT;
    }
    throw new VendingMachineException("user input invalid:" + userInput);
  }

  private FlowStatus authentication(Socket socket) throws IOException {
    System.out.println("(9) Opening service menu. Access code is required.");
    System.out.print("Enter access code:");
    Scanner scanner = new Scanner(System.in);
    String userInput = scanner.next();

    try {
      ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
      BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
      oos.writeObject(Command.PASSWORD);
      oos.flush();
      byte[] bytes = new byte[32];
      int count = bis.read(bytes);
      String password = new String(bytes, 0, count);
      if (password.equals(userInput)) {
        return ADMIN_ROOT;
      }
    } catch (NumberFormatException e) {
      // 不做任何处理，保证程序运行
    }
    System.out.println("Incorrect code!");
    System.out.println();
    return ROOT;

  }

  private void returnUserAmount() {
    System.out.printf("$%s has been returned.\n", userAmount);
    System.out.println();
    userAmount = 0;
  }

  private FlowStatus changeMenuFromPressProductBtn(String userInput, Socket socket)
      throws IOException, ClassNotFoundException {

    int index = Integer.parseInt(userInput);
    if (index == 0) {
      System.out.println("Going Back!");
      return ROOT;
    }
    Shelf purchase = shelves[index - 1];
    System.out.printf("You have pressed button %s.\n", purchase.getCode());
    if (purchaseProduct(purchase, socket)) {
      return ROOT;
    } else {
      System.out.println("invalid choice");
      return PRESS_PRODUCT_BTN;
    }
  }

  private boolean purchaseProduct(Shelf purchase, Socket socket)
      throws IOException, ClassNotFoundException {
    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
    BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
    oos.writeObject(Command.PURCHASE);
    oos.flush();
    oos.writeObject(purchase);
    oos.flush();
    ObjectInputStream ois = new ObjectInputStream(bis);
    boolean flag = (Boolean) ois.readObject();
    if (flag && userAmount >= purchase.getPrice()) {
      userAmount -= purchase.getPrice();
      salesAmount += purchase.getPrice();
      purchasedProduct = purchase.getCode();
      displayShelves(socket);
      purchasedProduct = NO_PURCHASE;
      return true;
    } else {
      return false;
    }
  }

  private Predicate<String> getInputPredicate(int to) {
    return s -> {
      int inputInt;
      try {
        inputInt = Integer.parseInt(s);
      } catch (NumberFormatException e) {
        return false;
      }
      return inputInt >= 0 && inputInt <= to;
    };
  }

  private FlowStatus changeMenuFromInsertCoin(String userInput, Socket socket)
      throws IOException, ClassNotFoundException {
    int amount = 0;
    switch (userInput) {
      case "1":
        amount += 1;
        break;
      case "2":
        amount += 2;
        break;
      case "3":
        amount += 5;
        break;
      case "4":
        amount += 10;
        break;
      case "0":
        System.out.println("Going back!");
        System.out.println();
        displayShelves(socket);
        return ROOT;
    }
    userAmount += amount;
    System.out.printf("You have inserted $%s.\n\n", amount);
    displayShelves(socket);
    return INSERT_COIN;
  }


  /**
   * (1) The displayed products are: A. Juice ($10) B. Cola ($6) C. Tea ($5) D. Water ($8) E. Coffee
   * ($7)
   */
  private void displayProductInfo(Socket socket) throws IOException, ClassNotFoundException {
    updateShelves(socket);
    for (Shelf shelf : shelves) {
      System.out.printf("%s. %s ($%s)\n", shelf.getCode(), shelf.getName(), shelf.getPrice());
    }
    System.out.println();
  }

  private FlowStatus changeMenuFromRoot(String userInput) {
    switch (userInput) {
      case "1":
        return READ_PRODUCT_INFO;
      case "2":
        return INSERT_COIN;
      case "3":
        return PRESS_PRODUCT_BTN;
      case "4":
        return PRESS_RETURN_BTN;
      case "9":
        return OPEN_SERVICE_MENU;
      case "0":
        return QUIT;
      default:
        throw new VendingMachineException("user input invalid:" + userInput);
    }
  }


  private String scanUserInput(Predicate<String> inputValidate,
      Consumer<String> displayDuringInputWrong) {
    Scanner scanner = new Scanner(System.in);
    System.out.println();
    System.out.print("Your choice:");
    String userInput = scanner.next();
    boolean testResult = inputValidate.test(userInput);
    if (!testResult) {
      System.out.println("Invalid choice!");
      System.out.println();
      if (displayDuringInputWrong != null) {
        displayDuringInputWrong.accept(userInput);
      }
      userInput = scanUserInput(inputValidate, displayDuringInputWrong);
    }
    return userInput;
  }

  @Override
  public void setFlowStatus(FlowStatus flowStatus) {
    this.flowStatus = flowStatus;
  }

}
