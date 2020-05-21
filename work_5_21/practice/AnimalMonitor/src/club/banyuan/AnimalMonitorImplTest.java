package club.banyuan;

import static org.junit.Assert.*;

import java.util.List;
import java.util.function.Consumer;
import org.junit.Test;

public class AnimalMonitorImplTest {

  @Test
  public void Test(){
    List<Sighting> sightings = new SightingFiller().getSightings();
    sightings.forEach(sighting -> System.out.println(sighting.getDetails()));
    Consumer<Sighting> getAnimal = e -> e.getAnimal();

//    () -> sightings.forEach(getAnimal)
    AnimalMonitor a = new AnimalMonitorImpl();
    for (Sighting sighting : sightings) {
//      AnimalMonitor a1 = sighting::;
    }

  }

}