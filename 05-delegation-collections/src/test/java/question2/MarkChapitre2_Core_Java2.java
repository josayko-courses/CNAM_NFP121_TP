package question2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class MarkChapitre2_Core_Java2 {

  @Test
  public void test_méthode_listeDesMots() {
    List<String> l = Chapitre2CoreJava2.listeDesMots();
    assertNotNull(l);
    assertEquals("Core", l.get(0), "premier élément est \"Core\" ???");
    assertEquals(2687, l.size(), "taille est de 2687 ???");
    assertEquals("list", l.get(l.size() - 1), "dernier élément est \"list\" ???");
  }

  @Test
  public void test_Occurrence() {
    Map<String, Integer> h =
        Chapitre2CoreJava2.occurrencesDesMots(Chapitre2CoreJava2.listeDesMots());
    assertNotNull(h);
    assertEquals(657, h.size(), " 657 mots sont attendus ???");
    java.lang.Integer i = h.get("list");
    assertNotNull(i);
    assertEquals(68, i.intValue(), " \"list\" est présent 68 fois ????");
  }
}
