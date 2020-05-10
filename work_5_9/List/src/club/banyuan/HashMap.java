package club.banyuan;

import club.banyuan.collection.ArrayList;
import club.banyuan.collection.List;
import club.banyuan.util.Iterator;

public class HashMap implements Map {

  public static final int INIT_LENGTH = 20;
  private List[] listArr = new List[INIT_LENGTH];
  private int size;

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean containsKey(Object key) {
    if (key == null) {
      return false;
    }
    List list = getList(key);
    if(list == null){
      return false;
    }

    Entry entry = getEntry(list, key);
    if(entry != null){
      return true;
    }
    return false;
  }

  @Override
  public boolean containsValue(Object value) {
    for (List list : listArr) {
      if (list == null)
        continue;
      Iterator iterator = list.iterator();
      while (iterator.hasNext()) {
        Entry entry = (Entry) iterator.next();
        if(value == null && entry.getValue() == null)
          return true;
        if (entry.getValue().equals(value))
          return true;
      }
    }
    return false;
  }

  @Override
  public Object get(Object key) {
    if (key == null) {
      return null;
    }
    List list = getList(key);
    if (list == null) {
      return null;
    }
    Entry entry = getEntry(list, key);
    if(entry != null){
      return entry.getValue();
    }
    return null;
  }


  @Override
  public Object put(Object key, Object value) {
    if (key == null) {
      return null;
    }

    int hashCode = key.hashCode();
    List list = listArr[hashCode % INIT_LENGTH];
    if (list == null) {
      list = new ArrayList();
      listArr[hashCode % INIT_LENGTH] = list;
    }

    Entry entry = getEntry(list, key);
    if(entry != null){
      entry.setValue(value);
      return value;
    }

    list.add(new Entry(key, value));
    size++;
    return value;
  }

  @Override
  public Object remove(Object key) {
    if(!containsKey(key)){
      System.out.println("不包含此元素");
      return null;
    }

    List list = getList(key);
    Entry entry = getEntry(list, key);
    if(entry != null){
      Object temp = entry.getValue();
      list.remove(entry);
      size--;
      return temp;
    }
    return null;
  }

  //通过key查找list
  private List getList(Object key) {
    int hashCode = key.hashCode();
    return listArr[hashCode % INIT_LENGTH];
  }

  //  通过key值和list查找entry并返回
  private Entry getEntry(List list, Object key){
    Iterator iterator = list.iterator();
    while(iterator.hasNext()) {
      Entry entry = (Entry) iterator.next();
      if(entry.getKey().equals(key)){
        return entry;
      }
    }
    return null;
  }

}
