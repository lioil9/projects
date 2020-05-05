package club.banyuan;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String []args) {
        List<Menu>  menuList= new ArrayList<Menu>();
        /*插入一些数据*/
        menuList.add(new Menu("0001","0","根菜单1"));
        menuList.add(new Menu("0101","0001","一级菜单1"));
        menuList.add(new Menu("0201","0101","二级菜单1-1"));
        menuList.add(new Menu("0202","0101","二级菜单1-2"));
        menuList.add(new Menu("0102","0001","一级菜单2"));
        menuList.add(new Menu("0301","0102","二级菜单2-1"));
        menuList.add(new Menu("0002","0","根菜单2"));
        menuList.add(new Menu("0003","0","根菜单3"));
        /*让我们创建树*/
        MenuTree menuTree =new MenuTree(menuList);
        menuList=menuTree.builTree();
        for(int i=0; i<menuList.size(); i++){
            System.out.println(i+1+menuList.get(i).getName());
        }
        showMenu(menuList,menuTree);


    }
    public static void showMenu(List<Menu> menuList, MenuTree menuTree){
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt() - 1;
        if (s == -1) {
            for (int i = 0; i < menuTree.getParentNode(menuList.get(s)).size(); i++) {
                System.out.println(i + 1 + menuTree.getParentNode(menuList.get(s)).get(i).getName());
            }
        } else {
            for (int i = 0; i < menuList.get(s).getChildren().size(); i++) {
                System.out.println(i + 1 + menuList.get(s).getChildren().get(i).getName());
            }
        }
        showMenu(menuList, menuTree);
    }


}