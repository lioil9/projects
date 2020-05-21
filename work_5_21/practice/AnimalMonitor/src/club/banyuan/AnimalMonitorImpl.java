package club.banyuan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 监视不同种类动物的数量。由观察者记录目击事件。
 */
public class AnimalMonitorImpl implements AnimalMonitor {

  // 记录所有发现的动物。
  private List<Sighting> sightings;

  /**
   * 创建一个AnimalMonitor。
   */
  public AnimalMonitorImpl() {
    this.sightings = new SightingFiller().getSightings();
  }


  /**
   * 打印所有目击动物的详细信息。
   */
  @Override
  public void printList() {
    for (Sighting sighting : sightings) {
      System.out.println(sighting.getDetails());
    }
  }

  /**
   * 打印给定动物的所有目击细节。
   *
   * @param animal 动物的类型。
   */
  @Override
  public void printSightingsOf(String animal) {
    for (Sighting sighting : sightings) {
      if (sighting.getAnimal().equals(animal)) {
        System.out.println(sighting.getDetails());
      }
    }

  }

  /**
   * 打印给定观察者的所有目击动物。
   *
   * @param spotter 观察者的ID。
   */
  @Override
  public void printSightingsBy(int spotter) {
    sightings.stream().filter(sighting -> sighting.getSpotter() == spotter)
        .map(Sighting::getDetails)
        .forEach(System.out::println);
  }

  /**
   * 打印一份被视为濒临灭绝的动物的清单。
   *
   * @param animalNames     动物名称列表。
   * @param dangerThreshold 小于或等于此级别的动物总数被认为濒临灭绝的
   */
  @Override
  public void printEndangered(List<String> animalNames, int dangerThreshold) {
    Set<String> dangerList = new HashSet<>();
    for (String animalName : animalNames) {
      if (getCount(animalName) <= dangerThreshold) {
        dangerList.add(animalName);
      }
    }
    System.out.println(dangerList);
  }

  /**
   * 打印在特定期间periodID内记录的所有目击事件的详细信息，并将其作为参数传递给该方法
   *
   * @param period 日期ID
   * @return 指定日期的清单
   */
  @Override
  public List<Sighting> printSightingsInPeriod(int period) {
    List<Sighting> sightingList = new ArrayList<>();
    for (Sighting sighting : sightings) {
      if (sighting.getPeriod() == period) {
        System.out.println(sighting.getDetails());
        sightingList.add(sighting);
      }
    }
    return sightingList;
  }

  /**
   * 打印并返回指定日期区间内的清单
   *
   * @param fromPeriod 日期开始
   * @param toPeriod   日期结束
   * @param animal     动物类型
   */
  @Override
  public List<Sighting> printSightingsOfInPeriod(int fromPeriod, int toPeriod, String animal) {
    List<Sighting> sightingList = new ArrayList<>();
    for (Sighting sighting : sightings) {
      if (sighting.getPeriod() >= fromPeriod && sighting.getPeriod() <= toPeriod
          && sighting.getAnimal().equals(animal)) {
        System.out.println(sighting.getDetails());
        sightingList.add(sighting);
      }
    }
    return sightingList;
  }

  /**
   * 打印特定动物类型的总的目击数量
   *
   * @param animal 动物类型
   */
  @Override
  public List<Sighting> printCounts(String animal) {
    List<Sighting> countList = sightings.stream()
        .filter(sighting -> sighting.getAnimal().equals(animal))
        .collect(Collectors.toList());

    return countList;
  }

  /**
   * 返回给定动物目击次数。
   *
   * @param animal 动物的类型。
   * @return 给定动物的目击次数总数。
   */
  @Override
  public int getCount(String animal) {
    List<Sighting> countList = printCounts(animal);
    return countList.stream().mapToInt(Sighting::getCount).sum();
  }

  /**
   * 从发现清单中删除计数为零的记录。
   */
  @Override
  public void removeZeroCounts() {
    sightings = sightings.stream()
        .filter(sighting -> sighting.getCount() != 0)
        .collect(Collectors.toList());
  }

  /**
   * 返回特定区域内给定类型的动物的所有动物清单。
   *
   * @param animal 动物的类型。
   * @param area   区域的ID。
   * @return 目击清单。
   */
  @Override
  public List<Sighting> getSightingsInArea(String animal, int area) {
    return sightings.stream()
        .filter(sighting -> sighting.getAnimal().equals(animal) && sighting.getArea() == area)
        .collect(Collectors.toList());
  }

  /**
   * 返回给定动物的所有目击清单。
   *
   * @param animal 动物的类型。
   * @return 给定动物的所有目击物清单。
   */
  @Override
  public List<Sighting> getSightingsOf(String animal) {
    return sightings.stream()
        .filter(sighting -> sighting.getAnimal().equals(animal))
        .collect(Collectors.toList());
  }

  /**
   * @param spotter
   * @param period
   * @return 包含该观察者在特定日期看到的动物的名称，只包括数量大于零的动物
   */
  @Override
  public List<String> getAnimalBy(int spotter, int period) {
    return sightings.stream()
        .filter(s -> s.getSpotter()==spotter && s.getPeriod()==period && s.getCount()>0)
        .map(Sighting::getAnimal)
        .collect(Collectors.toList());
  }

  /**
   * @param animal
   * @param period
   * @return 在该特定日期看到该动物的观察者
   */
  @Override
  public List<Integer> getSpotterBy(String animal, int period) {
    return sightings.stream()
        .filter(s -> s.getAnimal().equals(animal) && s.getPeriod()==period)
        .map(Sighting::getSpotter)
        .collect(Collectors.toList());
  }

  @Override
  public List<String> getAnimalBySpotterInDay(int spotter, int period) {
    return sightings.stream()
        .filter(s -> s.getSpotter()==spotter && s.getPeriod()==period)
        .map(Sighting::getAnimal)
        .collect(Collectors.toList());
  }

  @Override
  public int getSpotterInDay(String animal, int period) throws UnKnownSpotterException{
      for (Sighting sighting : sightings) {
          if (sighting.getAnimal().equals(animal) && sighting.getPeriod()==period){
              return sighting.getPeriod();
          }
      }
      throw new UnKnownSpotterException("没有观察者观察到此类动物");
  }

}
