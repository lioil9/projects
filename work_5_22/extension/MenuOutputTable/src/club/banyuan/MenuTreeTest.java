package club.banyuan;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
    menuList.add(new Menu(1010, "O", 0, 2));
    menuList.add(new Menu(1002, "B", 1001, 1));
    menuList.add(new Menu(1003, "C", 1001, 2));
    menuList.add(new Menu(1004, "D", 1002, 1));
    menuList.add(new Menu(1004, "J", 1002, 2));
    menuList.add(new Menu(1005, "E", 1002, 3));
    menuList.add(new Menu(1006, "F", 1005, 1));
    menuList.add(new Menu(1007, "G", 1005, 2));
    menuList.add(new Menu(1008, "H", 1003, 4));
    menuList.add(new Menu(1009, "I", 1003, 5));
    MenuTree menuTree = new MenuTree(menuList);
    menuList = menuTree.buildMenuTree();
    PrintHtml.calCol(menuList);
    int deepest = PrintHtml.getDeepest(menuList.get(0));
    PrintHtml.calRaw(menuList, deepest);
    System.out.println("<table border=\"1px \" align=\"center\"; style=\"text-align: center\"; height=200 width=200>");
    for (int i = 1; i <= deepest; i++) {
      int finalI = i;
      List<Menu> menus = menuTree.getMenus().stream()
          .filter(s -> s.getDeep() == finalI)
          .collect(Collectors.toList());
      System.out.print("<tr>");
      for(int j=1; j<=menuList.get(0).getColspan(); j++){
        int finalJ = j;
        menus.stream().filter(s -> s.getOrder()==finalJ).forEach(PrintHtml::printHtml);
      }
      System.out.println("</tr>");
    }
    System.out.println("</table>");
  }
}