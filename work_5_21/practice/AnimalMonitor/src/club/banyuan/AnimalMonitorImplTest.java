package club.banyuan;

import static org.junit.Assert.*;

import java.util.List;
import java.util.function.Consumer;
import org.junit.Test;

public class AnimalMonitorImplTest {

  @Test
  public void Test() {
    List<Sighting> sightings = new SightingFiller().getSightings();
    sightings.forEach(sighting -> System.out.println(sighting.getDetails()));

    AnimalMonitor a = new AnimalMonitorImpl();
    a.printCounts("山地大猩猩");
    System.out.println(a.getCount("山地大猩猩"));
    System.out.println("----------------------------------");
    a.printSightingsBy(0);
    System.out.println("----------------------------------");
    a.printList();
    a.removeZeroCounts();
    System.out.println("----------------------------------");
    a.printList();
  }

}