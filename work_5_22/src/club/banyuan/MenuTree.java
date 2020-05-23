package club.banyuan;

import java.util.ArrayList;
import java.util.List;

public class MenuTree {
    private List<Menu> menuList;

    public MenuTree(List<Menu> menuList) {
        this.menuList=menuList;
    }

    //建立树形结构
    public List<Menu> buildTree(){
        List<Menu> treeMenus = new  ArrayList<Menu>();
        for(Menu menuNode : getRootNode()) {
            menuNode = buildChildTree(menuNode);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //递归，建立子树形结构
    private Menu buildChildTree(Menu pNode){
        List<Menu> childMenus = new  ArrayList<Menu>();
        for(Menu menuNode : menuList) {
            if(menuNode.getParentId().equals(pNode.getId())) {
                childMenus.add(buildChildTree(menuNode));
            }
        }
        pNode.setChildren(childMenus);
        return pNode;
    }

    //获取根节点
    private List<Menu> getRootNode() {
        List<Menu> rootMenuLists =new  ArrayList<Menu>();
        for(Menu menuNode : menuList) {
            if(menuNode.getParentId().equals("0")) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }

}
