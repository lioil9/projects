package club.banyuan;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String MAIN_PAGE = "1.编辑菜单\n2.查看菜单\n0.退出程序";

    public static void main(String []args) {
        List<Menu>  menuList= new ArrayList<Menu>();
        //插入数据
        menuList.add(new Menu("0001","0","根菜单1"));
        menuList.add(new Menu("0101","0001","一级菜单1"));
        menuList.add(new Menu("0201","0101","二级菜单1-1"));
        menuList.add(new Menu("0202","0101","二级菜单1-2"));
        menuList.add(new Menu("0102","0001","一级菜单2"));
        menuList.add(new Menu("0301","0102","二级菜单2-1"));
        menuList.add(new Menu("0002","0","根菜单2"));
        menuList.add(new Menu("0003","0","根菜单3"));
        //创建树
        MenuTree menuTree =new MenuTree(menuList);
        menuList=menuTree.buildTree();

        ShowMenu.showRootMenu(menuList);
        ShowMenu.showMenu(menuList);
    }

    public static void mainInterface(){
        System.out.println(MAIN_PAGE);
        Scanner sc = new Scanner(System.in);


    }


}