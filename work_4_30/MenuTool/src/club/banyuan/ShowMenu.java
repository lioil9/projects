package club.banyuan;

import java.util.List;
import java.util.Scanner;

public class ShowMenu {
    private static final int RETURN = -1;
    private static  final String ROOT_ID = "0";
    private static final String TOP_PAGE = "--------当前为查看菜单，输入0返回上一级，输入-1返回主菜单---------" ;

    public static void showMenu(List<Menu> menuList){
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            int s = sc.nextInt() - 1;
            if(s == -2){
                break;
            }
            if(s == RETURN){
                System.out.println("当前已经是根目录，无法返回");
                showRootMenu(menuList);
                continue;
            }
            Menu menu = menuList.get(s);
            while (true) {
                System.out.println(TOP_PAGE);
                menu.showChildMenu();
                s = sc.nextInt() - 1;
                if(s == -2){
                    flag = false;
                    break;
                }
                if (s != RETURN) {
                    if (menu.getChildren().get(s).getChildren().size() == 0) {
                        System.out.println("该菜单中无子菜单,请重新选择");
                    } else {
                        menu = menu.getChildren().get(s);
                    }
                } else {
                    if (menu.getParentId().equals(ROOT_ID)) {
                        showRootMenu(menuList);
                        break;
                    }
                    for (Menu menuNode : menuList) {
                        if (menuNode.getId().equals(menu.getParentId()))
                            menu = menuNode;
                    }
                }
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
