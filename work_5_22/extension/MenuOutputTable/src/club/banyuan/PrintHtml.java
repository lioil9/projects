package club.banyuan;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PrintHtml {

  public static void calCol(List<Menu> menuList){
    if(menuList==null || menuList.size()==0){
      return;
    }
    for (Menu menu : menuList) {
      menu.setColspan(PrintHtml.calNoChild(menu,0));
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
      col = calNoChild(menu,col);
    }
    return col;
  }

  public static void calRaw(List<Menu> menuList) {
    if(menuList == null || menuList.size() == 0){
      return;
    }

    if(menuList.size() == 1){
      calRaw(menuList.get(0).getChildren());
    }else {
      List<Menu> noChildrenNode = menuList.stream()
          .filter(menu -> menu==null || menu.getChildren().size()==0)
          .collect(Collectors.toList());
      List<Menu> tempList = menuList;
      tempList.removeAll(noChildrenNode);
      int d = 0;
      for (Menu menu : tempList) {
        if(d < getDeepest(menu)){
          d = getDeepest(menu);
        }
      }
      for (Menu menu : noChildrenNode) {
        menu.setRowspan(d-menu.getDeep());
      }
      for (Menu resp : menuList) {
        calRaw(resp.getChildren());
      }
    }
  }

  public static int getDeepest(Menu node){

    if(node.getChildren().size()!=0){
      node.getChildren().forEach(PrintHtml::getDeepest);
    }
    return node.getDeep();
  }


}
