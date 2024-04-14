package question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class EnsembleTest {
  // Classe de tests à étendre. Les tests de notation consultent
  // cette classe et vérifient que vous testez suffisamment de cas.

  @Test
  public void test_Ensemble_Union() { // {2,3} union {3,4}={2,3,4}
    question1.Ensemble<Integer> e1 = new Ensemble<>();
    e1.add(2);
    e1.add(3);
    question1.Ensemble<Integer> e2 = new Ensemble<>();
    e2.add(4);
    e2.add(3);
    question1.Ensemble<Integer> union = e1.union(e2);
    // À compléter. 
  }

  // À compléter. 
  @Test
  public void test_Ensemble_add() {
    question1.Ensemble<Integer> e1 = new Ensemble<>();
    e1.add(1);
    e1.add(2);
    e1.add(3);
    Set<Integer> e2 = new HashSet<>(Arrays.asList(1, 2, 3));
    assertTrue(e1.size() == e2.size(), "Same size");
    assertTrue(e1.containsAll(e2), "Same elements");
    e1.add(1);
    assertTrue(e1.size() == 3, "No duplicate");
  }
}
