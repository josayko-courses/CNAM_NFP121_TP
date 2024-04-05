package question1;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import util.InfoClass;
import util.InfoMethod;

@TestInstance(Lifecycle.PER_CLASS) // Une seule instance pour tous les tests.
public class MarkPresenceAppelsDeMethodesClasseDeTest {

  private static final String Q1ENSTESTS = "/question1/EnsembleTest.class";

  @SuppressWarnings("unused")
  private class E {
    public static final String UNION =
        "question1.Ensemble.union(Lquestion1/Ensemble;)Lquestion1/Ensemble;";
    public static final String INTER =
        "question1.Ensemble.inter(Lquestion1/Ensemble;)Lquestion1/Ensemble;";
    public static final String DIFF =
        "question1.Ensemble.diff(Lquestion1/Ensemble;)Lquestion1/Ensemble;";
    public static final String DIFFSYM =
        "question1.Ensemble.diffSym(Lquestion1/Ensemble;)Lquestion1/Ensemble;";
    public static final String RETAINALL = "question1.Ensemble.retainAll(Ljava/util/Collection;)Z";
    public static final String REMOVEALL = "question1.Ensemble.removeAll(Ljava/util/Collection;)Z";
    public static final String ADDALL = "question1.Ensemble.addAll(Ljava/util/Collection;)Z";
    public static final String INIT = "question1.Ensemble.<init>()V";
    public static final String CONTAINS = "question1.Ensemble.contains(Ljava/lang/Object;)Z";
    public static final String ITERATOR = "question1.Ensemble.iterator()Ljava/util/Iterator;";
  }

  InfoClass info;
  InfoMethod all, allTests;

  @BeforeAll
  public void setUp() throws Exception {
    // On lance la collecte d'infos (une seule fois avant les tests)
    info = new InfoClass(Q1ENSTESTS);
    all = info.get("#all#");
    allTests = info.get("#alltests#");
  }

  @Test
  public void test_NbmethodesTest() {
    info.assertNbTestGreater(4);
  }

  @Test
  public void test_appels_union() {
    String msg = "\nPas assez d'appels à " + E.UNION + "dans les méthodes de test.";
    allTests.assertNbCallsGreater(E.UNION, 1, msg);
    // assertNombreAppels(1, E.UNION);
  }

  @Test
  public void test_appels_inter() {
    allTests.assertNbCallsGreater(E.INTER, 1);
  }

  @Test
  public void test_appels_diff() {
    allTests.assertNbCallsGreater(E.DIFF, 1);
  }

  @Test
  public void test_appels_diffsym() {
    allTests.assertNbCallsGreater(E.DIFFSYM, 1);
  }
}
