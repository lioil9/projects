package club.banyuan;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PrintHtml {

  public static void calCol(List<Menu> menuList) {
    if (menuList == null || menuList.size() == 0) {
      return;
    }
    for (Menu menu : menuList) {
      menu.setColspan(PrintHtml.calNoChild(menu, 0));
      calCol(menu.getChildren());
    }
  }

  public static int calNoChild(Menu node, int col) {
    if (node == null || node.getChildren().size() == 0) {
      return col;
    }

    col += (int) node.getChildren().stream()
        .filter(m -> m == null || m.getChildren().size() == 0).count();

    List<Menu> collect = node.getChildren().stream()
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
    for (Menu menu : collect) {
      col = calNoChild(menu, col);
    }
    return col;
  }

  public static void calRaw(List<Menu> menuList, int deep) {
    if (menuList == null || menuList.size() == 0) {
      return;
    }
    List<Menu> noChildrenNode = menuList.stream()
        .filter(menu -> menu == null || menu.getChildren().size() == 0)
        .collect(Collectors.toList());
    noChildrenNode.forEach(menu -> menu.setRowspan(deep - menu.getDeep() + 1));
    menuList.forEach(menu -> calRaw(menu.getChildren(), deep));
  }

  public static int getDeepest(Menu node) {
    if (node.getChildren().size() == 0) {
      return node.getDeep();
    }
    int deep = node.getDeep();
    for (Menu menu : node.getChildren()) {
      if (deep < getDeepest(menu)) {
        deep = getDeepest(menu);
      }
    }
    return deep;
  }

  public static void printHtml(Menu node) {
    System.out.print("<td");
    if (node.getColspan() != null) {
      System.out.print((node.getColspan() == 0) ? "" : " colspan=" + node.getColspan());
    }
    if (node.getRowspan() != null) {
      System.out.print((node.getRowspan() == 0) ? "" : " rowspan=" + node.getRowspan());
    }
    System.out.print(">" + node.getName());
    System.out.print("</td>");
  }


}
