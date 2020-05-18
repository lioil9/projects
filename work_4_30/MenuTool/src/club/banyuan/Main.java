package club.banyuan;
import java.util.*;

public class Main {
    private static final String MAIN_PAGE = "1.编辑菜单\n2.查看菜单\n0.退出程序";

    public static void main(String []args) {
        List<Menu> menuList = new ArrayList<Menu>();
        //插入数据
        menuList.add(new Menu("0|1","0","根菜单1"));
        menuList.add(new Menu("1|1","0|1","一级菜单1"));
        menuList.add(new Menu("2|1","1|1","二级菜单1-1"));
        menuList.add(new Menu("2|2","1|1","二级菜单1-2"));
        menuList.add(new Menu("1|2","0|1","一级菜单2"));
        menuList.add(new Menu("3|1","1|2","二级菜单2-1"));
        menuList.add(new Menu("0|2","0","根菜单2"));
        menuList.add(new Menu("0|3","0","根菜单3"));
        //创建树
        MenuTree menuTree =new MenuTree(menuList);
        menuList=menuTree.buildTree();

//        //System.out.println(TOP_PAGE);
//        Iterator<Menu> it = menuList.iterator();
//        while (it.hasNext()){
//            Menu menu = it.next();
//            System.out.println(menu.getName());
//        }

        //ShowMenu.showRootMenu(menuList);
        ShowMenu.Menu(menuList);
    }

    public static void mainInterface(){
        System.out.println(MAIN_PAGE);
        Scanner sc = new Scanner(System.in);

    }


}