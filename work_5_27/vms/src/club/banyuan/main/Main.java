package club.banyuan.main;

import club.banyuan.machine.FlowStatus;
import club.banyuan.machine.VendingMachine;
import club.banyuan.machine.VendingMachineWithMenu;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import netscape.javascript.JSObject;

public class Main {

  public static void main(String[] args) {
    // VendingMachine vendingMachine = new VendingMachine();
    // vendingMachine.start();
    VendingMachineWithMenu vendingMachineWithMenu = deserializeVendingMachine();
    if(vendingMachineWithMenu == null) {
      vendingMachineWithMenu = new VendingMachineWithMenu();
    }
    vendingMachineWithMenu.start();
//    serializeVendingMachine(vendingMachineWithMenu);
  }

  public static void serializeVendingMachine(VendingMachineWithMenu vms) {
    try {
      ObjectOutputStream oos = new ObjectOutputStream(
          new FileOutputStream(new File("./information/vms.txt")));
      oos.writeObject(vms);
      oos.flush();
      oos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static VendingMachineWithMenu deserializeVendingMachine() {
    try {
      File file = new File("./information/vms.txt");
      if(!file.exists()){
        return null;
      }
      ObjectInputStream ois = new ObjectInputStream(
          new FileInputStream(file));
      VendingMachineWithMenu vms = (VendingMachineWithMenu) ois.readObject();
      return vms;
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }


}
