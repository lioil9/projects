package club.banyuan;

import java.util.List;
import java.util.Scanner;

public class ShowMenu {
    private static final int RETURN = -1;
    private static  final String ROOT_ID = "0";

    public static void showMenu(List<Menu> menuList){
        Scanner sc = new Scanner(System.in);

        while (true) {
            int s = sc.nextInt() - 1;
            if(s == RETURN){
                System.out.println("当前已经是根目录，无法返回");
                showRootMenu(menuList);
                continue;
            }
            Menu menu = menuList.get(s);
            while (true) {
                menu.showChildMenu();
                s = sc.nextInt() - 1;
                if (s == RETURN) {
                    if (menu.getParentId().equals(ROOT_ID)) {
                        showRootMenu(menuList);
                        break;
                    }
                    for (Menu menuNode : menuList) {
                        if (menuNode.getId().equals(menu.getParentId()))
                            menu = menuNode;
                    }
                    continue;
                }
                menu = menu.getChildren().get(s);
            }
        }
    }

    public static void showRootMenu(List<Menu> menuList){
        for(int i=0; i<menuList.size(); i++){
            System.out.println(i+1+"."+menuList.get(i).getName());
        }
    }

}
