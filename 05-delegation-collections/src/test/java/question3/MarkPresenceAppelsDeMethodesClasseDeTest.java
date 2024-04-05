package question3;

import java.io.IOException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import util.InfoClass;
import util.InfoMethod;

@TestInstance(Lifecycle.PER_CLASS) // Une seule instance pour tous les tests.
public class MarkPresenceAppelsDeMethodesClasseDeTest {
  private static final String Q3TESTS = "/question3/Tests.class";

  private class H {
    public static final String CREATE = "question3.HashSetFactory.create()Ljava/util/Set;";
    public static final String INIT = "question3.HashSetFactory.<init>()V";
  }

  private class T {
    private static final String INIT = "question3.TreeSetFactory.<init>()V";
    private static final String CREATE = "question3.TreeSetFactory.create()Ljava/util/Set;";
  }

  // contient une map stockant le nb d'appels à chaque méthode dans chaque méthode de test.
  InfoClass info = null;
  // Vue en un seul infomethod
  InfoMethod all; // pseudo -méthode avec tous les appels de la classe
  InfoMethod allTests; // avec tous les appels dans des méthodes de test

  @BeforeAll
  public void setUp() throws IOException {
    // On lance la collecte d'infos (une seule fois avant les tests)
    info = new InfoClass(Q3TESTS);
    // union de tous les appels de méthodes dans la classe
    all = info.get("#all#");
    // pareil mais seulement les méthodes de test
    allTests = info.get("#alltests#");
  }

  @Test
  public void test_NbmethodesTest() {
    info.assertNbTestGreater(4);
  }

  @Test
  public void test_appels_NewHashSetFactTest() {
    allTests.assertNbCallsGreater(H.INIT, 1);
  }

  @Test
  public void test_appels_NewTreeSetFactTest() {
    allTests.assertNbCallsGreater(T.INIT, 1);
  }

  @Test
  public void test_appels_TreeSetCreateTest() {
    allTests.assertNbCallsGreater(T.CREATE, 1);
  }

  @Test
  public void test_appels_HashSetCreateTest() {
    allTests.assertNbCallsGreater(H.CREATE, 1);
  }
}
