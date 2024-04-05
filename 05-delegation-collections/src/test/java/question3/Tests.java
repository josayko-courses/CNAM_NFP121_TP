package question3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.Test;

public class Tests {

  // Envisagez de créer des méthodes auxiliaires pouvant
   // être appelées indifféremment sur HashSetFactory ou TreeSetFactory::
   //  public void aux(question3.Factory<???> f) {
   // Set<???> set = f.create();
   // for (int i = 20; i > 0; i--) set.add(i);
   // assertEquals(20, set.size());
  //}
   
  @Test
  public void test_TreeSetFactory() {
    question3.TreeSetFactory<Integer> treeSetF1 = new question3.TreeSetFactory<Integer>();
    java.util.Set<Integer> ts = treeSetF1.create();
    assertNotNull(ts, "appel de create retourne null ???");
    // Compléter
  }

  @Test
  public void test_HashSetFactory2() {
    question3.HashSetFactory<Integer> hashSetF1 = new question3.HashSetFactory<Integer>();
    java.util.Set<Integer> hs = hashSetF1.create();
    // Compléter
  }
}
