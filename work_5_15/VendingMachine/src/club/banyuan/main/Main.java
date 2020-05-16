package club.banyuan.main;

import club.banyuan.machine.Machine;
import club.banyuan.menu.ShowMenu;
import club.banyuan.menu.TopMenu;

public class Main {
    public static void main(String[] args) {
        Machine machine = new Machine();
        machine.initMachine();
        TopMenu.mainMenu(machine);
    }
}
