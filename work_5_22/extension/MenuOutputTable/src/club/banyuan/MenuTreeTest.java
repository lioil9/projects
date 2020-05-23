package club.banyuan;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class MenuTreeTest {

  @org.junit.Test
  public void buildMenuTree() {
    List<Menu> menuList = new ArrayList<>();
    menuList.add(new Menu(1001, "A", 0, 1));
    menuList.add(new Menu(1002, "B", 1001, 1));
    menuList.add(new Menu(1003, "C", 1001, 2));
    menuList.add(new Menu(1004, "D", 1002, 1));
    menuList.add(new Menu(1005, "E", 1002, 2));
    MenuTree menuTree = new MenuTree(menuList);
    menuList = menuTree.buildMenuTree();

  }

  @org.junit.Test
  public void buildChildMenuTree() {
  }

  @org.junit.Test
  public void getRootMenu() {
  }

  @Test
  public void calCol() {
  }

  @Test
  public void calRaw() {
    List<Menu> menuList = new ArrayList<>();
    menuList.add(new Menu(1001, "A", 0, 1));
    menuList.add(new Menu(1002, "B", 1001, 1));
    menuList.add(new Menu(1003, "C", 1001, 2));
    menuList.add(new Menu(1004, "D", 1002, 1));
    menuList.add(new Menu(1005, "E", 1002, 2));
    MenuTree menuTree = new MenuTree(menuList);
    menuList = menuTree.buildMenuTree();
    PrintHtml.calCol(menuList);
    PrintHtml.calRaw(menuList);

//    menuList.forEach(menu -> menu.setColspan(PrintHtml.calCol(menu,0)));
  }
}