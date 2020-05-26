package club.banyuan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuTree {

  private final Integer RootDeep = 1;

  private final List<Menu> menus;

  public MenuTree(List<Menu> menus) {
    this.menus = menus;
  }

  public List<Menu> getMenus() {
    return menus;
  }

  public List<Menu> buildMenuTree() {
    return getRootMenu().stream()
        .map(m -> buildChildMenuTree(m, RootDeep))
        .collect(Collectors.toList());
  }

  public Menu buildChildMenuTree(Menu node, int deep) {
    List<Menu> childrenMenus = menus.stream()
        .filter(m -> m.getParentId().equals(node.getId()))
        .peek(m -> {
          m.setDeep(deep + 1);
          buildChildMenuTree(m, m.getDeep());
        })
        .collect(Collectors.toList());

    node.setChildren(childrenMenus);
    return node;
  }

  public List<Menu> getRootMenu() {
    List<Menu> menuList = new ArrayList<>();
    menus.stream().filter(m -> m.getParentId() == 0).forEach(m -> {
      menuList.add(m);
      m.setDeep(RootDeep);
    });

    return menuList;
  }
}
