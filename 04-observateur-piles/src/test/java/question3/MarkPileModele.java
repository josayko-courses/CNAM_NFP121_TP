package question3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * NE TOUCHEZ PAS À CETTE CLASSE (elle sera écrasée par les tests de correction). Vous devez
 * implanter vos propres tests dans PileModeleTest
 *
 * <p>Les tests ci-dessous font appels à des méthodes auxiliaires de test cachées.
 */
public class MarkPileModele {
  private question3.Z_ReferenceTestPileModele test;

  @BeforeEach
  public void setUp() {
    this.test = new Z_ReferenceTestPileModele();
    this.test.setUp();
  }

  @Test
  public void test_presence_fichiers_question3() {
    try {
      Class.forName("question3.PileModele");
      Class.forName("question3.Controleur");
      Class.forName("question3.VuePile");
      Class.forName("question3.VueIHMCalculette");
      Class.forName("question3.IHMCalculette");
    } catch (ClassNotFoundException e) {
      fail("classe absente " + e.getMessage());
    }
  }

  @Test
  public void test_PileModele_empiler() throws Exception {
    test.test_PileModele_empiler();
  }

  @Test
  public void test_PileModele_depiler() throws Exception {
    test.test_PileModele_depiler();
  }

  @Test
  public void test_PileModele_depiler2() throws Exception {
    test.test_PileModele_depiler2();
  }

  @Test
  public void test_PileModele_accesseurs() throws Exception {
    test.test_PileModele_accesseurs();
  }
}
