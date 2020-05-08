package club.banyuan;

import java.util.List;

public class Menu {
    private String id;
    private String parentId;
    private String name;
    private List<Menu> children;

    public Menu(String id, String parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public void showChildMenu(){
        for(int i=0; i<children.size(); i++){
            System.out.println(i+1+"."+children.get(i).getName());
        }
    }
}
