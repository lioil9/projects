package club.banyuan;

import java.util.List;

public class Menu {
  private Integer id;
  private String name;
  private Integer parentId;
  private Integer order;
  private List<Menu> children;
  private Integer colspan;
  private Integer rowspan;
  private Integer deep;

  public Integer getDeep() {
    return deep;
  }

  public void setDeep(Integer deep) {
    this.deep = deep;
  }

  public Menu(Integer id, String name, Integer parentId, Integer order) {
    this.id = id;
    this.name = name;
    this.parentId = parentId;
    this.order = order;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  public List<Menu> getChildren() {
    return children;
  }

  public void setChildren(List<Menu> children) {
    this.children = children;
  }

  public Integer getColspan() {
    return colspan;
  }

  public void setColspan(Integer colspan) {
    this.colspan = colspan;
  }

  public Integer getRowspan() {
    return rowspan;
  }

  public void setRowspan(Integer rowspan) {
    this.rowspan = rowspan;
  }
}
