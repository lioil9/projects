package club.banyuan.menu;

import club.banyuan.machine.Coin;
import club.banyuan.machine.Machine;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShowMenuTest {

    @org.junit.Test
    public void displayProductMenu() {
    }

    @org.junit.Test
    public void insertCoinMenu() {
        Machine machine = new Machine();
        machine.initMachine();
        ShowMenu.insertCoinMenu(machine);
    }

    @org.junit.Test
    public void backAndChoice() {

    }

    @org.junit.Test
    public void graphicMachine() {
        Machine machine = new Machine();
        machine.initMachine();
        machine.insertCoin(Coin.Ten);
        ShowMenu.graphicMachine(machine);
    }

    @Test
    public void testDisplayProductMenu() {
    }

    @Test
    public void testInsertCoinMenu() {
    }

    @Test
    public void buyProductMenu() {
    }

    @Test
    public void returnMenu() {
    }

    @Test
    public void showServiceMenu() {
    }

    @Test
    public void testBackAndChoice() {
    }

    @Test
    public void testGraphicMachine() {
    }
}