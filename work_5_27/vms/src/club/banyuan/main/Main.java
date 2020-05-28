package club.banyuan.main;

import club.banyuan.machine.FlowStatus;
import club.banyuan.machine.VendingMachine;
import club.banyuan.machine.VendingMachineWithMenu;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.Properties;
import netscape.javascript.JSObject;

public class Main {

  public static void main(String[] args) {
    // VendingMachine vendingMachine = new VendingMachine();
    // vendingMachine.start();
    VendingMachineWithMenu vendingMachineWithMenu = (VendingMachineWithMenu) deserializeVendingMachine();
    if (vendingMachineWithMenu == null) {
      vendingMachineWithMenu = new VendingMachineWithMenu();
    }
    vendingMachineWithMenu.start();
//    serializeVendingMachine(vendingMachineWithMenu);
  }

  public static void serializeVendingMachine(Serializable sl) {
    String path = Main.class.getClassLoader().getResource("").getPath() ;
    try {
      File file = new File(path+"vms.properties");
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
      oos.writeObject(sl);
      oos.flush();
      oos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Serializable deserializeVendingMachine() {
    String path = Main.class.getClassLoader().getResource("").getPath() ;
    try {
      File file = new File(path + "vms.properties");
      if (!file.exists()) {
        return null;
      }
      ObjectInputStream ois = new ObjectInputStream(
          new FileInputStream(file));
      return (Serializable) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

}
