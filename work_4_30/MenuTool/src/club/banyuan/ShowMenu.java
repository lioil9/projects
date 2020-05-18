package club.banyuan;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ShowMenu {
    private static final int RETURN = -1;
    private static  final String ROOT_ID = "0";
    private static final String TOP_PAGE = "--------当前为查看菜单，输入0返回上一级，输入-1返回主菜单---------" ;

    public static void Menu(List<Menu> menuList){
        Scanner sc = new Scanner(System.in);
        List<Menu> rootMenuList = menuList;
        while (true){
            showRootMenu(menuList);
            int choice = sc.nextInt() - 1;
            int count = 0;
            if(choice>=0 && choice<menuList.size()){
                Menu menu = menuList.get(choice);
                if(menu.getChildren().size() == 0){
                    System.out.println("该菜单中无子菜单,请重新选择");
                    continue;
                }else {
                    menuList = menu.getChildren();
                    count++;
                }
            }else if(choice == RETURN){
                Menu temp = menuList.get(0);
                if (temp.getParentId().equals(ROOT_ID)) {
                    System.out.println("当前已经是根目录，无法返回");
                    continue;
                }
                temp = getParent(temp, rootMenuList);
                for(int i=1; i<count; i++){
                    for(int j=0; i<rootMenuList.size(); j++){
                        List<Menu> tempMenu = rootMenuList.get(j).getChildren();
                        if(tempMenu.contains(temp)){
                            menuList = tempMenu;
                        }
                    }
                }
//                for (Menu menuNode : rootMenuList) {
//                    if (menuNode.getId().equals(temp.getParentId()))
//                        menuList = getParent(menuNode, rootMenuList).getChildren();
//                }
            }else if(choice == -2){
                return;
            }else {
                System.out.println("无此菜单,请重新选择");
                continue;
            }

        }
    }

    public static Menu getParent(Menu menu, List<Menu> rootMenuList){
        for(Menu menuNode : rootMenuList){
            if(menuNode.getId().equals(menu.getParentId())){
                return menuNode;
            }
        }
        return menu;
    }

    public static void showSubMenu(Menu menu, List<Menu> menuList){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println(TOP_PAGE);
            menu.showChildMenu();
            int choice = sc.nextInt()-1;
            if(choice>=0 && choice<menu.getChildren().size()) {
                Menu temp = menu;
                menu = menu.getChildren().get(choice);
                if(menu.getChildren().size() == 0){
                    System.out.println("该菜单中无子菜单,请重新选择");
                    menu = temp;
                    continue;
                }
            }else if(choice == RETURN){
                if (menu.getParentId().equals(ROOT_ID)) {
                    break;
                }
                for (Menu menuNode : menuList) {
                    if (menuNode.getId().equals(menu.getParentId()))
                        menu = menuNode;
                }
            }else {
                System.out.println("无此菜单,请重新选择");
                continue;
            }
        }
    }

    public static void showRootMenu(List<Menu> menuList){
        System.out.println(TOP_PAGE);
        for(int i=0; i<menuList.size(); i++){
            System.out.println(i+1+"."+menuList.get(i).getName());
        }
    }

}
