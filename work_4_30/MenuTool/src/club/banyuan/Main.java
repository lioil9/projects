package club.banyuan;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String []args) {
        List<Menu>  menuList= new ArrayList<Menu>();
        //插入数据
        menuList.add(new Menu("0001","0","根菜单1", 0));
        menuList.add(new Menu("0101","0001","一级菜单1", 1));
        menuList.add(new Menu("0201","0101","二级菜单1-1", 2));
        menuList.add(new Menu("0202","0101","二级菜单1-2", 2));
        menuList.add(new Menu("0102","0001","一级菜单2", 1));
        menuList.add(new Menu("0301","0102","二级菜单2-1", 2));
        menuList.add(new Menu("0002","0","根菜单2", 0));
        menuList.add(new Menu("0003","0","根菜单3", 0));
        //创建树
        MenuTree menuTree =new MenuTree(menuList);
        menuList=menuTree.builTree();
        for(int i=0; i<menuList.size(); i++){
            System.out.println(i+1+menuList.get(i).getName());
        }
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt() - 1;
        Menu menu = menuList.get(s);
        while(true) {
            showMenu(menu);
            s = sc.nextInt() - 1;
            menu = menu.getChildren().get(s);
        }

    }
    public static void showMenu(Menu menuList){
                for (int i = 0; i < menuList.getChildren().size(); i++) {
                    System.out.println(i + 1 + menuList.getChildren().get(i).getName());
                }
    }


}