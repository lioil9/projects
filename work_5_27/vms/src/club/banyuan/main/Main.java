package club.banyuan.main;

import club.banyuan.machine.VendingMachineWithMenu;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

public class Main {

  public static void main(String[] args) {
    // VendingMachine vendingMachine = new VendingMachine();
    // vendingMachine.start();
    VendingMachineWithMenu vendingMachineWithMenu =  deserializeVendingMachine();
    if (vendingMachineWithMenu == null) {
      vendingMachineWithMenu = new VendingMachineWithMenu();
    }

    vendingMachineWithMenu.start();
//    serializeVendingMachine(vendingMachineWithMenu);
  }

  public static void serializeVendingMachine(Serializable sl) {
    String path = Main.class.getClassLoader().getResource("").getPath();
    try {
      File file = new File(path + "vms.properties");
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
      oos.writeObject(sl);
      oos.flush();
      oos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static VendingMachineWithMenu deserializeVendingMachine() {
    String path = Objects.requireNonNull(Main.class.getClassLoader().getResource("")).getPath();
    File file = new File(path + "vms.properties");
    InputStream is = Main.class.getClassLoader().getResourceAsStream("/vms.properties");
    try {
      if (!file.exists()) {
        return null;
      }
      ObjectInputStream ois = new ObjectInputStream(is);
      return (VendingMachineWithMenu) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

}
