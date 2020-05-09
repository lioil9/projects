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
    return false;
  }

  @Override
  public boolean containsValue(Object value) {
    return false;
  }

  @Override
  public Object get(Object key) {
    List list = getList(key);
    if (list == null) return null;

    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
      Entry entry = (Entry) iterator.next();
      if (entry.getKey().equals(key)) {
        return entry.getValue();
      }
    }

    return null;
  }

  private List getList(Object key) {
    if (key == null) {
      return null;
    }

    int hashCode = key.hashCode();
    List list = listArr[hashCode % INIT_LENGTH];
    if (list == null) {
      return null;
  }
    return list;
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
    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
      Entry entry = (Entry) iterator.next();
      if (entry.getKey().equals(key)) {
        entry.setValue(value);
        return value;
      }
    }
    list.add(new Entry(key, value));
    size++;
    return value;
  }

  @Override
  public Object remove(Object key) {
    if(key == null)
      return null;

    int hashCode = key.hashCode();
    List list = listArr[hashCode % INIT_LENGTH];
    if (list == null){
      return null;
    }

    Iterator iterator = list.iterator();
    while(iterator.hasNext()){
      Entry entry = (Entry) iterator.next();
      if(entry.getKey().equals(key)){
        Entry temp = entry;
        entry.setValue(null);
        entry.setKey(null);
        return temp;
      }
    }
    return null;
  }

}
